package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.discount.Discount;
import com.codestates.seb.kiosk_program.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.kiosk_program.discount.discountCondition.DiscountCondition;
import com.codestates.seb.kiosk_program.discount.discountCondition.StudentDiscountCondition;
import com.codestates.seb.kiosk_program.discount.discountPolicy.FixedAmountDiscountPolicy;
import com.codestates.seb.kiosk_program.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.kiosk_program.product.ProductRepository;

public class KioskConfigurer {

    private ProductRepository productRepository = new ProductRepository();
    private Cart cart = new Cart(menu(), productRepository);
    private Discount discount;

    public ProductRepository productRepository() { return new ProductRepository(); }

    public Menu menu() { return new Menu(productRepository); }

    public Order order() { return new Order(cart, discount()); }

    public Cart cart() { return cart; }

    public Discount discount() {
        return new Discount(
                new DiscountCondition[] {
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new StudentDiscountCondition(new FixedAmountDiscountPolicy(500))
                }
        );
    }


}
