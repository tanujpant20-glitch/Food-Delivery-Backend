package in.foody.food_delivery.service.implimentation;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import in.foody.food_delivery.dto.general.OrderPlacedRequest;
import in.foody.food_delivery.dto.request.PaymentVerifyDto;
import in.foody.food_delivery.dto.response.OrderResponseDto;
import in.foody.food_delivery.entity.*;
import in.foody.food_delivery.entity.enums.OrderStatus;
import in.foody.food_delivery.entity.enums.PaymentMode;
import in.foody.food_delivery.entity.enums.PaymentStatus;
import in.foody.food_delivery.exceptionHandling.*;
import in.foody.food_delivery.repository.*;
import in.foody.food_delivery.service.serviceInterfaces.OrderService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderServiceImp implements OrderService {

    @Value("${rozarpay.key_id}")
    private String rozarpayKeyId;

    @Value("${rozarpay.key_secret}")
    private String rozarPaySecret;

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private PaymentRepository paymentRepository;
    private RestaurantRepository restaurantRepository;
    public OrderServiceImp(OrderRepository orderRepository,
                           UserRepository userRepository,
                           CartRepository cartRepository,
                           ModelMapper modelMapper,
                           PaymentRepository paymentRepository,
                           RestaurantRepository restaurantRepository){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
        this.cartRepository=cartRepository;
        this.modelMapper=modelMapper;
        this.paymentRepository=paymentRepository;
        this.restaurantRepository=restaurantRepository;
    }

    @Override
    @Transactional
    public OrderResponseDto orderPlaced(OrderPlacedRequest orderPlacedRequest) throws RazorpayException {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you cannot place order"));
        Address address=user.getAddresses().stream().filter(x->x.getId().equals(orderPlacedRequest.getAddressId())).findFirst().orElseThrow(()->new AddressNotFoundException("you don't have any address"));
        Cart cart=cartRepository.findByCreator(user).orElseThrow(()->new CartDoesNotExistEception("no cart exists"));

        double totalAmount=0.0;
        Order order=new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setOrderStatus(OrderStatus.PAYMENT_PENDING);
        for (CartItem cartItem: cart.getListFoodItems()){
            OrderItems orderItems=new OrderItems();
            totalAmount+=cartItem.getFinalPrice();
            orderItems.setFoodItems(cartItem.getFoodItem());
            orderItems.setOrder(order);
            order.getOrderItemsList().add(orderItems);
            orderItems.setQuantities(cartItem.getQuantities());
        }
        order.setTotalPrice(totalAmount);
        Order savedOrder=orderRepository.save(order);
        RazorpayClient razorpayClient=new RazorpayClient(rozarpayKeyId,rozarPaySecret);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount", order.getTotalPrice()*100);
        jsonObject.put("currency", "INR");
        jsonObject.put("receipt",  savedOrder.getId());

        com.razorpay.Order ordered=razorpayClient.orders.create(jsonObject);
        Payment payment=new Payment();
        payment.setOrder(savedOrder);
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setPaymentStatus(PaymentStatus.NOT_PAID);
        payment.setTransactionId(ordered.get("id"));
        savedOrder.setPayment(payment);
        Order finalSavedOrder=orderRepository.save(savedOrder);

        return modelMapper.map(finalSavedOrder, OrderResponseDto.class);
    }
    @Transactional
    @Override
    public String verifyPayment(PaymentVerifyDto paymentVerifyDto) throws RazorpayException {

        String payload= paymentVerifyDto.getRazorpayOrderId()+ '|' + paymentVerifyDto.getRazorpayPaymentId();
        boolean check= Utils.verifySignature(payload, paymentVerifyDto.getRazorpaySignature(), rozarPaySecret);

        if(!check){
            throw new BadRequestException("Invalid payment signature");
        }

        RazorpayClient rzpclient= new RazorpayClient(rozarpayKeyId,rozarPaySecret);
        com.razorpay.Payment rzrpay=rzpclient.payments.fetch(paymentVerifyDto.getRazorpayPaymentId());
        Payment payment=paymentRepository.findByTransactionId(paymentVerifyDto.getRazorpayOrderId()).orElseThrow(()->new PaymentNotFoundException("Payment record not found for Order ID"));

        Order order=payment.getOrder();
        payment.setPaymentMode(PaymentMode.valueOf(rzrpay.get("method").toString().toUpperCase()));
        payment.setPaymentStatus(PaymentStatus.PAID);
        order.setOrderStatus(OrderStatus.CONFIRMED);

        Cart cart = cartRepository.findByCreator(order.getUser())
                .orElseThrow(() -> new CartDoesNotExistEception("Cart not found"));
        cart.getListFoodItems().clear();


        return "Payment successful and Order confirmed!";
    }
    @Override
    public Page<OrderResponseDto> getOrdersByRestaurant(Long restaurantId,int page, int size) {

        Restaurant restaurant= restaurantRepository.findById(restaurantId).orElseThrow(()->new RestaurantNotExistException("Restaurant does not exists"));
        Pageable pageable= PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Order> orders = orderRepository.findByRestaurant(pageable);

        return orders.map(x->modelMapper.map(x, OrderResponseDto.class));
    }

    @Override
    public Page<OrderResponseDto> getOrders(int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("id").ascending());
       Page<Order> orders=orderRepository.findAll(pageable);
        return orders.map(order -> modelMapper.map(order, OrderResponseDto.class));
    }

    @Override
    public List<OrderResponseDto> getOrdersByUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not Found"));
        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .toList();
    }

    @Override
    public List<OrderResponseDto> getOrdersByDeliveryBoy(Long deliveryBoyId) {
        // Delivery Boy ko assigned orders list
        List<Order> orders = orderRepository.findByDeliveryBoyId(deliveryBoyId);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .toList();
    }

    @Override
    public OrderResponseDto trackOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        return modelMapper.map(order, OrderResponseDto.class);
    }

    @Override
    @Transactional
    public OrderResponseDto cancelledOrder(Long orderId) {
        // Security Check: Authenticated User verification
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedAccessException("Unauthorized user"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        // Check ki order usi user ka hai ya admin cancel kar raha hai
        if (!order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You are not allowed to cancel this order");
        }

        // Business Rule Check: Agar delivery ke liye nikal chuka hai toh cancel na ho
        if (order.getOrderStatus() == OrderStatus.ACCEPTED || order.getOrderStatus() == OrderStatus.OUTFORDELIVERY) {
            throw new BadRequestException("Order cannot be cancelled as it is already " + order.getOrderStatus());
        }

        // Status update
        order.setOrderStatus(OrderStatus.CANCELLED);

        // Agar payment ho chuki ho toh Payment Status bhi handle karo (Optional)
        if (order.getPayment() != null && order.getPayment().getPaymentStatus() == PaymentStatus.PAID) {
            order.getPayment().setPaymentStatus(PaymentStatus.REFUND_INITIATED);
        }

        Order updatedOrder = orderRepository.save(order);
        return modelMapper.map(updatedOrder, OrderResponseDto.class);
    }
}
