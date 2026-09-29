package in.foody.food_delivery.service.serviceInterfaces;

import com.razorpay.RazorpayException;
import in.foody.food_delivery.dto.general.OrderPlacedRequest;
import in.foody.food_delivery.dto.request.PaymentVerifyDto;
import in.foody.food_delivery.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    public OrderResponseDto orderPlaced(OrderPlacedRequest orderPlacedRequest) throws RazorpayException;
    public Page<OrderResponseDto> getOrdersByRestaurant(Long RestaurantId, int page, int size);

    public Page<OrderResponseDto> getOrders(int page, int size);

    public List<OrderResponseDto> getOrdersByUser(Long userId);
    public List<OrderResponseDto> getOrdersByDeliveryBoy(Long deliveryBoyId);
    public OrderResponseDto trackOrder(Long orderId);
    public OrderResponseDto cancelledOrder(Long orderId);

    public String verifyPayment(PaymentVerifyDto paymentVerifyDto) throws RazorpayException;
}
