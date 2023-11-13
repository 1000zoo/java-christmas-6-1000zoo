package christmas.validator;

import christmas.constant.ErrorMessage;
import christmas.domain.Menu;

public class MenuValidator implements Validator<String> {

    private final static String INVALID_MENU_ORDERED_ERROR_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private final Menu menu;

    public MenuValidator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void validate(String target) {
        throwIfDoesNotExistMenu(target);
    }

    private void throwIfDoesNotExistMenu(String target) {
        if (!menu.containsKey(target)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
