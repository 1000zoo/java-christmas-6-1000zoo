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

    public boolean orderAfterChristmas() {
        return date.hasChristmasPassed();
    }

    public Date getDate() {
        return date;
    }
}
