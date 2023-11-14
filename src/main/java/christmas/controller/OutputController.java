package christmas.controller;

import christmas.constant.InstructionMessage;
import christmas.dto.BenefitResultDto;
import christmas.dto.OrderHistoryDto;
import christmas.view.CustomerResultPrinter;
import christmas.view.OutputView;
import christmas.view.PolicyResultPrinter;

public class OutputController {

    private final OutputView outputView;

    public OutputController() {
        outputView = new OutputView();
    }

    public void printWelcomeMessage() {
        outputView.printMessage(InstructionMessage.WELCOME.getMessage());
    }

    public void printCustomerResults(OrderHistoryDto orderHistoryDTO) {
        CustomerResultPrinter printer = new CustomerResultPrinter();
        printer.printCustomerResults(orderHistoryDTO);
    }

    public void printPolicyResult(BenefitResultDto benefitResultDto) {
        PolicyResultPrinter printer = new PolicyResultPrinter();
        printer.printPolicyResult(benefitResultDto);
    }
}
