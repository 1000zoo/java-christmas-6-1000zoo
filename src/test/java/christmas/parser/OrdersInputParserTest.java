package christmas.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersInputParserTest {

    private OrdersInputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new OrdersInputParser(new Menu());
    }

    @ParameterizedTest
    @DisplayName("형식이 잘못되었다면 에러가 발생")
    @ValueSource(strings = {"크리스마스파스타,티본스테이크", "크리스마스파스타--1", "제로콜라-1, 타파스-2", "타파스-3-곱빼기", "", "-2"})
    void invalidInput(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("없는 음식을 시켰다면 에러 발생")
    @ValueSource(strings = {"감바스-3,타파스-2"})
    void notExistMenu(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("주문 수량이 0이면 에러 발생")
    @ValueSource(strings = {"타파스-0"})
    void zeroQuantity(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("음료만 시키면 에러 발생")
    @ValueSource(strings = {"제로콜라-2"})
    void orderOnlyDrinks(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("너무 많은 주문을 하면 에러 발생")
    @ValueSource(strings = {"티본스테이크-10,제로콜라-11"})
    void orderedTooManyMenu(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("중복메뉴를 한 번에 안 시키면 에러 발생")
    @ValueSource(strings = {"제로콜라-1,티본스테이크-2,제로콜라-1"})
    void menuMustBeAppearOnce(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> inputParser.parse(input)
        );
    }
}