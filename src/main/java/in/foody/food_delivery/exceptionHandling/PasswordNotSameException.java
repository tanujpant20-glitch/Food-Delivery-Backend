package in.foody.food_delivery.exceptionHandling;

public class PasswordNotSameException extends RuntimeException{
    public PasswordNotSameException(String msg){
        super(msg);
    }
}
