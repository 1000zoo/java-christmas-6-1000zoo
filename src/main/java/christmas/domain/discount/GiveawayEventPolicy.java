package christmas.domain.discount;

import christmas.domain.Orders;
import christmas.vo.MenuInformation;

public class GiveawayEventPolicy implements SpecialEventPolicy {

    private final static int THRESHOLD_AMOUNT = 120_000;

    private final MenuInformation giveawayMenu;
    private final Orders orders;

    public GiveawayEventPolicy(MenuInformation giveawayMenu, Orders orders) {
        this.giveawayMenu = giveawayMenu;
        this.orders = orders;
    }

    @Override
    public int getDiscountAmount() {
        return 0;
    }

    @Override
    public int getTotalBenefit() {
        if (orders.calculateTotalPrice() < THRESHOLD_AMOUNT) {
            return 0;
        }
        return giveawayMenu.price();
    }
}
