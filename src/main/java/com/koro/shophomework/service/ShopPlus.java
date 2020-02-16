package com.koro.shophomework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("PLUS")
public class ShopPlus extends ShopStart {

    @Value("${vat}")
    double vat;

    @Override
    public void showTotalPrice(){
        double totalPriceWithVat = countPriceWithVat(countPrice());
        System.out.println("Total price with " + vat + "% VAT: " + totalPriceWithVat);
    }

    protected double countPriceWithVat(double firstPrice){
        double totalPriceWithVat = firstPrice + (firstPrice * vat / 100.0);
        return Math.round(totalPriceWithVat * 100.0) / 100.0;
    }
}
