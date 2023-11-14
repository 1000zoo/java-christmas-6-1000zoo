package christmas.controller;

import christmas.domain.OrderHistory;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.EventPolicyType;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.dto.PoliciesRequestDto;
import java.util.EnumMap;
import java.util.Map;

public class EventPoliciesController {

    private final OrderHistory orderHistory;
    private final PoliciesRequestDto policiesRequestDto;
    private final Map<EventPolicyType, SpecialEventPolicy> policyList;

    public EventPoliciesController(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
        policiesRequestDto = new PoliciesRequestDto(
                orderHistory.countDessert(),
                orderHistory.countMain(),
                orderHistory.getDay()
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
        if (eventPolicyType.canAddEvent(orderHistory)) {
            policyList.put(eventPolicyType, eventPolicyType.from(policiesRequestDto));
        }
    }
}
