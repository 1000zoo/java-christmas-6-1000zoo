package christmas.configuration;

import christmas.controller.InputController;
import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.parser.DateInputParser;
import christmas.parser.OrdersInputParser;
import christmas.vo.Date;

public class InputConfiguration {

    private final static String ASK_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final static String TAKE_ORDER = "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private InputConfiguration() {
    }

    public static InputController<Date> createDateInputController() {
        return new InputController<>(new DateInputParser(), ASK_VISIT_DAY);
    }

    public static InputController<Orders> createOrdersInputController(Menu menu) {
        return new InputController<>(new OrdersInputParser(menu), TAKE_ORDER);
    }

}
