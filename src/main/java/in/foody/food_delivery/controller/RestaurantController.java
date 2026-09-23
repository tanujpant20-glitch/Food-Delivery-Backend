package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.request.RestaurantRequestDto;
import in.foody.food_delivery.dto.response.RestaurantResponseDto;
import in.foody.food_delivery.dto.update.RestaurantUpdateDto;
import in.foody.food_delivery.service.implimentation.RestaurantServiceImp;
import in.foody.food_delivery.service.serviceInterfaces.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private RestaurantServiceImp restaurantServiceImp;
    public RestaurantController(RestaurantServiceImp restaurantServiceImp){
        this.restaurantServiceImp=restaurantServiceImp;
    }

    @PostMapping("/private/Restaurants/create")
    public ResponseEntity<RestaurantResponseDto> addRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantServiceImp.createRestaurant(restaurantRequestDto));
    }

    @GetMapping("/public/Restaurants")
    private ResponseEntity<Page<RestaurantResponseDto>> getAllRestaurant(@RequestParam int pageNo,
                                                                         @RequestParam String sortBy,
                                                                         @RequestParam int pageSize,
                                                                         @RequestParam String sortDir){
          int Max_Size=50;
          if(pageSize>Max_Size){
              pageSize=Max_Size;
          }

        Sort sort=sortDir.equalsIgnoreCase("ASC")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<RestaurantResponseDto> response=restaurantServiceImp.getAllRestaurant(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/restaurant/{id}")
    public ResponseEntity<RestaurantResponseDto> getRetaurantById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantServiceImp.getRestaurant(id));
    }

    @PutMapping("/private/restaurant/{id}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantUpdateDto restaurantRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantServiceImp.updateRestaurant(id,restaurantRequestDto));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id){
        restaurantServiceImp.deleteRestaurant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/public/getOpenRestaurant")
    public ResponseEntity<Page<RestaurantResponseDto>> getOpenRestaurant(@RequestParam int pageNo,
                                                                         @RequestParam int pageSize,
                                                                         @PathVariable boolean status){

        Page<RestaurantResponseDto> response=restaurantServiceImp.getRestaurantsIsOpenStatus(status, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
