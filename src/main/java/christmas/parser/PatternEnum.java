package christmas.parser;

public enum PatternEnum {
    INTEGER("^\\d+$"),
    ORDER("(\\p{IsHangul}+)-(\\d+)"),
    ORDERS("^(\\p{IsHangul}+-\\d+)(,\\p{IsHangul}+-\\d+)*$");

    private final String pattern;

    PatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public boolean matches(String input) {
        return input.matches(pattern);
    }
}
