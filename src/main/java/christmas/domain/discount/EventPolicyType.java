package christmas.domain.discount;

public enum EventPolicyType {
    DDAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL_DAY("특별 할인"),
    GIVEAWAY("증정 이벤트");

    private final String typeName;

    EventPolicyType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
