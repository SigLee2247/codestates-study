package com.codestates.seb.calculator;
import java.util.Scanner;





public class Calculator {
  public static void main(String[] args) {
    System.out.println("===Java Calculator===");

    Scanner input = new Scanner(System.in);
        /*
            요구 사항에 따라 간단한 계산기를 만들어주세요.
            1. 사용자의 입력으로 첫 번째 숫자, 연산자, 두 번째 숫자를 받아야 합니다.
            2. 연산자의 종류는 +, -, *, / 입니다.
            3. 소수점 연산을 수행할 수 있어야 합니다.
            4. 연산 결과를 콘솔에 출력합니다.
        */
    // TODO...
    System.out.println("숫자 연산자 숫자 순으로 입력해주세요");
    double inputLeft = input.nextDouble();
    String inputOperator = input.next();
    double inputRight = input.nextDouble();

    Cal a = new Cal();    //인스턴스를 생성해 매개변수 a에 해당 주소를 저장한다. >>>>>이를 통해 Cal 클래스의 모든 내용을 사용할 수 있음
    a.setOprands(inputLeft,inputOperator,inputRight);
    }



  }



 class Cal {
   double left, right;

   void setOprands(double left, String operator, double right) {
     this.left = left;
     this.right = right;
     switch (operator) {
       case "+" :
         add(); break;
       case "-" :
         sub(); break;
       case "*" :
         multi(); break;
       case "/" :
         divide(); break;
       default:
         System.out.println("시스템을 출력할 수없습니다.");
     }
   }

   private void multi() {
     System.out.println(this.left + " * " + this.right + " = " + this.left * this.right);
   }


   private void divide() {
     if (this.right == 0)
       System.out.println("0으로 나눌 수 없습니다.");
     else
       System.out.println(this.left + " / " + this.right + " = " + this.left / this.right);
   }


   private void add() {
     System.out.println(this.left + " + " + this.right + " = " + (this.left + this.right));

   }

   private void sub() {
     System.out.println(this.left + " - " + this.right + " = " + (this.left - this.right));
   }
 }









