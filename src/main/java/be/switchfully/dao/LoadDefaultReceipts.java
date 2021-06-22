package be.switchfully.dao;

import be.switchfully.services.AllmartServiceFactory;
import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import be.switchfully.services.CashierService;
import java.util.HashMap;

public class LoadDefaultReceipts {
    private static CashierService cashierService = AllmartServiceFactory.getCashierService();

    public static void createReceipts(){
        Customer customer1 = new Customer("Mert", "Demirok");
        Customer customer2 = new Customer("Asena", "Demirok");
        Customer customer3 = new Customer("Kaan", "Demirok");
        Customer customer4 = new Customer("Almira", "Demirok");

        Product product1 = new Product("Book");
        Product product2 = new Product("Iphone");
        Product product3 = new Product("SamsungGalaxy");
        Product product4 = new Product("Huawei");
        Product product5 = new Product("Smartphone X");
        Product product6 = new Product("Slipper");
        Product product7 = new Product("Shirt");
        Product product8 = new Product("Skirt");
        Product product9 = new Product("Hat");
        Product product10 = new Product("Shoe");
        Product product11 = new Product("Tomato");
        Product product12 = new Product("Cucumber");
        Product product13 = new Product("Stapler");
        Product product14 = new Product("Frame");
        Product product15 = new Product("Bicyle");
        Product product16 = new Product("Rollerskate");
        Product product17 = new Product("Computer");
        Product product18 = new Product("Laptop");
        Product product19 = new Product("MacBook");
        Product product20 = new Product("Sony");
        Product product21 = new Product("Dell");

        HashMap<Product, Integer> productAmountMap1 = new HashMap<>();
        productAmountMap1.put(product1, 5);
        productAmountMap1.put(product9, 1);
        productAmountMap1.put(product20, 3);

        HashMap<Product, Integer> productAmountMap2 = new HashMap<>();
        productAmountMap2.put(product7, 10);
        productAmountMap2.put(product16, 2);
        productAmountMap2.put(product5, 1);

        HashMap<Product, Integer> productAmountMap3 = new HashMap<>();
        productAmountMap3.put(product11, 20);
        productAmountMap3.put(product15, 2);
        productAmountMap3.put(product13, 11);

        HashMap<Product, Integer> productAmountMap4 = new HashMap<>();
        productAmountMap4.put(product10, 5);
        productAmountMap4.put(product1, 6);

        cashierService.generateReceipt(customer1, productAmountMap1);
        cashierService.generateReceipt(customer2, productAmountMap2);
        cashierService.generateReceipt(customer3, productAmountMap3);
        cashierService.generateReceipt(customer4, productAmountMap4);
        cashierService.generateReceipt(customer1, productAmountMap1);
    }
}
