package in.foody.food_delivery.exceptionHandling;

public class PaymentNotFoundException extends  RuntimeException{
    public PaymentNotFoundException(String s) {
        super(s);
    }
}
