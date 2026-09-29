package in.foody.food_delivery.exceptionHandling;

public class FoodItemNotAvailableException extends RuntimeException {
    public FoodItemNotAvailableException(String message) {
        super(message);
    }
}
