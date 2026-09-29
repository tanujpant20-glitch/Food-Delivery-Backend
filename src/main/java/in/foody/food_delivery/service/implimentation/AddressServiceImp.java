package in.foody.food_delivery.service.implimentation;

import in.foody.food_delivery.dto.request.AddressRequestDto;
import in.foody.food_delivery.dto.response.AddressResponseDto;
import in.foody.food_delivery.entity.Address;
import in.foody.food_delivery.entity.User;
import in.foody.food_delivery.exceptionHandling.AddressAlreadyExistsException;
import in.foody.food_delivery.exceptionHandling.AddressNotFoundException;
import in.foody.food_delivery.exceptionHandling.UnauthorizedAccessException;
import in.foody.food_delivery.exceptionHandling.UserNotFoundException;
import in.foody.food_delivery.repository.AddressRepository;
import in.foody.food_delivery.repository.UserRepository;
import in.foody.food_delivery.service.serviceInterfaces.AddressService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AddressRepository addressRepository;
    public AddressServiceImp(UserRepository userRepository,AddressRepository addressRepository
    ,ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.addressRepository=addressRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    @Transactional
    public AddressResponseDto createAddress(AddressRequestDto addressRequestDto) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you are not authorised to do this"));
        boolean isExists=addressRepository.existsByUserAndHouseNumberAndPincode(user, addressRequestDto.getHouseNumber().trim(), addressRequestDto.getPinCode().trim());
        if(isExists){
            throw new AddressAlreadyExistsException("This address already exists");
        }

        Address address=new Address();
        address.setPincode(addressRequestDto.getPinCode());
        address.setCity(addressRequestDto.getCity());
        address.setCountry(addressRequestDto.getCountry());
        address.setHouseNumber(addressRequestDto.getHouseNumber());
        address.setNearBy(addressRequestDto.getNearBy());
        address.setState(addressRequestDto.getState());
        address.setUser(user);
        Address savedAddress=addressRepository.save(address);
            return modelMapper.map(savedAddress,AddressResponseDto.class);
    }

    @Override
    @Transactional
        public AddressResponseDto updateAddress(Long addressId, AddressRequestDto addressRequestDto) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UnauthorizedAccessException("you are not authorised to do this"));

            Address address = addressRepository.findByIdAndUser(addressId, user)
                    .orElseThrow(() -> new AddressNotFoundException("Address not found for this user"));

            address.setHouseNumber(addressRequestDto.getHouseNumber().trim());
            address.setNearBy(addressRequestDto.getNearBy());
            address.setCity(addressRequestDto.getCity());
            address.setState(addressRequestDto.getState());
            address.setCountry(addressRequestDto.getCountry());
            address.setPincode(addressRequestDto.getPinCode().trim());

            Address updatedAddress = addressRepository.saveAndFlush(address);
            return modelMapper.map(updatedAddress, AddressResponseDto.class);
        }

        public List<AddressResponseDto> getAllAddress(){
                String email=SecurityContextHolder.getContext().getAuthentication().getName();

                User user=userRepository.findByEmail(email).orElseThrow(()->new UnauthorizedAccessException("you are not authorised to do this"));

                List<Address> allAddress=addressRepository.findByUser(user);
                return allAddress.stream().map(x->modelMapper.map(x,AddressResponseDto.class)).toList();
        }

}
