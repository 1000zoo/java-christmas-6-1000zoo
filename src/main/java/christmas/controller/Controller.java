package christmas.controller;

import christmas.configuration.GiveawayMenu;
import christmas.configuration.InputConfiguration;
import christmas.domain.Badge;
import christmas.domain.Customer;
import christmas.domain.Menu;
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

        Customer customer = createCustomer();
        outputController.printCustomerResults(customer.toDTO());

        EventPolicies eventPolicies = createEventPolicies(customer);
        BenefitResultDto benefitResultDto = eventPolicies.createBenefitResultDto();

        outputController.printPolicyResult(benefitResultDto);
        outputController.printTotalBenefitAmount(eventPolicies.getTotalBenefit());

        int afterDiscountAmount = calculateAfterDiscountAmount(customer, eventPolicies);
        outputController.printAfterDiscount(afterDiscountAmount);

        Badge badge = Badge.findByAmount(customer.calculateTotalPrice());
        outputController.printBadgeResult(badge);
    }

    private void setGiveawayMenu() {
        MenuInformation menuInformation = menu.getInformationOf("샴페인");
        GiveawayMenu.INSTANCE.init(menuInformation);
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

    private EventPolicies createEventPolicies(Customer customer) {
        EventPoliciesController eventPoliciesController = new EventPoliciesController(customer);
        return eventPoliciesController.createEventPolicies();
    }

    private int calculateAfterDiscountAmount(Customer customer, EventPolicies eventPolicies) {
        return customer.calculateTotalPrice() - eventPolicies.getDiscountAmount();
    }
}
