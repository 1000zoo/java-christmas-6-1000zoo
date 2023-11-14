package christmas.domain.discount;

import christmas.constant.DiscountAmountEnum;
import christmas.dto.PoliciesRequestDto;

public class WeekdayDiscountPolicy implements SpecialEventPolicy {

    private final PoliciesRequestDto policiesRequestDto;

    public WeekdayDiscountPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return DiscountAmountEnum.WEEKDAY_DISCOUNT.getAmount() * policiesRequestDto.countDessert();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
