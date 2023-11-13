package christmas.controller;

import christmas.constant.AmountEnum;
import christmas.constant.QuantityEnum;
import christmas.domain.Customer;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.EventPolicyType;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.dto.PoliciesRequestDto;
import christmas.vo.MenuInformation;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class EventPoliciesController {

    private final Customer customer;
    private final PoliciesRequestDto policiesRequestDto;
    private final Map<EventPolicyType, SpecialEventPolicy> policyList;

    public EventPoliciesController(Customer customer, MenuInformation giveawayMenu) {
        this.customer = customer;
        policiesRequestDto = new PoliciesRequestDto(
                customer.countDessert(),
                customer.countMain(),
                customer.getDay(),
                giveawayMenu.price()
        );
        policyList = new EnumMap<>(EventPolicyType.class);
    }

    public EventPolicies createEventPolicies() {
        if (isEventApplied()) {
            addPolicy(EventPolicyType.DDAY, this::canAddDDayDiscountPolicy);
            addPolicy(EventPolicyType.WEEKDAY, this::canAddWeekdayDiscountPolicy);
            addPolicy(EventPolicyType.WEEKEND, this::canAddWeekendDiscountPolicy);
            addPolicy(EventPolicyType.SPECIAL_DAY, this::canAddSpecialDayDiscountPolicy);
            addPolicy(EventPolicyType.GIVEAWAY, this::canAddGiveawayEventPolicy);
        }
        return EventPolicies.from(policyList);
    }

    private boolean isEventApplied() {
        return customer.calculateTotalPrice() >= AmountEnum.MIN_BOUNDARY_FOR_EVENT.getAmount();
    }

    private void addPolicy(EventPolicyType eventPolicyType, BooleanSupplier supplier) {
        if (supplier.getAsBoolean()) {
            policyList.put(eventPolicyType, eventPolicyType.from(policiesRequestDto));
        }
    }

    private boolean canAddDDayDiscountPolicy() {
        return customer.orderBeforeChristmas();
    }

    private boolean canAddWeekdayDiscountPolicy() {
        return customer.orderAtWeekday() && customer.countDessert() > QuantityEnum.MIN.getQuantity();
    }

    private boolean canAddWeekendDiscountPolicy() {
        return customer.orderAtWeekend() && customer.countMain() > QuantityEnum.MIN.getQuantity();
    }

    private boolean canAddSpecialDayDiscountPolicy() {
        return customer.orderAtSpecialDay();
    }

    private boolean canAddGiveawayEventPolicy() {
        return customer.calculateTotalPrice() >= AmountEnum.MIN_BOUNDARY_FOR_GIVEAWAY.getAmount();
    }
}
