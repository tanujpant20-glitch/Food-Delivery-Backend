package in.foody.food_delivery.exceptionHandling;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message){
        super(message);
    }
}
