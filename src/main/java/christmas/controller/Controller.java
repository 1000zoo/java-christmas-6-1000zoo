package christmas.controller;

import christmas.configuration.GiveawayMenu;
import christmas.configuration.InputConfiguration;
import christmas.domain.Menu;
import christmas.domain.OrderHistory;
import christmas.domain.Orders;
import christmas.domain.discount.EventPolicies;
import christmas.dto.BenefitResultDto;
import christmas.vo.Date;
import christmas.vo.MenuInformation;

public class Controller {

    private final Menu menu;
    private final OutputController outputController;

    public Controller() {
        menu = new Menu();
        outputController = new OutputController();
    }

    public void start() {
        setGiveawayMenu();

        OrderHistory orderHistory = createCustomer();
        outputController.printCustomerResults(orderHistory.toDTO());

        EventPolicies eventPolicies = createEventPolicies(orderHistory);
        BenefitResultDto benefitResultDto = eventPolicies.createBenefitResultDto();

        outputController.printPolicyResult(benefitResultDto);
    }

    private void setGiveawayMenu() {
        MenuInformation menuInformation = menu.getInformationOf("샴페인");
        GiveawayMenu.INSTANCE.init(menuInformation);
    }

    private OrderHistory createCustomer() {
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

    private int calculateAfterDiscountAmount(OrderHistory orderHistory, EventPolicies eventPolicies) {
        return orderHistory.calculateTotalPrice() - eventPolicies.getDiscountAmount();
    }
}
