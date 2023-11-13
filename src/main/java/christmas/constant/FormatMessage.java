package christmas.constant;

public enum FormatMessage {
    DATE_RESULT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_RESULT("%s %d개"),
    EVENT_RESULT("%s: %s");

    private final String format;

    FormatMessage(String format) {
        this.format = format;
    }

    public String getFormatMessage(Object... objects) {
        return String.format(format, objects);
    }
}
