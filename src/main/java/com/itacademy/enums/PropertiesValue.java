package com.itacademy.enums;

public enum PropertiesValue {
    BROWSER("browser", "chrome");

    private String key;
    private String defaultValue;

    PropertiesValue(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
