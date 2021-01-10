package com.example.wordbook.domain.wordbook.enums;

public enum  WordBookType {
    USER("USER"), GROUP("GROUP");

    private final String type;

    WordBookType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
