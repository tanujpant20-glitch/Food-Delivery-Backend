package in.foody.food_delivery.service.serviceInterfaces;

import in.foody.food_delivery.dto.general.AddItemToCartDto;
import in.foody.food_delivery.dto.response.CartResponseDto;

public interface CartService {
    public CartResponseDto addToCart(AddItemToCartDto addItemToCartdto);
    public CartResponseDto getCart();
    public CartResponseDto removeFromCart( Long foodItemId);
    public String clearCart();
}
