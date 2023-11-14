package christmas.parser;

import christmas.constant.DelimiterEnum;
import christmas.constant.ErrorMessage;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.vo.MenuInformation;
import java.util.List;

public class OrderParser implements Parser<String, Order> {

    private final static int MENU_INDEX = 0;
    private final static int QUANTITY_INDEX = 1;
    private final static int SPLIT_LIST_LENGTH = 2;

    private final Menu menu;

    public OrderParser(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Order parse(String input) {
        List<String> stringOrder = split(input);

        MenuInformation menuInformation = menu.getInformationOf(stringOrder.get(MENU_INDEX));
        int quantity = parseIntOrElseThrow(stringOrder.get(QUANTITY_INDEX));

        return new Order(menuInformation, quantity);
    }

    private List<String> split(String input) {
        List<String> splitInput = List.of(input.split(DelimiterEnum.ORDER.getDelimiter()));
        validate(splitInput.size());
        return splitInput;
    }

    private void validate(int size) {
        if (size != SPLIT_LIST_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    private int parseIntOrElseThrow(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
