package in.foody.food_delivery.exceptionHandling;

public class FoodItemNotExistsException extends RuntimeException {
    public FoodItemNotExistsException(String message){
        super(message);
    }
}
