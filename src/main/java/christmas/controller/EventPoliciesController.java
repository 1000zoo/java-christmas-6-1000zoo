package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.discount.DDayDiscountPolicy;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.EventPolicyType;
import christmas.domain.discount.GiveawayEventPolicy;
import christmas.domain.discount.SpecialDayDiscountPolicy;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import christmas.dto.PoliciesRequestDto;
import christmas.vo.MenuInformation;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class EventPoliciesController {

    private final static int DOESNT_EXIST = 0;
    private final static int MIN_AMOUNT_FOR_DISCOUNT = 10_000;
    private final static int MIN_AMOUNT_FOR_GIVEAWAY = 120_000;

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
        if (customer.calculateTotalConsumption() >= MIN_AMOUNT_FOR_DISCOUNT) {
            addPolicy(EventPolicyType.DDAY, this::canAddDDayDiscountPolicy, DDayDiscountPolicy::new);
            addPolicy(EventPolicyType.WEEKDAY, this::canAddWeekdayDiscountPolicy, WeekdayDiscountPolicy::new);
            addPolicy(EventPolicyType.WEEKEND, this::canAddWeekendDiscountPolicy, WeekendDiscountPolicy::new);
            addPolicy(EventPolicyType.SPECIAL_DAY, this::canAddSpecialDayDiscountPolicy, SpecialDayDiscountPolicy::new);
            addPolicy(EventPolicyType.GIVEAWAY, this::canAddGiveawayEventPolicy, GiveawayEventPolicy::new);
        }
        return EventPolicies.from(policyList);
    }

    private void addPolicy(EventPolicyType eventPolicyType,
                           BooleanSupplier supplier,
                           Function<PoliciesRequestDto, SpecialEventPolicy> constructor
    ) {
        if (supplier.getAsBoolean()) {
            policyList.put(eventPolicyType, constructor.apply(policiesRequestDto));
        }
    }

    private boolean canAddDDayDiscountPolicy() {
        return customer.orderBeforeChristmas();
    }

    private boolean canAddWeekdayDiscountPolicy() {
        return customer.orderAtWeekday() && customer.countDessert() > DOESNT_EXIST;
    }

    private boolean canAddWeekendDiscountPolicy() {
        return customer.orderAtWeekend() && customer.countMain() > DOESNT_EXIST;
    }

    private boolean canAddSpecialDayDiscountPolicy() {
        return customer.orderAtSpecialDay();
    }

    private boolean canAddGiveawayEventPolicy() {
        return customer.calculateTotalPrice() >= MIN_AMOUNT_FOR_GIVEAWAY;
    }

}
