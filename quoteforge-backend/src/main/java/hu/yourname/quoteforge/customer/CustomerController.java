package hu.yourname.quoteforge.customer;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
    
    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}