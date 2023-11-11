package christmas.domain;

public enum MenuType {
    APPETIZER, MAIN, DESSERT, DRINK;

    public boolean isFood() {
        return this != DRINK;
    }
}
