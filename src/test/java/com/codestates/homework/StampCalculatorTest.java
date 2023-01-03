package com.codestates.homework;

import com.codestates.helper.StampCalculator;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StampCalculatorTest {
    @Test
    @DisplayName("실습1: 스탬프 카운트 계산 단위 테스트")
    public void calculateStampCountTest() {
        //given
        int nowCount = 10;
        int earned = 5;


        //when
        int actual = StampCalculator.calculateStampCount(nowCount, earned);
        int expected = 15;


        //then
        assertEquals(actual, expected);
        org.assertj.core.api.Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("실습1: 주문 후 누적 스탬프 카운트 계산 단위 테스트")
    public void calculateEarnedStampCountTest(){
        // TODO 여기에 테스트 케이스를 작성해주세요.
        //given
        Order order = new Order();
        OrderCoffee sample1 = new OrderCoffee();
        sample1.setQuantity(10);
        OrderCoffee sample2 = new OrderCoffee();
        sample2.setQuantity(20);

        order.setOrderCoffees(List.of(sample1, sample2));



        //when

        int actual = order.getOrderCoffees().stream().mapToInt(orderCoffee -> orderCoffee.getQuantity()).sum();
        int expected = sample1.getQuantity() + sample2.getQuantity();



        //then
        assertEquals(actual,expected);

    }
}
