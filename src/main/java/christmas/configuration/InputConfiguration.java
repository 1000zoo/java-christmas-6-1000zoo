package christmas.configuration;

import christmas.constant.InstructionMessage;
import christmas.controller.InputController;
import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.parser.DateInputParser;
import christmas.parser.OrdersInputParser;
import christmas.vo.Date;

public class InputConfiguration {

    private InputConfiguration() {
    }

    public static InputController<Date> createDateInputController() {
        return new InputController<>(new DateInputParser(), InstructionMessage.ASK_VISIT_DAY.getMessage());
    }

    public static InputController<Orders> createOrdersInputController(Menu menu) {
        return new InputController<>(new OrdersInputParser(menu), InstructionMessage.TAKE_ORDER.getMessage());
    }

}
