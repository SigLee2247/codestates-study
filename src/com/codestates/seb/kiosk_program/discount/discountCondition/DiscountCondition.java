package com.codestates.seb.kiosk_program.discount.discountCondition;

public interface DiscountCondition {
    // 디스카운트 하는지 물어보는 창
    void checkDiscountCondition();
    // 디스카운트 하고 난 다음 금액
    int applyDiscount(int price);
    // 디스카운트 하는지 여부
    boolean isSatisfied();
}
