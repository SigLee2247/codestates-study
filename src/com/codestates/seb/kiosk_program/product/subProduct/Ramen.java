package com.codestates.seb.kiosk_program.product.subProduct;

import com.codestates.seb.kiosk_program.product.Product;

public class Ramen extends Product {
    private boolean hasEgg;

    public Ramen(int productId, String name, int amount, int price, boolean hasEgg) {
        super(productId, name, amount, price);
        this.hasEgg = hasEgg;
    }

    public Ramen(Ramen ramen) {
        super(ramen.getProductId(), ramen.getName(), ramen.getAmount(), ramen.getPrice());
        this.hasEgg = ramen.isHasEgg();
    }

    public boolean isHasEgg() {
        return hasEgg;
    }

    public void setHasEgg(boolean hasEgg) {
        this.hasEgg = hasEgg;
    }
}
