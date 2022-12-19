package com.codestates.coffee.entity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private Long coffeeId;
    private String korName;
    private String engName;
    private int price;

}
