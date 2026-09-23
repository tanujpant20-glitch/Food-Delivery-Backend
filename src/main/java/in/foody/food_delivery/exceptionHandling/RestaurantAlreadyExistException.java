package in.foody.food_delivery.exceptionHandling;

public class RestaurantAlreadyExistException extends RuntimeException{
    public RestaurantAlreadyExistException(String message){
        super(message);
    }
}
