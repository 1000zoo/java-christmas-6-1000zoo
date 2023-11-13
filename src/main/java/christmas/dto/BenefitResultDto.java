package christmas.dto;

import christmas.domain.discount.EventPolicyType;
import java.util.Map;

public record BenefitResultDto(Map<EventPolicyType, Integer> benefitResults) {

    public boolean containsGiveaway() {
        return benefitResults.containsKey(EventPolicyType.GIVEAWAY);
    }

    public int getBenefitOf(EventPolicyType eventPolicyType) {
        if (!benefitResults.containsKey(eventPolicyType)) {
            return 0;
        }
        return benefitResults.get(eventPolicyType);
    }
}
