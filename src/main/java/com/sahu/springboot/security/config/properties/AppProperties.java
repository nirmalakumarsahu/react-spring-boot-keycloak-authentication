package com.sahu.springboot.security.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String oauth2ClientName;
    private Cors cors;

    @Setter
    @Getter
    public static class Cors {
        private List<String> urls;
        private List<String> methods;
        private List<String> headers;
    }
}
