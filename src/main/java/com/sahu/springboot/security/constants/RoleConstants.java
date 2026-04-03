package com.sahu.springboot.security.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleConstants {
    ROLE_PREFIX("ROLE_"),
    VIEW_CARS("view-cars")

    ;
    private final String value;
}
