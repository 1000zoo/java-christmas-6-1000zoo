package christmas.controller;

import christmas.configuration.InputConfiguration;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.view.output.OutputView;
import christmas.vo.Date;

public class Controller {

    private final static String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private final Menu menu;

    public Controller() {
        menu = new Menu();
    }

    public void start() {
        Customer customer = createCustomer();
    }

    private Customer createCustomer() {
        OutputView.printMessage(WELCOME_MESSAGE);
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
