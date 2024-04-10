package com.codestates.seb.lol_program;
import java.util.Scanner;

/**
 * @LoL_char() : 게임정보를 담는 클래스
 * @name : 유닛의 이름
 * @ad :   유닛의 공격력
 * @def :  유닛의 방어력
 * @hp :   유닛의 체력
 * */
class LoL_char {
  //TODO:
  // 사용자의 입력을 받기위한 객체 생성을 진행합니다.

  // 게임에 필요한 변수 타입을 사전 정의합니다.

  /**
   * @user_info : 생성된 유닛의 정보를 담는 메서드
   * @this : 해당 메서드가 입력받은 변수들은 this 를
   * */
  void user_info(String name, String ad, String def, String hp) {
  }

  /**
   * @user_create() : 유닛을 생성하는 메서드이며, 입력된 값은 user_info() 메서드에게 전달 및 반환 처리합니다.
   * 반환된 값은 시스템 정보창에 출력되도록 합니다.
   * */
  String[] user_create() {
    //TODO:
    //입력 메시지와 함께, 게임에 필요한 유닛의 정보를 입력받습니다.

    // 입력된 값은 user_info()에 전달하여 줍니다.

    // 유저마다의 객체 생성을 위해 반환 과정 또한 정의하여 줍니다.
  }

  /**
   * @user_print() : 입력된 배열을 통해 유닛 정보 출력
   * */
  void user_print(String[] user) {
    //TODO:
  }

  /**
   * @user_info_int() : 공격력과 방어력 등을 고려해 체력 감소를 위해 문자열을 정수형태로 전환합니다.
   * Integer.parseInt() : 정수형태로 전환합니다.
   */
  int[] user_info_int(String[] info) {
    //TODO:
    // 같은 형식의 반환을 위해 배열 선언

    // 배열의 값을 하나씩 꺼내 정수형태로 전환합니다.

  }


  /**
   * @attack() : 공격을 수행하는 메서드
   * 아군 유닛 정보와 상대의 정보를 입력받습니다.
   * */
  void attack(int[] me_info_int, int[] enemy) {
    // 조건 1. 적군의 체력이 0 이하면 [유닛 제거] 가 됩니다.
    // 조건 2. 적군 체력이 0 이하가 아니라면 공격을 성공적으로 수행합니다.
    // 공격 정책 수식 : 적군 체력 -= 아군 유닛 공격력 / 적군 유닛 방어력
    //TODO:
}