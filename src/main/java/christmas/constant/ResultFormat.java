package christmas.constant;

public enum ResultFormat {
    DATE_FORMAT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_FORMAT("%s %d개"),
    EVENT_FORMAT("%s: %s");

    private final String format;

    ResultFormat(String format) {
        this.format = format;
    }

    public String getFormatMessage(Object... objects) {
        return String.format(format, objects);
    }
}
