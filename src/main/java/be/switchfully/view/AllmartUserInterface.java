package be.switchfully.view;

import be.switchfully.dao.ReceiptDao;
import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import be.switchfully.services.AllmartServiceFactory;
import be.switchfully.services.CashierService;
import be.switchfully.dao.LoadDefaultReceipts;
import be.switchfully.services.CustomerService;
import be.switchfully.services.ReportingService;

import java.util.HashMap;
import java.util.Scanner;

public class AllmartUserInterface {
    private static CashierService cashierService = AllmartServiceFactory.getCashierService();
    private static CustomerService customerService = AllmartServiceFactory.getCustomerService();
    private static ReportingService reportingServiceDaily = AllmartServiceFactory.getReportingServiceDaily();
    private static ReportingService reportingServiceMonthly = AllmartServiceFactory.getReportingServiceMonthly();
    private static boolean isOpen = true;

    final static Scanner scanner = new Scanner(System.in);

    public static void run(){
        LoadDefaultReceipts.createReceipts();
        openCashRegister();
    }

    private static void openCashRegister(){
        HashMap<Product, Integer> productAmountMap = new HashMap<>();

        while (isOpen){
            System.out.println("Enter Customer FullName:  'X' closes the cash register ");
            String input = scanner.nextLine();

            if(!isSupermarketOpen(input)) break;

            Customer customer;

            if(customerService.findByCustomerName(input) != null){
                customer = customerService.findByCustomerName(input);
            } else {
                customer = new Customer(input);
            }

            System.out.println("Enter product name:");
            String productName = scanner.nextLine();
            Product product = new Product(productName);

            System.out.println("Enter the amount of product:");
            Integer amount = Integer.valueOf(scanner.nextLine());

            productAmountMap.put(product, amount);

            cashierService.generateReceipt(customer, productAmountMap);
        }
        generateReport();
    }

    private static void generateReport() {
        System.out.println("Enter your preference: \n1: Customer-of-the-day Report, \n2: Customer-of-the-month Report");

        switch (scanner.next()) {
            case "1":
                reportingServiceDaily.generateReport();
                break;
            case "2":
                reportingServiceMonthly.generateReport();
                break;
            default:break;
        }
    }

    private static boolean isSupermarketOpen(String input) {
        if (input.equalsIgnoreCase("X")) {
            isOpen = false;
        }
        return isOpen;
    }
}
