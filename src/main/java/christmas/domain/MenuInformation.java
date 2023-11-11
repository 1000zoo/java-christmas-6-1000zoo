package christmas.domain;

import java.util.List;

public record MenuInformation(
        String name,
        Price price,
        MenuType menuType
) {

    private final static int NAME_INDEX = 0;
    private final static int PRICE_INDEX = 1;
    private final static int TYPE_INDEX = 2;

    public static MenuInformation of(List<String> information) {
        return new MenuInformation(
                information.get(NAME_INDEX),
                Price.of(information.get(PRICE_INDEX)),
                MenuType.valueOf(information.get(TYPE_INDEX))
        );
    }

    @Override
    public String toString() {
        return "MenuInformation{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", menuType=" + menuType +
                '}';
    }
}
