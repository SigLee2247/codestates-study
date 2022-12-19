package com.codestates.coffee.controller;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.coffee.mapper.CoffeeMapper;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v5/coffees")
@Validated
@AllArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper cm;

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        // TODO CoffeeService 클래스와 연동하세요.
        // TODO DTO <-> Entity 변환 Mapper를 적용하세요.

        Coffee coffee = cm.coffeePostDtoToCoffee(coffeePostDto);

        CoffeeResponseDto response = cm.coffeeToCoffeeResponse(coffeeService.createCoffee(coffee));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);

        // TODO CoffeeService 클래스와 연동하세요.
        // TODO DTO <-> Entity 변환 Mapper를 적용하세요.
        Coffee coffee = cm.coffeePatchDtoToCoffee(coffeePatchDto);

        CoffeeResponseDto response = cm.coffeeToCoffeeResponse(coffeeService.updatedCoffee(coffee));




        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        // TODO CoffeeService 클래스와 연동하세요.
        // TODO DTO <-> Entity 변환 Mapper를 적용하세요.

        Coffee coffee = coffeeService.findCoffee(coffeeId);

        CoffeeResponseDto response = cm.coffeeToCoffeeResponse(coffee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        // TODO CoffeeService 클래스와 연동하세요.
        // TODO DTO <-> Entity 변환 Mapper를 적용하세요.
        List<Coffee> coffees = coffeeService.findCoffees();

        List<CoffeeResponseDto> response =
                coffees.stream().map(coffee -> cm.coffeeToCoffeeResponse(coffee))
                        .collect(Collectors.toList());


        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // TODO CoffeeService 클래스와 연동하세요.
        // TODO DTO <-> Entity 변환 Mapper를 적용하세요.

        coffeeService.deleteCoffee(coffeeId);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
