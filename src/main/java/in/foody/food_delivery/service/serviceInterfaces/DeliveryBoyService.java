package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.DeliveryBoyRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.entity.DeliveryBoy;

import java.util.List;

public interface DeliveryBoyService {

    public DeliveryBoyResponseDto createDeliveryBoy(DeliveryBoyRequestDto deliveryBoyRequestDto);
    public DeliveryBoyRequestDto getDeliveryBoyById(Long deliveryBoyid);
    public List<DeliveryBoyRequestDto> getDeliveryBoy();
    public DeliveryBoyResponseDto updateDeliveryBoy(Long deliveryBoyid);
    public DeliveryBoyResponseDto deleteDeliveryBoy(Long deliveryBoyid);
    public DeliveryBoyResponseDto getDeliveryBoyByOrder(Long orderid);
}
