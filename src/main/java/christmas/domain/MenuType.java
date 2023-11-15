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

    public static MenuType of(String menuTypeString) {
        try {
            return valueOf(menuTypeString);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("없는 메뉴 카테고리");
        }
    }
}
