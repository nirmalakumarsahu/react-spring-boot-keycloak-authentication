package com.sahu.springboot.security.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeycloakConstants {
    REALM_ACCESS("realm_access"),
    RESOURCE_ACCESS("resource_access"),
    ROLES("roles")

    ;

    private String value;
}
