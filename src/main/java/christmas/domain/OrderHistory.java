package christmas.domain;

import christmas.dto.OrderHistoryDto;
import christmas.vo.Date;

public class OrderHistory {

    private final Date date;
    private final Orders orders;

    public OrderHistory(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public int countDessert() {
        return orders.countDessert();
    }

    public int countMain() {
        return orders.countMain();
    }

    public int getDay() {
        return date.getDay();
    }

    public int calculateTotalPrice() {
        return orders.calculateTotalPrice();
    }

    public boolean orderBeforeChristmas() {
        return !date.hasChristmasPassed();
    }

    public boolean orderAtWeekday() {
        return date.isWeekday();
    }

    public boolean orderAtWeekend() {
        return date.isWeekend();
    }

    public boolean orderAtSpecialDay() {
        return date.isSpecialDay();
    }

    public OrderHistoryDto toDTO() {
        return new OrderHistoryDto(date.getDay(), orders.toDto());
    }
}
