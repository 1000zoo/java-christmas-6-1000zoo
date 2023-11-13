package christmas.domain.discount;

import christmas.dto.BenefitResultDto;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class EventPolicies {

    private final Map<EventPolicyType, SpecialEventPolicy> policies;

    private EventPolicies(Map<EventPolicyType, SpecialEventPolicy> policies) {
        this.policies = Collections.unmodifiableMap(policies);
    }

    public static EventPolicies from(Map<EventPolicyType, SpecialEventPolicy> policies) {
        return new EventPolicies(policies);
    }

    public int getDiscountAmount() {
        return policies.values().stream()
                .mapToInt(SpecialEventPolicy::getDiscountAmount)
                .sum();
    }

    public int getTotalBenefit() {
        return policies.values().stream()
                .mapToInt(SpecialEventPolicy::getTotalBenefit)
                .sum();
    }

    public BenefitResultDto createBenefitResultDto() {
        Map<EventPolicyType, Integer> benefitResults = new EnumMap<>(EventPolicyType.class);
        policies.keySet().forEach(event -> benefitResults.put(event, policies.get(event).getTotalBenefit()));
        return new BenefitResultDto(benefitResults);
    }
}
