package com.codestates.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class OrderCoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private int quantity;
}
