package be.switchfully.services;

import be.switchfully.model.Receipt;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public interface ReportingService {
    void generateReport(Collection<Receipt> receipts);

    default int calculateScore(Receipt receipt){
        int score = 0;
        if(receipt.getCustomer() == null || receipt.getCustomer().getFullName().isEmpty()){
            return 0;
        }

        if(receipt.getCustomer().getFullName().equalsIgnoreCase("Jane Janukova")){
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
