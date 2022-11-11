package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.product.Product;
import com.codestates.seb.kiosk_program.product.ProductRepository;
import com.codestates.seb.kiosk_program.product.subProduct.Kimbab;
import com.codestates.seb.kiosk_program.product.subProduct.Ramen;
import com.codestates.seb.kiosk_program.product.subProduct.Tteokbokki;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart {
    // private Product[] products = new Product[0];
    private ArrayList<Product> products = new ArrayList<>();
    private ProductRepository productRepository;
    private Scanner sc = new Scanner(System.in);

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void removeCart(int index){
        if(index < 1 && index > products.size()){
            System.out.println("장바구니 비우기에 실패했습니다.");
            return;
        }
        Product getProduct = products.get(index-1);
        System.out.print(index + ") " + getProduct.getName() + " " + getProduct.getAmount() + "개를 정말 제거하시겠습니까? (y/n) : ");
        String answer = sc.nextLine();
        if(!answer.equals("y")){
            if(answer.equals("n")) {
                System.out.println(index + ") " + getProduct.getName() + " " + getProduct.getAmount() + "개 제거를 취소하셨습니다.");
                return;
            }
            System.out.println("잘못 입력하셨습니다. 메뉴로 돌아갑니다.");
            return;
        }
        Product removeProduct = products.remove(index-1);
        System.out.println(removeProduct.getName() + " " + removeProduct.getAmount() + "개를 장바구니에서 제거했습니다/");
    }

    public void removeAllCart() {
        products.clear();
        System.out.println("장바구니를 모두 비웠습니다.");
    }

    public void addCart(int productId) {
        Product product = productRepository.findById2(productId);
        if (product.getAmount() <= 0) {
            System.out.println("상품이 품절되었습니다. 처음으로 돌아갑니다.");
            return;
        }
        chooseOptions(product);
        Product newProduct;
        if (product instanceof Kimbab) newProduct = new Kimbab((Kimbab) product);
        else if (product instanceof Tteokbokki) newProduct = new Tteokbokki((Tteokbokki) product);
        else newProduct = new Ramen((Ramen) product);

        addAmount(newProduct);

//        Product[] newProducts = new Product[products.length + 1];
//
//        System.arraycopy(products, 0, newProducts, 0, products.length);
//        newProducts[newProducts.length - 1] = newProduct;

//        products = newProducts;
        products.add(newProduct);

        System.out.printf("%s 메뉴를 %d개 장바구니에 담았습니다.\n", newProduct.getName(), newProduct.getAmount());
    }

    private Product chooseOptions(Product product) {
        if (product instanceof Kimbab) {
            System.out.print("오이를 빼시겠습니까? 1) 예 2) 아니오 : ");
            ((Kimbab) product).setHasCucumber(Integer.parseInt(sc.nextLine()) == 1 ? false : true);
        } else if (product instanceof Tteokbokki) {
            System.out.print("맵기는 어느정도로 하시겠습니까? 1) 순한맛 2) 중간맛 3) 매운맛 4) 지옥맛 : ");
            ((Tteokbokki) product).setSpicyStep(Integer.parseInt(sc.nextLine()));
        } else if (product instanceof Ramen) {
            System.out.print("계란을 추가하시겠습니까? 1) 예 2) 아니오 : ");
            ((Ramen) product).setHasEgg(Integer.parseInt(sc.nextLine()) == 1 ? true : false);
        }

        return product;
    }

    private Product addAmount(Product product) {
        int maxAmount = productRepository.findById2(product.getProductId()).getAmount();
        System.out.print("선택하신 메뉴의 수량을 입력하여 주세요.");
        System.out.printf("( ※ 최대 주문 가능 수량 : %d ) : ", maxAmount);
        int amount = Integer.parseInt(sc.nextLine());
        if (amount > maxAmount || amount <= 0) {
            System.out.printf("[경고] %d는 입력하실 수 없습니다.", amount);
            System.out.println("수량 선택 화면으로 돌아갑니다.");
            addAmount(product);
        } else {
            product.setAmount(amount);
            productRepository.findById2(product.getProductId()).setAmount(maxAmount - amount);
        }

        return product;

    }

    public void printAllCartProduct(boolean isCart) {
        if(products.size() == 0){
            System.out.println("장바구니가 비어있습니다.");
            return;
        }
        System.out.println("-".repeat(70));
        for (int i=0; i<products.size(); i++) {
            Product product = products.get(i);
            if (product instanceof Kimbab) {
                System.out.printf((i+1)+") 메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 오이 : %s\n", product.getName(), product.getAmount(), product.getPrice() * product.getAmount(), (((Kimbab) product).isHasCucumber() ? "O" : "X"));
            } else if (product instanceof Tteokbokki) {
                System.out.printf((i+1)+") 메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 맵기 정도 : ", product.getName(), product.getAmount(), product.getPrice() * product.getAmount());
                String spicyStep = ((Tteokbokki) product).spicySepName(((Tteokbokki) product).getSpicyStep());

                System.out.println(spicyStep);
            } else {
                System.out.printf((i+1)+") 메뉴 이름 : %s 수량 : %d개 해당 메뉴 총 가격 : %d 계란 : %s\n", product.getName(), product.getAmount(), product.getPrice() * product.getAmount(), (((Ramen) product).isHasEgg() ? "O" : "X"));
            }
        }
        System.out.println("-".repeat(70));

        if(isCart) {
            System.out.print("메뉴로 돌아가고 싶으면 엔터, 장바구니에서 제거하고 싶은 상품이 있으시면 1번을 입력해주세요. ");
            String goMenu = sc.nextLine();
            if (goMenu.equals("1")) {
                System.out.print("제거하고 싶은 상품의 번호를 입력해주세요. : ");
                removeCart(Integer.parseInt(sc.nextLine()));
            } else if (goMenu.equals("")) System.out.println("메뉴로 돌아갑니다.");
            else System.out.println("잘못 입력하셨습니다. 메뉴로 돌아갑니다.");
        }

    }

    public int totalPrice() {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getAmount();
        }
        return totalPrice;
    }
}
