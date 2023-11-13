package christmas.validator;

import christmas.constant.ErrorMessage;
import christmas.domain.Menu;

public class MenuValidator implements Validator<String> {

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
