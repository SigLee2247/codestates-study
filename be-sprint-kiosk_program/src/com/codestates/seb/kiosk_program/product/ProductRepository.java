package com.codestates.seb.kiosk_program.product;

import com.codestates.seb.kiosk_program.product.subProduct.Kimbab;
import com.codestates.seb.kiosk_program.product.subProduct.Ramen;
import com.codestates.seb.kiosk_program.product.subProduct.Tteokbokki;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductRepository {
//    private Product[] products = new Product[] {
//            new Kimbab(1, "김밥", 99, 1000, true),
//            new Kimbab(3, "계란김밥", 99, 1500, true),
//            new Tteokbokki(4, "떡볶이", 99, 2000, 1),
//            new Tteokbokki(5, "치즈 떡볶이", 99, 3000, 1),
//            new Ramen(6, "라면", 99, 1500, false),
//            new Ramen(7, "해장라면", 99, 2500, false)
//    };

    private ArrayList<Product> products1 = new ArrayList<>();

    public ProductRepository() {
        products1.add(new Kimbab(1, "김밥", 99, 1000, true));
        products1.add(new Kimbab(2, "참치김밥", 30, 2500, true));
        products1.add(new Kimbab(3, "계란김밥", 99, 1500, true));
        products1.add(new Tteokbokki(4, "떡볶이", 99, 2000, 1));
        products1.add(new Tteokbokki(5, "치즈 떡볶이", 99, 3000, 1));
        products1.add(new Ramen(6, "라면", 99, 1500, false));
        products1.add(new Ramen(7, "해장라면", 99, 2500, false));
    }

    public ArrayList<Product> getProducts1() {
        return products1;
    }

//    public Product[] getProducts() {
//        return products;
//    }

//    public Product findById(int productId) {
//        for(Product product : products1){
//            if(productId == product.getProductId()) return product;
//        }
//        return null;
//    }

    public Product findById2(int productId) {
        for(Product product : products1){
            if(productId == product.getProductId()) return product;
        }
        return null;
    }

    public boolean findByName(String productName) {
        for(Product product : products1) {
            if(productName.equals(product.getName())) return true;
        }
        return false;
    }

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        Product product;
        try {
            System.out.print("상품 종류를 선택해주세요. (1) 김밥 (2) 떡볶이 (3) 라면 : ");
            int productType = Integer.parseInt(sc.nextLine());
            if(productType <= 0 || productType > 3){
                return;
            }
            System.out.print("상품 이름을 입력해주세요. : ");
            String name = sc.nextLine();
            if(findByName(name)){
                System.out.println("같은 이름이 이미 있습니다. 처음으로 돌아갑니다.");
                return;
            }
            System.out.print("상품 가격을 입력해주세요. : ");
            int price = Integer.parseInt(sc.nextLine());
            if(price <= 0) {
                System.out.println("상품 가격은 0보다 작을 수 없습니다. 처음으로 돌아갑니다.");
                return;
            }
            System.out.print("상품 수량을 입력해주세요. : ");
            int amount = Integer.parseInt(sc.nextLine());
            if(amount <= 0) {
                System.out.println("수량이 0보다 작을 수 없습니다. 처음으로 돌아갑니다.");
                return;
            }

            switch (productType) {
                case 1 :
                    product = new Kimbab(products1.size()+1, name, amount, price, true);
                    break;
                case 2 :
                    product = new Tteokbokki(products1.size()+1, name, amount, price, 1);
                    break;
                default :
                    product = new Ramen(products1.size()+1, name, amount, price, false);
                    break;
            }

            products1.add(product);

            System.out.println("상품 등록에 성공하셨습니다.");

        } catch (Exception e){
            System.out.println("잘못 입력하셨습니다. 처음으로 돌아갑니다.");
        }

    }
}
