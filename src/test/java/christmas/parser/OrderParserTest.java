package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderParserTest {

    private OrderParser parser;

    @BeforeEach
    void setUp() {
        parser = new OrderParser(new Menu());
    }

    @ParameterizedTest
    @DisplayName("메뉴에 없는 주문이 들어오면 에러가 발생")
    @ValueSource(strings = {"김치찌개-2"})
    void notExistMenuOrder(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("잘못된 주문 형식이 들어오면 에러가 발생")
    @ValueSource(strings = {"-2", "크리스마스파스타-", "티본스테이크-하나", "티본스테이크-2-곱빼기"})
    void invalidOrderParser(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(input)
        );
    }

    @ParameterizedTest
    @DisplayName("주문수량이 0일 경우 에러 발생")
    @ValueSource(strings = {"티본스테이크-0"})
    void zeroQuantity(String input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(input)
        );
    }


    @ParameterizedTest
    @DisplayName("정상적인 주문이 들어오면 통과")
    @ValueSource(strings = {"타파스-1"})
    void validOrder(String input) {
        Menu menu = new Menu();
        String name = input.split("-")[0];
        int quantity = Integer.parseInt(input.split("-")[1]);
        Order answer = new Order(menu.getInformationOf(name), quantity);
        Order order = parser.parse(input);

        assertThat(order.getMenuName()).isEqualTo(answer.getMenuName());
        assertThat(order.getQuantity()).isEqualTo(answer.getQuantity());
    }
}