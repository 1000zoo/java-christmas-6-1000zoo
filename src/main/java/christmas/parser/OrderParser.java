package christmas.parser;

import christmas.constant.ErrorMessage;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.vo.MenuInformation;
import java.util.List;

public class OrderParser implements Parser<String, Order> {

    private final static String DELIMITER = "-";
    private final static int MENU_INDEX = 0;
    private final static int QUANTITY_INDEX = 1;
    private final static int SPLIT_LIST_LENGTH = 2;

    private final MenuParser menuParser;

    public OrderParser(Menu menu) {
        this.menuParser = new MenuParser(menu);
    }

    @Override
    public Order parse(String input) {
        List<String> stringOrder = split(input);
        MenuInformation menuInformation = menuParser.parse(stringOrder.get(MENU_INDEX));
        int quantity = Integer.parseInt(stringOrder.get(QUANTITY_INDEX));
        return new Order(menuInformation, quantity);
    }

    private List<String> split(String input) {
        List<String> splitInput = List.of(input.split(DELIMITER));
        if (splitInput.size() != SPLIT_LIST_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        return splitInput;
    }
}
