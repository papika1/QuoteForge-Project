package hu.yourname.quoteforge.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CustomerNotFoundException extends RuntimeException {

	
    public CustomerNotFoundException(Long id) {
        super("Customer not found with id: " + id);
    }
}