package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.product.Product;
import com.codestates.seb.kiosk_program.product.ProductRepository;
import com.codestates.seb.kiosk_program.product.subProduct.Kimbab;
import com.codestates.seb.kiosk_program.product.subProduct.Ramen;
import com.codestates.seb.kiosk_program.product.subProduct.Tteokbokki;

import java.util.Scanner;

public class Cart {
    private Product[] products = new Product[0];
    private Menu menu;
    private ProductRepository productRepository;
    private Scanner sc = new Scanner(System.in);

    public Cart(Menu menu, ProductRepository productRepository) {
        this.menu = menu;
        this.productRepository = productRepository;
    }

    public void addCart(int productId) {
        Product product = productRepository.findById(productId);
        chooseOptions(product);
        Product newProduct;
        if(product instanceof Kimbab) newProduct = new Kimbab((Kimbab) product);
        else if(product instanceof Tteokbokki) newProduct = new Tteokbokki((Tteokbokki) product);
        else  newProduct = new Ramen((Ramen) product);

        addAmount(newProduct);

        Product[] newProducts = new Product[products.length + 1];

        System.arraycopy(products, 0, newProducts, 0, products.length);
        newProducts[newProducts.length - 1] = newProduct;

        products = newProducts;

        System.out.printf("%s 메뉴를 %d개 장바구니에 담았습니다.\n", newProduct.getName(), newProduct.getAmount());
    }

    private Product chooseOptions(Product product) {
        if(product instanceof Kimbab){
            System.out.print("오이를 빼시겠습니까? 1) 예 2) 아니오 : ");
            ((Kimbab) product).setHasCucumber(Integer.parseInt(sc.nextLine()) == 1 ? false : true);
        } else if(product instanceof Tteokbokki){
            System.out.print("맵기는 어느정도로 하시겠습니까? 1) 순한맛 2) 중간맛 3) 매운맛 4) 지옥맛 : ");
            ((Tteokbokki) product).setSpicyStep(Integer.parseInt(sc.nextLine()));
        } else if(product instanceof Ramen){
            System.out.print("계란을 추가하시겠습니까? 1) 예 2) 아니오 : ");
            ((Ramen) product).setHasEgg(Integer.parseInt(sc.nextLine()) == 1 ? true : false);
        }

        return product;
    }

    private Product addAmount(Product product) {
        int maxAmount = productRepository.findById(product.getProductId()).getAmount();
        System.out.print("선택하신 메뉴의 수량을 입력하여 주세요.");
        System.out.printf("( ※ 최대 주문 가능 수량 : %d ) : ",
                maxAmount);
        int amount = Integer.parseInt(sc.nextLine());
        if(amount > maxAmount || amount <= 0){
            System.out.printf("[경고] %d는 입력하실 수 없습니다.", amount);
            System.out.println("수량 선택 화면으로 돌아갑니다.");
            addAmount(product);
        }
        else {
            product.setAmount(amount);
            productRepository.findById(product.getProductId()).setAmount(maxAmount - amount);
        }

        return product;

    }

    public void printAllCartProduct() {
        System.out.println("-".repeat(70));
        for(Product product : products) {
            if(product instanceof Kimbab) {
                System.out.printf("메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 오이 : %s\n",
                        product.getName(), product.getAmount(), product.getPrice() * product.getAmount(),
                        (((Kimbab) product).isHasCucumber() ? "O" : "X")
                );
            } else if(product instanceof Tteokbokki) {
                System.out.printf("메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 맵기 정도 : ",
                        product.getName(), product.getAmount(), product.getPrice() * product.getAmount()
                );
                String spicyStep = ((Tteokbokki) product).spicySepName(((Tteokbokki) product).getSpicyStep());

                System.out.println(spicyStep);
            } else {
                System.out.printf("메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 계란 : %s\n",
                        product.getName(), product.getAmount(), product.getPrice() * product.getAmount(),
                        (((Ramen) product).isHasEgg() ? "O" : "X")
                );
            }
        }
        System.out.println("-".repeat(70));
    }

    public int totalPrice() {
        int totalPrice = 0;
        for(Product product : products){
            totalPrice += product.getPrice() * product.getAmount();
        }
        return totalPrice;
    }
}
