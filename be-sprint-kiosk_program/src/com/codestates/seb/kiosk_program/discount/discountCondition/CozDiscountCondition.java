package com.codestates.seb.kiosk_program.discount.discountCondition;

import com.codestates.seb.kiosk_program.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class CozDiscountCondition implements DiscountCondition {
    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    private Scanner sc = new Scanner(System.in);

    public CozDiscountCondition(DiscountPolicy discountPolicy) {
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
        System.out.print("코드스테이츠 수강생이십니까? (1) 예 (2) 아니오 : ");
        String input = sc.nextLine();

        if(input.equals("1")) setSatisfied(true);
        else if(input.equals("2")) setSatisfied(false);
    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculatePrice(price);
    }
}
