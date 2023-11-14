package christmas.domain.discount;

import christmas.constant.DiscountAmountEnum;
import christmas.dto.PoliciesRequestDto;

public class WeekendDiscountPolicy implements SpecialEventPolicy {

    private final PoliciesRequestDto policiesRequestDto;

    public WeekendDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return DiscountAmountEnum.WEEKEND_DISCOUNT.getAmount() * policiesRequestDto.countMain();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
