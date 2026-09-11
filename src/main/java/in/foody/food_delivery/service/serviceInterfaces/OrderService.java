package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.general.OrderPlacedRequest;
import in.foody.food_delivery.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    public OrderResponseDto orderPlaced(OrderPlacedRequest orderPlacedRequest);
    public List<OrderResponseDto> getOrdersByRestaurant(Long RestaurantId);
    public List<OrderResponseDto> getOrders();
    public List<OrderResponseDto> getOrdersByUser(Long userId);
    public List<OrderResponseDto> getOrdersByDeliveryBoy(Long deliveryBoyId);
    public OrderResponseDto trackOrder(Long orderId);
    public OrderResponseDto cancelledOrder(Long orderId);
}
