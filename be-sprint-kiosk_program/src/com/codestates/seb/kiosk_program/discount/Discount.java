package com.codestates.seb.kiosk_program.discount;

import com.codestates.seb.kiosk_program.discount.discountCondition.DiscountCondition;

public class Discount {
    private DiscountCondition[] discountConditions;

    public Discount(DiscountCondition[] discountConditions) {
        this.discountConditions = discountConditions;
    }

    public void checkAllDiscountConditions() {
        for (DiscountCondition discountCondition : discountConditions){
            discountCondition.checkDiscountCondition();
        }
    }

    public int discount(int price) {
        int discountPrice = price;

        for(DiscountCondition discountCondition : discountConditions){
            if(discountCondition.isSatisfied()) discountPrice = discountCondition.applyDiscount(discountPrice);
        }
        return discountPrice;
    }
}
