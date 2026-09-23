package in.foody.food_delivery.exceptionHandling;

public class RestaurantNotExistException extends RuntimeException {
    public RestaurantNotExistException(String message) {
        super(message);
    }
}
