package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * <h3>DTO 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeeController는 실습 과제에 대한 두 개의 Solution 메서드(postCoffee(), patchCoffee())등을 포함하고 있습니다.
 * CoffeeController는 의존하는 서비스 계층의 클래스는 존재하지 않으며, 따라서 구체적인 구현 로직을 포함하고 있지 않습니다.
 *
 * @author  황정식
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1/coffees")
@Validated
public class CoffeeController {
    /**
     * 커피 정보 등록을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>@RequestParam을 이용해 각각의 파라미터를 전달 받는 대신에 DTO 클래스를 이용해 한번에 전달 받습니다.</li>
     * </ul>
     * @param coffeePostDto 커피 정보 등록을 위해 클라이언트 측으로부터 전달 받은 request body에 매핑되는 DTO 클래스
     * @return  클라이언트 측에 전송하는 response body용 커피 정보가 포함된 ResponseEntity
     *          <p>
     *          서비스 계층과의 연동이 없으므로 편의상 request body로 전달받은 coffeePostDto를 그대로 되돌려 줍니다.
     */
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    /**
     * 커피 정보 수정을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *    <li>@RequestParam을 이용해 각각의 파라미터를 전달 받는 대신에 DTO 클래스를 이용해 한번에 전달 받습니다.</li>
     * </ul>
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자.
     *                  커피 정보 수정의 대상이 됩니다.
     *
     * @param coffeePatchDto 커피 정보 수정을 위해 클라이언트 측으로부터 전달 받은 request body에 매핑되는 DTO 클래스.
     *
     * @return 클라이언트 측에 전송하는 response body용 커피 정보가 포함된 ResponseEntity.
     *         서비스 계층과의 연동이 없으므로 편의상 request body로 전달받은 coffeePatchDto를 그대로 되돌려 줍니다.
     *
     */
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // No need business logic

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
