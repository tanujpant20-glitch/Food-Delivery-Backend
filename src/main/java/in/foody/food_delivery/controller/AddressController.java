package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.request.AddressRequestDto;
import in.foody.food_delivery.dto.response.AddressResponseDto;
import in.foody.food_delivery.service.implimentation.AddressServiceImp;
import in.foody.food_delivery.service.serviceInterfaces.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressServiceImp addressService;

    public AddressController(AddressServiceImp addressService) {
        this.addressService = addressService;
    }

    // 1. Create Address
    @PostMapping("private/address/create")
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto response = addressService.createAddress(addressRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Update Address by ID
    @PutMapping("private/address/update/{addressId}")
    public ResponseEntity<AddressResponseDto> updateAddress(
            @PathVariable Long addressId,
            @Valid @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto response = addressService.updateAddress(addressId, addressRequestDto);
        return ResponseEntity.ok(response);
    }

    // 3. Get All Addresses of Authenticated User
    @GetMapping("private/address/getAll")
    public ResponseEntity<List<AddressResponseDto>> getAllAddress() {
        List<AddressResponseDto> addresses = addressService.getAllAddress();
        return ResponseEntity.ok(addresses);
    }
}