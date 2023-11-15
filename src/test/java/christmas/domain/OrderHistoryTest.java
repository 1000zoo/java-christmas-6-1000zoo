package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.parser.OrdersInputParser;
import christmas.vo.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderHistoryTest {

    private final static Menu menu = new Menu();
    private static Orders orders;

    @BeforeEach
    void setUp() {
        OrdersInputParser parser = new OrdersInputParser(menu);
        orders = parser.parse("티본스테이크-1");
    }

    @ParameterizedTest
    @DisplayName("크리스마스 전, 후 확인")
    @CsvSource(value = {
            "1:true",
            "25:true",
            "28:false"
    }, delimiter = ':')
    void christmasTest(int day, boolean answer) {
        Date date = new Date(day);
        OrderHistory history = new OrderHistory(date, orders);
        boolean result = history.orderBeforeChristmas();

        assertThat(answer).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("평일 확인")
    @CsvSource(value = {
            "10:true",
            "25:true",
            "30:false"
    }, delimiter = ':')
    void weekdayTest(int day, boolean answer) {
        Date date = new Date(day);
        OrderHistory history = new OrderHistory(date, orders);
        boolean result = history.orderAtWeekday();

        assertThat(answer).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("주말 확인")
    @CsvSource(value = {
            "9:true",
            "29:true",
            "14:false"
    }, delimiter = ':')
    void weekendTest(int day, boolean answer) {
        Date date = new Date(day);
        OrderHistory history = new OrderHistory(date, orders);
        boolean result = history.orderAtWeekend();

        assertThat(answer).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("별표인 날 확인")
    @CsvSource(value = {
            "10:true",
            "25:true",
            "30:false"
    }, delimiter = ':')
    void specialDayTest(int day, boolean answer) {
        Date date = new Date(day);
        OrderHistory history = new OrderHistory(date, orders);
        boolean result = history.orderAtSpecialDay();

        assertThat(answer).isEqualTo(result);
    }
}
