package christmas.vo;

import christmas.constant.IndicesEnum;
import christmas.domain.MenuType;
import java.util.List;

public record MenuInformation(String name, int price, MenuType menuType) {

    public static MenuInformation of(List<String> information) {
        return new MenuInformation(
                information.get(IndicesEnum.NAME.index()),
                Integer.parseInt(information.get(IndicesEnum.PRICE.index())),
                MenuType.of(information.get(IndicesEnum.TYPE.index()))
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
}
