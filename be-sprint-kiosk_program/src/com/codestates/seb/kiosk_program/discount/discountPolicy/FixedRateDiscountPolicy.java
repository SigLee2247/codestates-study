package com.codestates.seb.kiosk_program.discount.discountPolicy;

public class FixedRateDiscountPolicy implements DiscountPolicy {
    private int rate;

    public FixedRateDiscountPolicy(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int calculatePrice(int price) {
        return price - ( price * rate / 100);
    }
}
