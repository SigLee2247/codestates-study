package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.order.dto.*;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Logger log = LoggerFactory.getLogger(getClass());

        Order order = new Order();
        Member member = new Member();

        member.setMemberId(orderPostDto.getMemberId());

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.setOrder(order);
                    orderCoffee.setCoffee(coffee);
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.addMember(member);
        order.setOrderCoffees(orderCoffees);

        return order;
    }


    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
//    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
    //note  Order -> OrderResponseDto 로 하나하나 매핑해준다.




    @Mapping(source = "member.memberId",target="memberId")
    OrderResponseDto orderToOrderResponseDto(Order order);
    @Mapping(source = "coffee.coffeeId", target="coffeeId")
    @Mapping(source = "coffee.korName", target="korName")
    @Mapping(source = "coffee.engName", target="engName")
    @Mapping(source = "coffee.price", target="price")
    OrderCoffeeResponseDto orderCoffeeToOrderCoffeeResponseDto(OrderCoffee orderCoffee);

}
