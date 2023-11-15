package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {

    @Test
    @DisplayName("없는 메뉴 호출 예외처리")
    void enumNameDoesntExist() {
        String enumName = "Beverage";

        assertThrows(
                IllegalArgumentException.class,
                () -> MenuType.of(enumName)
        );
    }
}
