package com.codestates.seb.kiosk_program;
import java.util.Scanner;

// Kiosk_Program 클래스를 정의 합니다.
public class Kiosk_Program {
  //TODO:
  // 메뉴 입력 및 사용자의 수량을 입력하기 위해 Scanner 객체 생성
  static Scanner sc = new Scanner(System.in);
  static int totalCost = 0;

  /**
   * @menu_n_cost : 메뉴의 가격을 정의 합니다.
   * 메뉴의 가격은 변하지 않습니다.
   * 해당 기능은 자바독(javadoc) 기능입니다.
   */
  //TODO:
  final static int menu_1_cost = 1000; // 김밥
  final static int menu_2_cost = 1500; // 계란 김밥
  final static int menu_3_cost = 1000; // 충무 김밥
  final static int menu_4_cost = 2000; // 떡볶이

  /**
   * 프로그램의 시작을 알리는 메서드입니다.
   * @.repeat() 메서드는 해당 문자를 반복해서 출력해주는 기능을 갖습니다.
   */
  static void input_print() {
    //TODO:
    System.out.println("안녕하세요 오월의 김밥입니다.");
    System.out.println("*".repeat(30));
  }

  /**
   * 사용자가 원하는 메뉴를 번호로 입력받습니다.
   * @재귀함수 : 본 메서드가 동작할 때 메뉴에 없는 번호가 입력되면 자기 자신이 다시 실행되도록 합니다.
   * 해당 count() 메소드는 기능이 모두 구현되어 있습니다. Scanner 객체의 변수명만 확인해야 합니다.
   */
  static int count() {

    int price;

    do {
      // 안내 문구
      System.out.println("[안내]원하시는 메뉴의 번호를 입력하여 주세요. 주문을 끝내시려면 0번을 눌러주세요.");
      System.out.println("1) 김밥(1000원) 2) 계란 김밥(1500원) 3) 충무 김밥(1000원) 4) 떡볶이(2000원)");
      
      // 번호 입력창
      int number = sc.nextInt();

      // 번호가 0이 아닌 경우
        // 0보다 작거나 4번을 초과하는 경우 -> 경고 문구와 함께 메서드 재실행
        // 1-4번 사
      if (!(number == 0)) {

        if (number < 0 || number > 4) {
          // [경고] 문구를 출력합니다.
          System.out.println("[경고]메뉴에 포함된 번호를 입력하여 주세요.\n");
          // 그리고 다시 본 함수가 동작하여 다시 메뉴의 번호를 입력하도록 합니다.
          count();
        } else {

          switch (number) {
            case 1:
              System.out.println("111");
              price = menu_1_cost;
              totalCost = totalCost + cost(price);
              break;
            case 2:
              price = menu_2_cost;
              totalCost = totalCost + cost(price);
              break;
            case 3:
              price = menu_3_cost;
              totalCost = totalCost + cost(price);
              break;
            case 4:
              price = menu_4_cost;
              totalCost = totalCost + cost(price);
          }
        }
      } else {
        break;
      }

    } while (1 != 0);

    return totalCost;
  }

  /**
   * 수량 입력 및 가격 연산을 진행하는 메서드를 정의합니다.
   * @수량 : 수량은 1 ~ 99 까지만 입력 받을 수 있도록 합니다.
   */
  static int cost(int price) {
    // 안내 문구를 출력합니다.
    //TODO:
    System.out.println("메뉴의 수량을 입력해주세요. 최대 주문 가능 수량은 99개입니다.");

    // 입력받은 수량을 변수에 저장합니다.
    //TODO:
    int count = sc.nextInt();
    int result = 0;

    // 조건에 따라 문구 출력, 재입력을 진행합니다.
    // 조건은 다음과 같습니다.
    // 0 이하 || 99 초과
    // 두 조건 중 하나라도 참인경우 경고 문구 출력과 함께 다시 수량을 입력받을 수 있도록 합니다.
    //TODO:
    if(count <= 0 || count > 99) {
      System.out.println("잘못된 수량을 입력하셨습니다. 다시 입력해주세요.");
      cost(price);
    } else  {
      result = price * count;
    }
    return result;
  }

  /**
   * 최종 가격과 종료 문구를 출력하는 메서드 입니다.
   */
  static void output_print(int result) {
    //TODO:
    System.out.println("고객님이 주문하신 메뉴의 총 금액은 " + result + "입니다.");
  }

  /**
   * @main() 메서드 동작
   * 프로그램 실행 -> 메뉴 입력 -> 수량 입력 및 가격 계산 -> 가격 출력
   */
  public static void main(String[] args) {
    //TODO:
    input_print();

    int totalCost = count();
    output_print(totalCost);
  }
}
