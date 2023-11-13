package christmas.domain.discount;

import christmas.dto.PoliciesRequestDto;

public class WeekendDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final PoliciesRequestDto policiesRequestDto;

    public WeekendDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * policiesRequestDto.countMain();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
