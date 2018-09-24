package org.winwin.lib;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

public class Environment {

    static Map<String, String> env = System.getenv();

    public static Optional<String> getEnvironmentVariable(String key) {
        String value = env.get(key);
        if(StringUtils.isEmpty(value)) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }
}
