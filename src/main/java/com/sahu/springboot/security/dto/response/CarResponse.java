package com.sahu.springboot.security.dto.response;

import lombok.Builder;

@Builder
public record CarResponse(
        String name,
        String color,
        Long price
) {
}
