package com.koro.shophomework.service;

import com.koro.shophomework.model.Product;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("START")
public class ShopStart {

    private static List<Product> productList;
    private static final double MIN_PRICE_OF_PRODUCT = 50;
    private static final double MAX_PRICE_OF_PRODUCT = 300;

    public ShopStart() {
        productList = new ArrayList<>();
        addRandomProducts(5);
    }

    private void addRandomProducts(int numberOfProducts) {
        for (int i=1; i<=numberOfProducts; i++) {
            String name = "product" + i;
            Product product = new Product(name, generateRandomPrice());
            productList.add(product);
        }
    }

    private double generateRandomPrice() {
        double price = Math.random() * (MAX_PRICE_OF_PRODUCT - MIN_PRICE_OF_PRODUCT + 1) + MIN_PRICE_OF_PRODUCT;
        return Math.round(price * 100.0) / 100.0;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showProductList() {
        System.out.println("Basket: ");
        for (Product p: productList) {
            System.out.println(p.toString());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showTotalPrice(){
        double totalPrice = countPrice();
        System.out.println("Total price: " + totalPrice);
    }

    protected double countPrice() {
        double totalPrice = 0;
        for (Product p: productList) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
