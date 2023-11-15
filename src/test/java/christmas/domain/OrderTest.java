package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.parser.OrderParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    private final static Menu menu = new Menu();
    private static OrderParser parser;

    @BeforeEach
    void setUp() {
        parser = new OrderParser(menu);
    }

    @ParameterizedTest
    @DisplayName("두 Order 객체의 메뉴가 같다면, 같은 걸로 취급한다.")
    @CsvSource(value = {
            "양송이수프-2:양송이수프-2",
            "크리스마스파스타-5:크리스마스파스타-1",
    }, delimiter = ':')
    void hasSameMenu(String input1, String input2) {
        Order order1 = parser.parse(input1);
        Order order2 = parser.parse(input2);

        boolean result = order1.hasSameMenu(order2);

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @DisplayName("두 Order 객체의 메뉴가 다르다면, 다른 걸로 취급한다.")
    @CsvSource(value = {
            "양송이수프-2:초코케이크-2",
            "크리스마스파스타-5:레드와인-1",
    }, delimiter = ':')
    void hasDifferentMenu(String input1, String input2) {
        Order order1 = parser.parse(input1);
        Order order2 = parser.parse(input2);

        boolean result = order1.hasSameMenu(order2);

        assertThat(result).isEqualTo(false);
    }
}
