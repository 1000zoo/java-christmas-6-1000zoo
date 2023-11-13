package christmas.domain.discount;

import christmas.constant.AmountEnum;
import christmas.dto.PoliciesRequestDto;

public class WeekendDiscountPolicy implements SpecialEventPolicy {

    private final PoliciesRequestDto policiesRequestDto;

    public WeekendDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return AmountEnum.WEEKEND_DISCOUNT.getAmount() * policiesRequestDto.countMain();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
