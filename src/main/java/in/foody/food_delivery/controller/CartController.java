package in.foody.food_delivery.controller;

import in.foody.food_delivery.dto.general.AddItemToCartDto;
import in.foody.food_delivery.dto.response.CartResponseDto;
import in.foody.food_delivery.service.implimentation.CartServiceImp;
import in.foody.food_delivery.service.serviceInterfaces.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

   private final CartServiceImp cartServiceImp;
   public CartController(CartServiceImp cartServiceImp){
       this.cartServiceImp=cartServiceImp;
   }

   @PostMapping("/private/cart/addToCart")
    public ResponseEntity<CartResponseDto> addToCart(
           @RequestBody AddItemToCartDto addItemToCartDto
           ) {
       return ResponseEntity.status(HttpStatus.CREATED).body(cartServiceImp.addToCart(addItemToCartDto));
   }

   @GetMapping("/private/cart/get")
    public ResponseEntity<CartResponseDto> getCart(){
       return ResponseEntity.status(HttpStatus.FOUND).body(cartServiceImp.getCart());
   }

    @DeleteMapping("/private/cart/removeToCart/{foodItemId}")
    public ResponseEntity<CartResponseDto> removeToCart(@PathVariable("foodItemId") Long foodItemId){
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cartServiceImp.removeFromCart(foodItemId));
    }

    @DeleteMapping("/private/cart/clearCart")
    public ResponseEntity<String> clearCart(){
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cartServiceImp.clearCart());
    }
}
