package christmas.domain.discount;

import christmas.constant.AmountEnum;
import christmas.dto.PoliciesRequestDto;

public class WeekdayDiscountPolicy implements SpecialEventPolicy {

    private final PoliciesRequestDto policiesRequestDto;

    public WeekdayDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return AmountEnum.WEEKDAY_DISCOUNT.getAmount() * policiesRequestDto.countDessert();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
