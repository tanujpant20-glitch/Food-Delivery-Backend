package in.foody.food_delivery.exceptionHandling;

import in.foody.food_delivery.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PasswordNotSameException.class)
    public ResponseEntity<ExceptionResponse> handlePasswordNotSameException(
            PasswordNotSameException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(exceptionResponse);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(
            UserNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> UserAlreadyExistException(
            UserAlreadyExistException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.ALREADY_REPORTED.value(),
                HttpStatus.ALREADY_REPORTED.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(exceptionResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCredentialsException(
            InvalidCredentialsException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RestaurantAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            RestaurantAlreadyExistException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RestaurantNotExistException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            RestaurantNotExistException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            UnauthorizedAccessException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exceptionResponse);
    }

    @ExceptionHandler(FoodItemNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            FoodItemNotExistsException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(FoodItemNotAvailableException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            FoodItemNotAvailableException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
    @ExceptionHandler(  CartDoesNotExistEception.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            CartDoesNotExistEception ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  AddressAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            AddressAlreadyExistsException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.ALREADY_REPORTED.value(),
                HttpStatus.ALREADY_REPORTED.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            AddressNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  RestaurantNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantAlreadyExistException(
            RestaurantNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  FoodItemAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleFoodItemAlreadyExistsException(
            FoodItemAlreadyExistsException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.ALREADY_REPORTED.value(),
                HttpStatus.ALREADY_REPORTED.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(
            BadRequestException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }


    @ExceptionHandler(  PaymentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePaymentNotFoundException(
            PaymentNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }


    @ExceptionHandler(  OrderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOrderNotFoundException(
            OrderNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(  ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest rs
    ){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                rs.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

}
