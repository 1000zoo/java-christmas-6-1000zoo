package christmas.domain.discount;

import christmas.dto.PoliciesRequestDto;

public class GiveawayEventPolicy implements SpecialEventPolicy {

    private final PoliciesRequestDto policiesRequestDto;

    public GiveawayEventPolicy(PoliciesRequestDto policiesRequestDto) {
        this.policiesRequestDto = policiesRequestDto;
    }

    @Override
    public int getDiscountAmount() {
        return 0;
    }

    @Override
    public int getTotalBenefit() {
        return policiesRequestDto.giveawayMenuPrice();
    }
}
