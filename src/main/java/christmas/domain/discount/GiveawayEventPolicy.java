package christmas.domain.discount;

import christmas.configuration.GiveawayMenu;
import christmas.dto.PoliciesRequestDto;

public class GiveawayEventPolicy implements SpecialEventPolicy {

    public GiveawayEventPolicy(PoliciesRequestDto ignored) {
    }

    @Override
    public int getDiscountAmount() {
        return 0;
    }

    @Override
    public int getTotalBenefit() {
        return GiveawayMenu.INSTANCE.getPrice();
    }
}
