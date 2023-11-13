package christmas.domain.discount;

import christmas.dto.PoliciesRequestDto;

public class DDayDiscountPolicy implements SpecialEventPolicy {

    private final static int START_DISCOUNT_AMOUNT = 900;
    private final static int INCREASE_AMOUNT = 100;

    private final PoliciesRequestDto policiesRequestDto;

    public DDayDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return START_DISCOUNT_AMOUNT + INCREASE_AMOUNT * policiesRequestDto.day();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
