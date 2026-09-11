package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.response.DeliveryBoyResponseDto;

import java.util.List;

public interface DeliveryBoyEarningService {

    public List<DeliveryBoyResponseDto> getDeliveryBoyEarningByDeliveryBoy();

}
