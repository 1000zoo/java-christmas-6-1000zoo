package christmas.domain.discount;

import christmas.constant.DiscountAmountEnum;
import christmas.constant.QuantityEnum;
import christmas.domain.OrderHistory;
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
    private final Predicate<OrderHistory> customerPredicate;

    EventPolicyType(String typeName,
                    Function<PoliciesRequestDto, SpecialEventPolicy> constructor,
                    Predicate<OrderHistory> predicate) {
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

    public boolean canAddEvent(OrderHistory orderHistory) {
        return isEventApplied(orderHistory) && customerPredicate.test(orderHistory);
    }

    private static boolean isEventApplied(OrderHistory orderHistory) {
        return orderHistory.calculateTotalPrice() >= DiscountAmountEnum.MIN_BOUNDARY_FOR_EVENT.getAmount();
    }

    private static boolean dDayPolicyPredicate(OrderHistory orderHistory) {
        return orderHistory.orderBeforeChristmas();
    }

    private static boolean weekdayPolicyPredicate(OrderHistory orderHistory) {
        return orderHistory.orderAtWeekday() && orderHistory.countDessert() > QuantityEnum.MIN.getQuantity();
    }

    private static boolean weekendPolicyPredicate(OrderHistory orderHistory) {
        return orderHistory.orderAtWeekend() && orderHistory.countMain() > QuantityEnum.MIN.getQuantity();
    }

    private static boolean specialDayPolicyPredicate(OrderHistory orderHistory) {
        return orderHistory.orderAtSpecialDay();
    }

    private static boolean giveawayPolicyPredicate(OrderHistory orderHistory) {
        return orderHistory.calculateTotalPrice() >= DiscountAmountEnum.MIN_BOUNDARY_FOR_GIVEAWAY.getAmount();
    }
}
