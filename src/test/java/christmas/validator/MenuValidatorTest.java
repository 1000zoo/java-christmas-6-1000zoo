package christmas.validator;

import christmas.domain.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuValidatorTest {

    private MenuValidator validator;

    @BeforeEach
    void setUp() {
        validator = new MenuValidator(new Menu());
    }

    @ParameterizedTest
    @DisplayName("유효한 주문인 경우")
    @ValueSource(strings = {"시저샐러드", "크리스마스파스타"})
    void validMenu(String menuName) {
        validator.validate(menuName);
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 주문인 경우")
    @ValueSource(strings = {"립아이스테이크", "크림파스타"})
    void invalidMenu(String menuName) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(menuName)
        );
    }
}