package com.codestates.seb.kiosk_program;

import com.codestates.seb.kiosk_program.product.ProductRepository;

import java.util.Scanner;

// Kiosk_Program 클래스를 정의 합니다.
public class Kiosk_Program {
    //TODO:
    // 메뉴 입력 및 사용자의 수량을 입력하기 위해 Scanner 객체 생성
    static Scanner sc = new Scanner(System.in);
    private int totalCost = 0;
    private Menu menu;
    private ProductRepository productRepository;

    private Cart cart;
    private Order order;

    public Kiosk_Program(Menu menu, ProductRepository productRepository, Cart cart, Order order) {
        this.menu = menu;
        this.productRepository = productRepository;
        this.cart = cart;
        this.order = order;
    }

    //TODO:

    /**
     * 프로그램의 시작을 알리는 메서드입니다.
     *
     * @.repeat() 메서드는 해당 문자를 반복해서 출력해주는 기능을 갖습니다.
     */
    private void input_print() {
        //TODO:
        System.out.println("안녕하세요 오월의 김밥입니다.");
        System.out.println("*".repeat(30));
    }

    /**
     * 사용자가 원하는 메뉴를 번호로 입력받습니다.
     *
     * @재귀함수 : 본 메서드가 동작할 때 메뉴에 없는 번호가 입력되면 자기 자신이 다시 실행되도록 합니다.
     * 해당 count() 메소드는 기능이 모두 구현되어 있습니다. Scanner 객체의 변수명만 확인해야 합니다.
     */
    private void count() {

        int price = 0;

        do {
            // 안내 문구
            menu.printMenu();
            // 번호 입력창
            String selectMenu = sc.nextLine();
            if(selectMenu.equals("+"))
                break;
            if(selectMenu.equals("-")) {
                productRepository.addProduct();
                continue;
            }
            int number = Integer.parseInt(selectMenu);

            if (number > 0) {
                cart.addCart(number);
            } else if (number == 0){
                cart.printAllCartProduct(true);
            } else {
                break;
            }

        } while (1 != 0);

        order.makeOrder();
    }

    /**
     * @main() 메서드 동작
     * 프로그램 실행 -> 메뉴 입력 -> 수량 입력 및 가격 계산 -> 가격 출력
     */

    public void start() {
        input_print();
        count();
        System.out.println("이용해 주셔서 감사합니다.");
    }
}
