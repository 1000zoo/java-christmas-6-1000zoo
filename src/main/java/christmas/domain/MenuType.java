package christmas.domain;

public enum MenuType {
    APPETIZER, MAIN, DESSERT, DRINK;

    public boolean isFood() {
        return this != DRINK;
    }

    public boolean isDessert() {
        return this == DESSERT;
    }

    public boolean isMain() {
        return this == MAIN;
    }
}
