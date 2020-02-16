package com.koro.shophomework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("PRO")
public class ShopPro extends ShopPlus {

    @Value("${discount}")
    double discount;

    @Override
    public void showTotalPrice() {
        double totalPriceWithVat = countPriceWithVat(countPrice());
        double totalCountWithDiscount = includeDiscount(totalPriceWithVat);
        System.out.println("Total price with " + vat + "% VAT: " + totalPriceWithVat);
        System.out.println("Total price with " + discount + "% discount: " + totalCountWithDiscount);
    }

    protected double includeDiscount(double firstPrice){
        double totalPriceWithDiscount = firstPrice - (firstPrice * discount / 100.0);
        return Math.round(totalPriceWithDiscount * 100.0) / 100.0;
    }
}
