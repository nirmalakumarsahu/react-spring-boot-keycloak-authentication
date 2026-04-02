package com.sahu.springboot.security.controller.rest;

import com.sahu.springboot.security.dto.response.CarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cars")
public class CarRestController {

    @GetMapping
    public List<CarResponse> getCars() {
        log.info("Getting cars...");
        return List.of(
                CarResponse.builder()
                        .name("Tesla")
                        .color("red")
                        .price(35000000L)
                        .build(),
                CarResponse.builder()
                        .name("BMW")
                        .color("black")
                        .price(25000000L)
                        .build(),
                CarResponse.builder()
                        .name("Audi")
                        .color("white")
                        .price(30000000L)
                        .build()
        );
    }
}
