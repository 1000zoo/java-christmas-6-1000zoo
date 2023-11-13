package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.EventPolicyType;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.dto.PoliciesRequestDto;
import java.util.EnumMap;
import java.util.Map;

public class EventPoliciesController {

    private final Customer customer;
    private final PoliciesRequestDto policiesRequestDto;
    private final Map<EventPolicyType, SpecialEventPolicy> policyList;

    public EventPoliciesController(Customer customer) {
        this.customer = customer;
        policiesRequestDto = new PoliciesRequestDto(
                customer.countDessert(),
                customer.countMain(),
                customer.getDay()
        );
        policyList = new EnumMap<>(EventPolicyType.class);
    }

    public EventPolicies createEventPolicies() {
        for (EventPolicyType eventPolicyType : EventPolicyType.values()) {
            addPolicy(eventPolicyType);
        }
        return EventPolicies.from(policyList);
    }

    private void addPolicy(EventPolicyType eventPolicyType) {
        if (eventPolicyType.canAddEvent(customer)) {
            policyList.put(eventPolicyType, eventPolicyType.from(policiesRequestDto));
        }
    }
}
