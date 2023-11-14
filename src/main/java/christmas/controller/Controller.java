package christmas.controller;

import christmas.configuration.InputConfiguration;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.OrderHistory;
import christmas.domain.Orders;
import christmas.domain.discount.EventPolicies;
import christmas.vo.Date;

public class Controller {

    private final Menu menu;
    private final OutputController outputController;

    public Controller() {
        menu = new Menu();
        outputController = new OutputController();
    }

    public void start() {
        OrderHistory orderHistory = createOrderHistory();
        outputController.printOutputHistory(orderHistory);

        EventPolicies eventPolicies = createEventPolicies(orderHistory);
        outputController.printPolicyResult(eventPolicies);

        Customer customer = new Customer(orderHistory, eventPolicies);
        outputController.printCustomerResult(customer);
    }

    private OrderHistory createOrderHistory() {
        outputController.printWelcomeMessage();
        Date date = askVisitDate();
        Orders orders = takeOrder();
        return new OrderHistory(date, orders);
    }

    private Date askVisitDate() {
        InputController<Date> dateInputController = InputConfiguration.createDateInputController();
        return dateInputController.readLine();
    }

    private Orders takeOrder() {
        InputController<Orders> ordersInputController = InputConfiguration.createOrdersInputController(menu);
        return ordersInputController.readLine();
    }

    private EventPolicies createEventPolicies(OrderHistory orderHistory) {
        EventPoliciesController eventPoliciesController = new EventPoliciesController(orderHistory);
        return eventPoliciesController.createEventPolicies();
    }
}
