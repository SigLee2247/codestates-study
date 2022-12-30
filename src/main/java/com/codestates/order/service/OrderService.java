package com.codestates.order.service;

import com.codestates.coffee.repository.CoffeeRepository;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import com.codestates.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final CoffeeService coffeeService;

    private final MemberRepository memberRepository;

    public Order createOrder(Order order) {
        // 회원이 존재하는지 확인
        Member member = memberService.findVerifiedMember(order.getMember().getMemberId());

        log.info("Order = {}",order);


        //검증 시작
        order.getOrderCoffees()
                .stream()
                .forEach(orderCoffee ->
                        coffeeService
                                .verifyExistCoffee(
                                        orderCoffee.getCoffee().getCoffeeCode()));//검증 확인 용
        //검증
        //스탬프 증가
        int orderedCoffeeCount = order.getOrderCoffees().stream().mapToInt(OrderCoffee::getQuantity).sum();

        member.getStamp().setStampCount(member.getStamp().getStampCount() + orderedCoffeeCount);

        order.addMember(member);


        log.info("스템프 값 추가 후의 Order = {}",order);

        return orderRepository.save(order);
    }

    // 메서드 추가



    public Order updateOrder(Order order) {
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus(orderStatus));
        findOrder.setModifiedAt(LocalDateTime.now());
        return orderRepository.save(findOrder);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        // OrderStatus의 step이 2 이상일 경우(ORDER_CONFIRM)에는 주문 취소가 되지 않도록한다.
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        findOrder.setModifiedAt(LocalDateTime.now());
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
