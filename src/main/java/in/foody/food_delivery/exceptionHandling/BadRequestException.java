package in.foody.food_delivery.exceptionHandling;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
