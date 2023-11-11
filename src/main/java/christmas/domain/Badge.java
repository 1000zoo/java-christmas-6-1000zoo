package christmas.domain;

import java.util.Arrays;

public enum Badge {
    NONE(0, "없음"),
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private final int minimumAmount;
    private final String name;

    Badge(int minimumAmount, String name) {
        this.minimumAmount = minimumAmount;
        this.name = name;
    }

    public static Badge of(int amount) {
        return Arrays.stream(values())
                .filter(badge -> amount >= badge.minimumAmount)
                .reduce((first, second) -> second)
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
