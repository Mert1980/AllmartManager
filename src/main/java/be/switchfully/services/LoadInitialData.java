package be.switchfully.services;

import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import be.switchfully.model.Receipt;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadInitialData {
    public static ArrayList<Receipt> createReceipts(){

        ArrayList<Receipt> receipts = new ArrayList<>();

        Customer customer1 = new Customer("Mert");
        Customer customer2 = new Customer("Asena");
        Customer customer3 = new Customer("Kaan");
        Customer customer4 = new Customer("Almira");

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

        Receipt receipt1 = new Receipt(customer1, productAmountMap1);
        Receipt receipt2 = new Receipt(customer2, productAmountMap2);
        Receipt receipt3 = new Receipt(customer3, productAmountMap3);
        Receipt receipt4 = new Receipt(customer4, productAmountMap4);

        Receipt receipt5 = new Receipt(customer1, productAmountMap1);

        receipts.add(receipt1); // score 0
        receipts.add(receipt2); // score 10
        receipts.add(receipt3); // score 9
        receipts.add(receipt4); // score 5
        receipts.add(receipt5); // score 0
        receipts.add(receipt2); // score 10

        return receipts;
    }
}
