package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();


    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! -------------------//
    // 1. 커피 정보 수정을 위한 핸들러 메서드 구현
    // 2. 커피 정보 삭제를 위한 핸들러 서드 구현

    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("coffeeId") Long coffeeId,
                                     @RequestParam("korName") String korName,
                                     @RequestParam("engName") String engName,
                                     @RequestParam("price") int price
                                     ){
        Map<String, Object> coffee1 = new HashMap<>();
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", korName);
        coffee1.put("engName", engName);
        coffee1.put("price", price);

        return  new ResponseEntity<>(coffee1,HttpStatus.OK);
    }
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee (
                                        @PathVariable("coffee-id") Long coffeeId,
                                        @RequestParam("korName") String korName,
                                        @RequestParam("engName") String engName){

        coffees.get(coffeeId).put("korName", korName);
        coffees.get(coffeeId).put("engName",engName);

        return new ResponseEntity(coffees, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") Long coffeeId){
        coffees.remove(coffeeId);

        return new ResponseEntity(coffees,HttpStatus.NO_CONTENT);
    }

}
