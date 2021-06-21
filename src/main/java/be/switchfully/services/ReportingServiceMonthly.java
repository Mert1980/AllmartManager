package be.switchfully.services;

import be.switchfully.model.Customer;
import be.switchfully.model.Receipt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportingServiceMonthly implements ReportingService {

    @Override
    public void generateReport() {
        generateReport(LocalDate.now());
    }

    @Override
    public void generateReport(LocalDate date) {
        List<Receipt> receipts = AllmartServiceFactory.getReceiptDao().getAll();

        ArrayList<Receipt> receiptsOfDay = receipts.stream()
                .filter(receipt -> receipt.getDate().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toCollection(ArrayList::new));

        receiptsOfDay.forEach(receipt -> calculateMonthlyScore(receipt));

        System.out.println("Customer-of-the-month Report | Date of generation: " +
                LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth().getValue() + "-" +
                LocalDate.now().getYear());
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("%-20s %-20s","Customer", "Score"));

        receiptsOfDay.stream()
                .map(receipt -> receipt.getCustomer())
                .distinct()
                .filter(customer -> customer != null && !customer.getFullName().isEmpty())
                .sorted(Comparator.comparingInt(Customer::getScoreMonth).reversed())
                .forEach(customer -> System.out.println(String.format("%-20s %-20s", customer.getFullName(), customer.getScoreMonth())));
    }

    private void calculateMonthlyScore(Receipt receipt) {
        int score = calculateScore(receipt);
        if (receipt.getCustomer() != null) {
            receipt.getCustomer().setScoreMonth(score);
            System.out.println(receipt.getCustomer().getScoreMonth());
        }
    }
}
