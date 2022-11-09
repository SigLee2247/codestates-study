package com.codestates.seb.kiosk_program.discount.discountCondition;

import com.codestates.seb.kiosk_program.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class StudentDiscountCondition implements DiscountCondition {

    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    private Scanner sc = new Scanner(System.in);

    public StudentDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
    @Override
    public void checkDiscountCondition() {
        System.out.print("나이가 어떻게 되십니까? : ");
        int input = Integer.parseInt(sc.nextLine());

        if(input < 20) setSatisfied(true);
        else setSatisfied(false);

    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculatePrice(price);
    }
}
