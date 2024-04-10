package com.codestates.order.service;

import com.codestates.coffee.service.CoffeeService;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.service.MemberService;
import com.codestates.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public Order createOrder(Order order) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Order findOrder(long orderId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Page<Order> findOrders(int page, int size) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public void cancelOrder(long orderId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    private Order findVerifiedOrder(long orderId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }
}
