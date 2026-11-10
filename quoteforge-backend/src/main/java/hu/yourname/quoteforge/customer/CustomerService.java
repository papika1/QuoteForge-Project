package hu.yourname.quoteforge.customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public Customer findById(Long id) {
        return customerRepository.findById(id)
        		.orElseThrow(() -> new CustomerNotFoundException(id));
    }
}