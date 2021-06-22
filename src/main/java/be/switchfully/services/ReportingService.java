package be.switchfully.services;

import be.switchfully.model.Product;
import be.switchfully.model.Receipt;
import java.time.LocalDate;
import java.util.Map;

public interface ReportingService {

    void generateReport();

    void generateReport(LocalDate date);

    default int calculateScore(Receipt receipt){
        int score = 0;

        if (receipt.getCustomer().toString().trim().isEmpty()) {
            return 0;
        }

        score+=scoreSpecialCustomer(receipt);

        score+=scoreSpecialProduct(receipt);

        int totalAmount = totalAmountOfProduct(receipt);

        score+=totalAmount;

        score+=scoreTotalAmountOfProduct(totalAmount);

        return score;
    }

    private int scoreTotalAmountOfProduct(int totalAmount) {
        if(totalAmount > 30) {
            return 9;
        } else if(totalAmount > 20) {
            return 7;
        } else if(totalAmount > 10) {
            return 5;
        }
        return 0;
    }

    private int totalAmountOfProduct(Receipt receipt){
        return receipt.getProductAmountMap()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    };

    private int scoreSpecialCustomer(Receipt receipt){
        if(receipt.getCustomer().toString().equalsIgnoreCase("Jane Janukova")){
            return 10;
        }
        return 0;
    };

    private int scoreSpecialProduct(Receipt receipt){
        for (Map.Entry<Product, Integer> productEntry : receipt.getProductAmountMap().entrySet()) {
            if(productEntry.getKey().getProductName().equalsIgnoreCase("SmartPhone X")){
                return productEntry.getValue()*5;
            }
        }
        return 0;
    };
}
