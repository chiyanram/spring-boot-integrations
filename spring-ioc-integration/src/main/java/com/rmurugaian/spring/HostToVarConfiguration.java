package com.rmurugaian.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;

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
