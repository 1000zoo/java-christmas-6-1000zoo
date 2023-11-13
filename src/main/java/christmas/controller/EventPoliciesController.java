package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.dto.PoliciesRequestDto;
import christmas.vo.MenuInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class EventPoliciesController {

    private final static int DOESNT_EXIST = 0;
    private final static int MIN_AMOUNT_FOR_DISCOUNT = 10_000;
    private final static int MIN_AMOUNT_FOR_GIVEAWAY = 120_000;

    private final Customer customer;
    private final PoliciesRequestDto policiesRequestDto;
    private final List<SpecialEventPolicy> policyList;

    public EventPoliciesController(Customer customer, MenuInformation giveawayMenu) {
        this.customer = customer;
        policiesRequestDto = new PoliciesRequestDto(
                customer.countDessert(),
                customer.countMain(),
                customer.getDay(),
                giveawayMenu.price()
        );
        policyList = new ArrayList<>();
    }

    public EventPolicies createEventPolicies() {
        if (customer.calculateTotalConsumption() >= MIN_AMOUNT_FOR_DISCOUNT) {
            addDDayDiscountPolicy();
            addWeekdayDiscountPolicy();
            addWeekendDiscountPolicy();
            addDiscountAmount();
            addGiveawayEventPolicy();
        }
        return EventPolicies.from(policyList);
    }

    private void addPolicy(BooleanSupplier supplier, Function<PoliciesRequestDto, SpecialEventPolicy> constructor) {
        if (supplier.getAsBoolean()) {
            policyList.add(constructor.apply(policiesRequestDto));
        }
    }

    private boolean canAddDDayDiscountPolicy() {
        return customer.orderBeforeChristmas();
    }

    private void addDDayDiscountPolicy() {
        if (customer.orderBeforeChristmas()) {
//            policyList.add(new DDayDiscountPolicy(customer.getDay()));
        }
    }

    private boolean canAddWeekdayDiscountPolicy() {
        return customer.orderAtWeekday() && customer.countDessert() > DOESNT_EXIST;
    }

    private void addWeekdayDiscountPolicy() {
        if (customer.orderAtWeekday() && customer.countDessert() > DOESNT_EXIST) {
//            policyList.add(new WeekdayDiscountPolicy(customer.countDessert()));
        }
    }

    private boolean canAddWeekendDiscountPolicy() {
        return customer.orderAtWeekend() && customer.countMain() > DOESNT_EXIST;
    }

    private void addWeekendDiscountPolicy() {
        if (customer.orderAtWeekend() && customer.countMain() > DOESNT_EXIST) {
//            policyList.add(new WeekendDiscountPolicy(customer.countMain()));
        }
    }

    private boolean canAddSpecialDayDiscountPolicy() {
        return customer.orderAtSpecialDay();
    }

    private void addDiscountAmount() {
        if (customer.orderAtSpecialDay()) {
//            policyList.add(new SpecialDayDiscountPolicy());
        }
    }

    private boolean canAddGiveawayEventPolicy() {
        return customer.calculateTotalPrice() >= MIN_AMOUNT_FOR_GIVEAWAY;
    }

    private void addGiveawayEventPolicy() {
        if (customer.calculateTotalPrice() >= MIN_AMOUNT_FOR_GIVEAWAY) {
//            policyList.add(new GiveawayEventPolicy(giveawayMenu));
        }
    }
}
