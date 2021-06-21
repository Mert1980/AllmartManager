package be.switchfully.services;

import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import java.util.HashMap;

public interface CashierService {
    void generateReceipt(Customer customer, HashMap<Product, Integer> productAmountMap);
}
