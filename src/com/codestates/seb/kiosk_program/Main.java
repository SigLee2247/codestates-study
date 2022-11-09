package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.product.ProductRepository;

public class Main {

    public static void main(String[] args) {

        KioskConfigurer kioskConfigurer = new KioskConfigurer();

        Kiosk_Program kiosk_program = new Kiosk_Program(
                kioskConfigurer.menu(),
                kioskConfigurer.productRepository(),
                kioskConfigurer.cart(),
                kioskConfigurer.order()
            );

        kiosk_program.start();
    }
}
