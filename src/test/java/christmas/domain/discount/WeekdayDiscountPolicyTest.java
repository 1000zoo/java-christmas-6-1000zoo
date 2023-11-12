package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.parser.InputParser;
import christmas.parser.OrdersInputParser;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekdayDiscountPolicyTest {

    private InputParser<Orders> inputParser;

    private static Stream<Arguments> provideOrdersData() {
        return Stream.of(
                arguments("티본스테이크-2,초코케이크-3,아이스크림-2", 10115)
        );
    }

    @BeforeEach
    void setUp() {
        inputParser = new OrdersInputParser(new Menu());
    }

    @ParameterizedTest
    @DisplayName("평일 할인 정책 테스트")
    @MethodSource("provideOrdersData")
    void discountAmount(String input, int answer) {
        Orders orders = inputParser.parse(input);
        SpecialEventPolicy policy = new WeekdayDiscountPolicy(orders);

        int discount = policy.getDiscountAmount();

        assertThat(discount).isEqualTo(answer);
    }
}