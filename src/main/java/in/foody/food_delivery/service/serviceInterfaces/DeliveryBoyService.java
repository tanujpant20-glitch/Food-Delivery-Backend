package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.DeliveryBoyRequestDto;
import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;
import in.foody.food_delivery.dto.update.DeliveryBoyUpdateDto;
import in.foody.food_delivery.entity.DeliveryBoy;

import java.util.List;

public interface DeliveryBoyService {

    public DeliveryBoyResponseDto getDeliveryBoyById(Long deliveryBoyid);
    public List<DeliveryBoyResponseDto> getDeliveryBoy();
    public DeliveryBoyResponseDto updateDeliveryBoy(Long deliveryBoyId, DeliveryBoyUpdateDto deliveryBoyUpdateDto);
    public DeliveryBoyResponseDto deleteDeliveryBoy(Long deliveryBoyid);
    public DeliveryBoyResponseDto getDeliveryBoyByOrder(Long orderid);
}
