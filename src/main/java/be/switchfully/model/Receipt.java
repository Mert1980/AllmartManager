package be.switchfully.model;

import lombok.*;
import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
public class Receipt {
    private Customer customer;
    private LocalDate date = LocalDate.now();
    private HashMap<Product, Integer> productAmountMap;

    public Receipt(Customer customer, HashMap<Product, Integer> productAmountMap) {
        this.customer = customer;
        this.productAmountMap = productAmountMap;
    }
}
