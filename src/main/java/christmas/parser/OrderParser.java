package christmas.parser;

import christmas.constant.DelimiterEnum;
import christmas.constant.ErrorMessage;
import christmas.constant.IndicesEnum;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.vo.MenuInformation;
import java.util.List;

public class OrderParser implements Parser<String, Order> {

    private final Menu menu;

    public OrderParser(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Order parse(String input) {
        throwIfInvalidPattern(input);

        List<String> stringOrder = split(input);

        MenuInformation menuInformation = menu.getInformationOf(stringOrder.get(IndicesEnum.MENU.index()));
        int quantity = IntegerParser.parse(stringOrder.get(IndicesEnum.QUANTITY.index()),
                ErrorMessage.INVALID_ORDER.getMessage());

        return new Order(menuInformation, quantity);
    }

    private List<String> split(String input) {
        return List.of(input.split(DelimiterEnum.ORDER.getDelimiter()));
    }

    private void throwIfInvalidPattern(String input) {
        if (!PatternEnum.ORDER.matches(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
