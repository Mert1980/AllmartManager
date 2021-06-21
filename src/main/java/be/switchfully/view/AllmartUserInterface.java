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
    private static ReportingService reportingServiceDaily = AllmartServiceFactory.getReportingServiceDaily();
    private static ReportingService reportingServiceMonthly = AllmartServiceFactory.getReportingServiceMonthly();
    private static boolean isCashRegisterOpen = true;
    private static boolean isMoreProduct = true;

    final static Scanner scanner = new Scanner(System.in);

    public static void run(){
        LoadDefaultReceipts.createReceipts();

        openCashRegister();

        scheduleDailyReport();

        askForMonthlyReport();
    }

    private static void scheduleDailyReport() {
        DailyReportScheduler scheduler = new DailyReportScheduler();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormatter.parse("2021-06-21 19:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(true);
        timer.schedule(scheduler, date, 86_400_000);
    }

    private static void openCashRegister(){

        while (isCashRegisterOpen){

            System.out.println("Enter Customer FullName:  'X' closes the cash register ");
            String input = scanner.nextLine();

            if(!isSupermarketOpen(input)) break;

            HashMap<Product, Integer> productAmountMap = new HashMap<>();
            Customer customer;

            if(customerService.findByCustomerName(input) != null){
                customer = customerService.findByCustomerName(input);
            } else {
                customer = new Customer(input);
            }

            while(isMoreProduct){
                System.out.println("Enter product name: 'X' if no more product" );
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
        System.out.println("Do you want to print customer of the month report? Y/N");
        switch (scanner.next()) {
            case "Y":
                reportingServiceMonthly.generateReport();
                break;
            default:break;
        }
    }

    private static boolean checkMoreProduct(String input) {
        if (input.equalsIgnoreCase("X")) {
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
