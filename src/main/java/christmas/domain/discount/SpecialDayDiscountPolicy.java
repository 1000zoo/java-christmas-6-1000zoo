package christmas.domain.discount;

import christmas.constant.DiscountAmountEnum;
import christmas.dto.PoliciesRequestDto;

public class SpecialDayDiscountPolicy implements SpecialEventPolicy {

    public SpecialDayDiscountPolicy(PoliciesRequestDto ignored) {
    }

    @Override
    public int getDiscountAmount() {
        return DiscountAmountEnum.SPECIAL_DISCOUNT.getAmount();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
