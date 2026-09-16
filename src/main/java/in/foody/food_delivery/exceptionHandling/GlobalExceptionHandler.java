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
}
