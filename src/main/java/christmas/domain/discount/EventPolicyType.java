package christmas.domain.discount;

import christmas.constant.AmountEnum;
import christmas.constant.QuantityEnum;
import christmas.domain.Customer;
import christmas.dto.PoliciesRequestDto;
import java.util.function.Function;
import java.util.function.Predicate;

public enum EventPolicyType {
    DDAY("크리스마스 디데이 할인", DDayDiscountPolicy::new, EventPolicyType::dDayPolicyPredicate),
    WEEKDAY("평일 할인", WeekdayDiscountPolicy::new, EventPolicyType::weekdayPolicyPredicate),
    WEEKEND("주말 할인", WeekendDiscountPolicy::new, EventPolicyType::weekendPolicyPredicate),
    SPECIAL_DAY("특별 할인", SpecialDayDiscountPolicy::new, EventPolicyType::specialDayPolicyPredicate),
    GIVEAWAY("증정 이벤트", GiveawayEventPolicy::new, EventPolicyType::giveawayPolicyPredicate);

    private final String typeName;
    private final Function<PoliciesRequestDto, SpecialEventPolicy> constructor;
    private final Predicate<Customer> customerPredicate;

    EventPolicyType(String typeName,
                    Function<PoliciesRequestDto, SpecialEventPolicy> constructor,
                    Predicate<Customer> predicate) {
        this.typeName = typeName;
        this.constructor = constructor;
        this.customerPredicate = predicate;
    }

    public String getTypeName() {
        return typeName;
    }

    public SpecialEventPolicy from(PoliciesRequestDto policiesRequestDto) {
        return constructor.apply(policiesRequestDto);
    }

    public boolean canAddEvent(Customer customer) {
        return isEventApplied(customer) && customerPredicate.test(customer);
    }

    private static boolean isEventApplied(Customer customer) {
        return customer.calculateTotalPrice() >= AmountEnum.MIN_BOUNDARY_FOR_EVENT.getAmount();
    }

    private static boolean dDayPolicyPredicate(Customer customer) {
        return customer.orderBeforeChristmas();
    }

    private static boolean weekdayPolicyPredicate(Customer customer) {
        return customer.orderAtWeekday() && customer.countDessert() > QuantityEnum.MIN.getQuantity();
    }

    private static boolean weekendPolicyPredicate(Customer customer) {
        return customer.orderAtWeekend() && customer.countMain() > QuantityEnum.MIN.getQuantity();
    }

    private static boolean specialDayPolicyPredicate(Customer customer) {
        return customer.orderAtSpecialDay();
    }

    private static boolean giveawayPolicyPredicate(Customer customer) {
        return customer.calculateTotalPrice() >= AmountEnum.MIN_BOUNDARY_FOR_GIVEAWAY.getAmount();
    }
}
