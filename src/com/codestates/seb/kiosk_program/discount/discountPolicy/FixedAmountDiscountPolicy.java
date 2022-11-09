package com.codestates.seb.kiosk_program.discount.discountPolicy;

public class FixedAmountDiscountPolicy implements DiscountPolicy{
    private int amount;

    public FixedAmountDiscountPolicy(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int calculatePrice(int price) {
        return price - amount;
    }
}
