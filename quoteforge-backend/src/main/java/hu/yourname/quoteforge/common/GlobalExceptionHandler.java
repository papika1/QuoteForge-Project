package hu.yourname.quoteforge.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hu.yourname.quoteforge.customer.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomerNotFound(
            CustomerNotFoundException exception) {

        ApiError error = new ApiError(
                404,
                "Not Found",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}