package christmas.vo;

import christmas.constant.IndicesEnum;
import christmas.domain.MenuType;
import java.util.List;
import java.util.Objects;

public record MenuInformation(String name, int price, MenuType menuType) {

    public static MenuInformation of(List<String> information) {
        return new MenuInformation(
                information.get(IndicesEnum.NAME.index()),
                Integer.parseInt(information.get(IndicesEnum.PRICE.index())),
                MenuType.valueOf(information.get(IndicesEnum.TYPE.index()))
        );
    }

    public boolean isFood() {
        return menuType.isFood();
    }

    public boolean isDessert() {
        return menuType.isDessert();
    }

    public boolean isMain() {
        return menuType.isMain();
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
}
