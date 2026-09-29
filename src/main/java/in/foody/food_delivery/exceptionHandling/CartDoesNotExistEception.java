package in.foody.food_delivery.exceptionHandling;

public class CartDoesNotExistEception extends RuntimeException {
    public CartDoesNotExistEception(String message) {
        super(message);
    }
}
