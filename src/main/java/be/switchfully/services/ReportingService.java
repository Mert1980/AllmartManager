package be.switchfully.services;

import be.switchfully.model.Receipt;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public interface ReportingService {
    void generateReport();
    void generateReport(LocalDate date);

    default int calculateScore(Receipt receipt){
        int score = 0;

        if(receipt.getCustomer().toString().equalsIgnoreCase("Jane Janukova")){
            score+=10;
        }

        AtomicInteger scoreForSmartPhone = new AtomicInteger();
        receipt.getProductAmountMap().entrySet()
                .stream().forEach(productIntegerEntry -> {
            if(productIntegerEntry.getKey().getProductName().equalsIgnoreCase("SmartPhone X")){
                scoreForSmartPhone.set(productIntegerEntry.getValue() * 5);
            } else scoreForSmartPhone.set(0);
        });

        score+=scoreForSmartPhone.get();

        int amount = receipt.getProductAmountMap()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        score+=amount;

        if(amount > 30) {
            score+=9;
        } else if(amount > 20) {
            score+=7;
        } else if(amount > 10) {
            score+=5;
        }
        return score;
    }
}
