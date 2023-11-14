package christmas.constant;

public enum IndicesEnum {
    NAME(0),
    PRICE(1),
    TYPE(2),
    MENU(0),
    QUANTITY(1);

    private final int index;

    IndicesEnum(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
