package christmas.constant;

public enum SummaryMessage {
    ORDER_MENU("주문 메뉴"),
    TOTAL_AMOUNT("할인 전 총주문 금액"),
    GIVEAWAY_MENU("증정 메뉴"),
    EVENT_RESULT("혜택 내역"),
    BENEFITS_RESULT("총혜택 금액"),
    AFTER_DISCOUNT("할인 후 예상 결제 금액"),
    BADGE_RESULT("12월 이벤트 배지");

    private final static String INSTRUCTION_FORMAT = "<%s>";
    private final String message;

    SummaryMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(INSTRUCTION_FORMAT, message);
    }
}
