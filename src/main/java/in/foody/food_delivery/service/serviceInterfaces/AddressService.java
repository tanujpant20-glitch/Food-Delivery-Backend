package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.request.AddressRequestDto;
import in.foody.food_delivery.dto.response.AddressResponseDto;

import java.util.List;

public interface AddressService {

    public AddressResponseDto createAddress(AddressRequestDto addressRequestDto);
    public AddressResponseDto updateAddress(Long AddressId,AddressRequestDto addressRequestDto);

    public List<AddressResponseDto> getAllAddress();

}
