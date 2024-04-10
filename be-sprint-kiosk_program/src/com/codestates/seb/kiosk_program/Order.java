package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.discount.Discount;
import com.codestates.seb.kiosk_program.product.Product;

public class Order {
    private Cart cart;
    private Discount discount;

    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOrder() {
        discount.checkAllDiscountConditions();
        int totalPrice = cart.totalPrice();
        int finalPrice = discount.discount(totalPrice);

        System.out.println("주문이 완료되었습니다.");
        System.out.println("주문하신 내역은 다음과 같습니다.");

        cart.printAllCartProduct(false);

        System.out.printf("주문하신 메뉴의 총 금액은 %d입니다.\n", totalPrice);
        System.out.printf("주문하신 메뉴의 할인 적용 금액은 %d입니다.\n", finalPrice);

    }

}
