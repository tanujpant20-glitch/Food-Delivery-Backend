package in.foody.food_delivery.exceptionHandling;

public class FoodItemAlreadyExistsException extends RuntimeException {
    public FoodItemAlreadyExistsException(String s) {
        super(s);
    }
}
