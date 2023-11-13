package christmas.constant;

import java.text.DecimalFormat;

public enum KoreanWonFormat {
    PRICE("#,###원"),
    DISCOUNT("-#,###원");

    private final String format;

    KoreanWonFormat(String format) {
        this.format = format;
    }

    public String getAmountMessage(int amount) {
        DecimalFormat formatter = new DecimalFormat(format);
        return formatter.format(amount);
    }
}
