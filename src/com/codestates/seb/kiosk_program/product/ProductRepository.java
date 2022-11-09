package com.codestates.seb.kiosk_program.product;

import com.codestates.seb.kiosk_program.product.subProduct.Kimbab;
import com.codestates.seb.kiosk_program.product.subProduct.Ramen;
import com.codestates.seb.kiosk_program.product.subProduct.Tteokbokki;

public class ProductRepository {
    private Product[] products = new Product[] {
            new Kimbab(1, "김밥", 99, 1000, true),
            new Kimbab(2, "충무김밥", 99, 1000, true),
            new Kimbab(3, "계란김밥", 99, 1500, true),
            new Tteokbokki(4, "떡볶이", 99, 2000, 1),
            new Tteokbokki(5, "치즈 떡볶이", 99, 3000, 1),
            new Ramen(6, "라면", 99, 1500, false),
            new Ramen(7, "해장라면", 99, 2500, false)
    };

    public Product[] getProducts() {
        return products;
    }

    public Product findById(int productId) {
        for(Product product : products){
            if(productId == product.getProductId()) return product;
        }
        return null;
    }
}
