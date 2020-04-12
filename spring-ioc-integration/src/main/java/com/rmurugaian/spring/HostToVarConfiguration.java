package com.rmurugaian.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConstructorBinding
@ConfigurationProperties("host-var")
public class HostToVarConfiguration {

    private final Map<String, String> map;

    public HostToVarConfiguration(final Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
