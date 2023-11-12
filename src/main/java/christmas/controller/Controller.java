package christmas.controller;

import christmas.configuration.InputConfiguration;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.vo.Date;

public class Controller {

    private final Menu menu;
    private final OutputController outputController;

    public Controller() {
        menu = new Menu();
        outputController = new OutputController();
    }

    public void start() {
        Customer customer = createCustomer();
        outputController.printCustomerResults(customer.toDTO());
    }

    private Customer createCustomer() {
        outputController.printWelcomeMessage();
        Date date = askVisitDate();
        Orders orders = takeOrder();
        return new Customer(date, orders);
    }

    private Date askVisitDate() {
        return InputConfiguration.createDateInputController().readLine();
    }

    private Orders takeOrder() {
        return InputConfiguration.createOrdersInputController(menu).readLine();
    }
}
