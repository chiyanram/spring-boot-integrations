package com.rmurugaian.spring.util;

public interface SomeUtility {
    default String SomeHandler(final String value) {
        try {
            if (null != value) {
                final long keyName = Double.valueOf((value.trim())).longValue();
                return String.valueOf(keyName);
            }
        } catch (final NumberFormatException nfe) {
        }
        return value;
    }
}