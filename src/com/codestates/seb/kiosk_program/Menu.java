package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.product.Product;
import com.codestates.seb.kiosk_program.product.ProductRepository;
import com.codestates.seb.kiosk_program.product.subProduct.Kimbab;
import com.codestates.seb.kiosk_program.product.subProduct.Ramen;
import com.codestates.seb.kiosk_program.product.subProduct.Tteokbokki;

public class Menu {
    private ProductRepository productRepository;

    public Menu(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void printMenu() {

        System.out.println("메뉴판");
        kimbapMenu(productRepository);

        tteokbokkiMenu(productRepository);

        ramenMenu(productRepository);

        System.out.println();

        System.out.println("0) 장바구니     +) 주문하기");

        System.out.print("원하시는 메뉴의 번호를 입력해 주세요. : ");

    }

    protected void ramenMenu(ProductRepository productRepository) {
        System.out.println("라면 메뉴");

        for(Product product : productRepository.getProducts()) {
            if(product instanceof Ramen)
                eachPrint(product);
        }
        System.out.println();
    }

    protected void tteokbokkiMenu(ProductRepository productRepository) {
        System.out.println("떡볶이 메뉴");

        for(Product product : productRepository.getProducts()) {
            if(product instanceof Tteokbokki)
                eachPrint(product);
        }
        System.out.println();
    }

    protected void kimbapMenu(ProductRepository productRepository) {
        System.out.println("김밥 메뉴");

        for(Product product : productRepository.getProducts()) {
            if(product instanceof Kimbab)
                eachPrint(product);
        }
        System.out.println();
    }

    private void eachPrint(Product product) {
        System.out.printf("%d) %s(%d원)\t", product.getProductId(), product.getName(), product.getPrice());
    }

}
