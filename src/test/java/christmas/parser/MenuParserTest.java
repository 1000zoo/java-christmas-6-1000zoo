package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.vo.MenuInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuParserTest {

    private MenuParser parser;

    @BeforeEach
    void setUp() {
        parser = new MenuParser(new Menu());
    }

    @ParameterizedTest
    @DisplayName("올바른 메뉴를 불러오는 지 확인")
    @ValueSource(strings = {"레드와인", "해산물파스타"})
    void validMenu(String name) {
        MenuInformation menu = parser.parse(name);
        assertThat(menu.name()).isEqualTo(name);
    }

    @Test
    @DisplayName("잘못된 메뉴를 불러오면 에러 발생")
    void invalidMenu() {
        String name = "밀크쉐이크";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(name)
        );
    }
}