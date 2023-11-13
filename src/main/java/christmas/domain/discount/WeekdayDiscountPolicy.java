package christmas.domain.discount;

import christmas.dto.PoliciesRequestDto;

public class WeekdayDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final PoliciesRequestDto policiesRequestDto;

    public WeekdayDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }


    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * policiesRequestDto.countDessert();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
