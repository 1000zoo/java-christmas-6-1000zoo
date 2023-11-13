package christmas.domain.discount;

import christmas.dto.PoliciesRequestDto;
import java.util.function.Function;

public enum EventPolicyType {
    DDAY("크리스마스 디데이 할인", DDayDiscountPolicy::new),
    WEEKDAY("평일 할인", WeekdayDiscountPolicy::new),
    WEEKEND("주말 할인", WeekendDiscountPolicy::new),
    SPECIAL_DAY("특별 할인", SpecialDayDiscountPolicy::new),
    GIVEAWAY("증정 이벤트", GiveawayEventPolicy::new);

    private final String typeName;
    private final Function<PoliciesRequestDto, SpecialEventPolicy> constructor;

    EventPolicyType(String typeName,
                    Function<PoliciesRequestDto, SpecialEventPolicy> constructor) {
        this.typeName = typeName;
        this.constructor = constructor;
    }

    public String getTypeName() {
        return typeName;
    }

    public SpecialEventPolicy from(PoliciesRequestDto policiesRequestDto) {
        return constructor.apply(policiesRequestDto);
    }
}
