package com.codestates.seb.kiosk_program.product.subProduct;

import com.codestates.seb.kiosk_program.product.Product;

public class Kimbab extends Product {
    private boolean hasCucumber;

    public Kimbab(int productId, String name, int amount, int price, boolean hasCucumber) {
        super(productId, name, amount, price);
        this.hasCucumber = hasCucumber;
    }

    public Kimbab(Kimbab kimbab) {
        super(kimbab.getProductId(), kimbab.getName(), kimbab.getAmount(), kimbab.getPrice());
        this.hasCucumber = kimbab.isHasCucumber();
    }

    public boolean isHasCucumber() {
        return hasCucumber;
    }

    public void setHasCucumber(boolean hasCucumber) {
        this.hasCucumber = hasCucumber;
    }
}
