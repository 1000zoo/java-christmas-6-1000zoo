package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.discount.DDayDiscountPolicy;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.GiveawayEventPolicy;
import christmas.domain.discount.SpecialDayDiscountPolicy;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import christmas.vo.MenuInformation;
import java.util.ArrayList;
import java.util.List;

public class EventPoliciesController {

    private final static int MIN_AMOUNT_FOR_DISCOUNT = 10_000;
    private final static int MIN_AMOUNT_FOR_GIVEAWAY = 120_000;

    private final Customer customer;
    private final MenuInformation giveawayMenu;
    private final List<SpecialEventPolicy> policyList;

    public EventPoliciesController(Customer customer, MenuInformation giveawayMenu) {
        this.customer = customer;
        this.giveawayMenu = giveawayMenu;
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

    private void addDDayDiscountPolicy() {
        if (customer.orderBeforeChristmas()) {
            policyList.add(new DDayDiscountPolicy(customer.getDay()));
        }
    }

    private void addWeekdayDiscountPolicy() {
        if (customer.orderAtWeekday()) {
            policyList.add(new WeekdayDiscountPolicy(customer.countDessert()));
        }
    }

    private void addWeekendDiscountPolicy() {
        if (customer.orderAtWeekend()) {
            policyList.add(new WeekendDiscountPolicy(customer.countMain()));
        }
    }

    private void addDiscountAmount() {
        if (customer.orderAtSpecialDay()) {
            policyList.add(new SpecialDayDiscountPolicy());
        }
    }

    private void addGiveawayEventPolicy() {
        if (customer.calculateTotalPrice() >= MIN_AMOUNT_FOR_GIVEAWAY) {
            policyList.add(new GiveawayEventPolicy(giveawayMenu));
        }
    }
}
