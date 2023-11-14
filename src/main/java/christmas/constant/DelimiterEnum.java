package christmas.constant;

public enum DelimiterEnum {
    REPOSITORY("/"),
    ORDER("-"),
    ORDERS(","),
    INVALID_ORDERS(",,");

    private final String delimiter;

    DelimiterEnum(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
