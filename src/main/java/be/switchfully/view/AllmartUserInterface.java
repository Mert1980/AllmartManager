package be.switchfully.view;

import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import be.switchfully.services.*;
import be.switchfully.dao.LoadDefaultReceipts;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;

public class AllmartUserInterface {
    private static CashierService cashierService = AllmartServiceFactory.getCashierService();
    private static CustomerService customerService = AllmartServiceFactory.getCustomerService();
    private static ReportingService reportingServiceMonthly = AllmartServiceFactory.getReportingServiceMonthly();
    private static boolean isCashRegisterOpen = true;
    private static boolean isMoreProduct = true;

    final static Scanner scanner = new Scanner(System.in);

    public static void run(){
        LoadDefaultReceipts.createReceipts();

        scheduleDailyReport();

        openCashRegister();

        askForMonthlyReport();
    }

    private static void scheduleDailyReport() {
        DailyReportScheduler scheduler = new DailyReportScheduler();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormatter.parse("2021-06-2 12:06");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(true);
        timer.schedule(scheduler, date, 86_400_000);
    }

    private static void openCashRegister(){

        while (isCashRegisterOpen){

            System.out.println("Enter customer first name OR enter 'X' to close the cash register ");
            String firstName = scanner.nextLine();

            if(!isSupermarketOpen(firstName)) break;

            System.out.println("Enter customer last name: ");
            String lastName = scanner.nextLine();

            Customer customer;

            if(customerService.findByCustomerName(firstName,lastName) != null){
                customer = customerService.findByCustomerName(firstName,lastName);
            } else {
                customer = new Customer(firstName, lastName);
            }

            HashMap<Product, Integer> productAmountMap = new HashMap<>();

            while(isMoreProduct){
                System.out.println("Enter product name OR enter 'N' if no more product" );
                String productName = scanner.nextLine();

                if(!checkMoreProduct(productName)) break;
                Product product = new Product(productName);

                System.out.println("Enter the amount of product:");
                Integer amount = Integer.valueOf(scanner.nextLine());

                productAmountMap.put(product, amount);
            }

            isMoreProduct = true;
            cashierService.generateReceipt(customer, productAmountMap);
        }
    }

    private static void askForMonthlyReport() {
        System.out.println("Enter 'Y' to print customer of the month report?");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("Y")) {
            reportingServiceMonthly.generateReport();
        }
    }

    private static boolean checkMoreProduct(String input) {
        if (input.equalsIgnoreCase("N")) {
            isMoreProduct = false;
        }
        return isMoreProduct;
    }

    private static boolean isSupermarketOpen(String input) {
        if (input.equalsIgnoreCase("X")) {
            isCashRegisterOpen = false;
        }
        return isCashRegisterOpen;
    }
}
