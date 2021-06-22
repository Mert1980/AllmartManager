package be.switchfully.services;

import be.switchfully.model.Customer;
import be.switchfully.model.Receipt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportingServiceDaily implements ReportingService{

    @Override
    public void generateReport() {
        generateReport(LocalDate.now());
    }

    @Override
    public void generateReport(LocalDate date) {
        List<Receipt> receipts = AllmartServiceFactory.getReceiptDao().getAll();

        ArrayList<Receipt> receiptsOfDay = receipts.stream()
                .filter(receipt -> receipt.getDate().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                .collect(Collectors.toCollection(ArrayList::new));

        receiptsOfDay.forEach(receipt -> calculateDailyScore(receipt));

        System.out.println("Customer-of-the-day Report | Date of generation: " +
                LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth().getValue() + "-" +
                LocalDate.now().getYear());
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("%-20s %-20s","Customer", "Score"));

        receiptsOfDay.stream()
                .map(receipt -> receipt.getCustomer())
                .distinct()
                .filter(customer -> customer != null && !customer.toString().trim().isEmpty())
                .sorted(Comparator.comparingInt(Customer::getScoreDay).reversed())
                .forEach(customer -> System.out.println(String.format("%-20s %-20s", customer.toString(), customer.getScoreDay())));
    }

    private void calculateDailyScore(Receipt receipt) {
        receipt.getCustomer().resetDailyScore();
        int score = calculateScore(receipt);
        if (receipt.getCustomer() != null && !receipt.getCustomer().toString().trim().isEmpty()) {
            receipt.getCustomer().addScoreDay(score);
        }
    }
}
