package christmas.controller;

import christmas.constant.InstructionMessage;
import christmas.domain.Customer;
import christmas.domain.OrderHistory;
import christmas.domain.discount.EventPolicies;
import christmas.view.output.CustomerResultPrinter;
import christmas.view.output.OrderHistoryPrinter;
import christmas.view.output.OutputView;
import christmas.view.output.PolicyResultPrinter;

public class OutputController {

    private final OutputView outputView;

    public OutputController() {
        outputView = new OutputView();
    }

    public void printWelcomeMessage() {
        outputView.printMessage(InstructionMessage.WELCOME.getMessage());
    }

    public void printOutputHistory(OrderHistory orderHistory) {
        OrderHistoryPrinter printer = new OrderHistoryPrinter();
        printer.printOrderHistory(orderHistory.toDTO());
    }

    public void printPolicyResult(EventPolicies eventPolicies) {
        PolicyResultPrinter printer = new PolicyResultPrinter();
        printer.printPolicyResult(eventPolicies.createBenefitResultDto());
    }

    public void printCustomerResult(Customer customer) {
        CustomerResultPrinter printer = new CustomerResultPrinter();
        printer.printCustomerResult(customer.toDTO());
    }
}
