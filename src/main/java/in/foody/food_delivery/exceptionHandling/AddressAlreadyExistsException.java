package in.foody.food_delivery.exceptionHandling;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}
