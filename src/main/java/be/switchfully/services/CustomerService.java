package be.switchfully.services;

import be.switchfully.model.Customer;

public interface CustomerService {
    Customer findByCustomerName(String name);
    Customer findCustomerById(String id);
}
