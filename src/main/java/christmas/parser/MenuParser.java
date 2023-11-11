package christmas.parser;

import christmas.domain.Menu;
import christmas.vo.MenuInformation;

public class MenuParser implements Parser<String, MenuInformation> {

    private final Menu menu;

    public MenuParser(Menu menu) {
        this.menu = menu;
    }

    @Override
    public MenuInformation parse(String menuName) {
        return menu.getInformationOf(menuName);
    }
}
