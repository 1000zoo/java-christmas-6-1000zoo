package christmas.vo;

import christmas.domain.MenuType;
import christmas.domain.Price;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuInformation that = (MenuInformation) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price)
                && menuType == that.menuType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, menuType);
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