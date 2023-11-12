package christmas.domain;

import christmas.vo.Date;

public class Customer {

    private final Date date;
    private final Orders orders;

    public Customer(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public int calculateTotalConsumption() {
        return orders.calculateTotalPrice();
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
}
