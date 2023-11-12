package christmas.domain.discount;

import christmas.vo.MenuInformation;

public class GiveawayEventPolicy implements SpecialEventPolicy {

    private final MenuInformation giveawayMenu;

    public GiveawayEventPolicy(MenuInformation giveawayMenu) {
        this.giveawayMenu = giveawayMenu;
    }

    @Override
    public int getDiscountAmount() {
        return 0;
    }

    @Override
    public int getTotalBenefit() {
        return giveawayMenu.price();
    }
}
