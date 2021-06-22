package be.switchfully.services;

import be.switchfully.model.Customer;

public interface CustomerService {
    Customer findByCustomerName(String firstName, String lastName);
    Customer findCustomerById(String id);
}
