package christmas.configuration;

import christmas.vo.MenuInformation;

public enum GiveawayMenu {
    INSTANCE;

    private MenuInformation menuInformation;

    public void init(MenuInformation menuInformation) {
        if (this.menuInformation == null) {
            this.menuInformation = menuInformation;
        }
    }

    public int getPrice() {
        return menuInformation.price();
    }

    public String getMenuName() {
        return menuInformation.name();
    }
}