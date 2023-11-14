package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.OrderHistory;
import christmas.domain.Orders;
import christmas.parser.OrdersInputParser;
import christmas.vo.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventPolicyTypeTest {

    private static OrdersInputParser ordersInputParser;

    @BeforeEach
    void setUp() {
        ordersInputParser = new OrdersInputParser(new Menu());
    }

    @ParameterizedTest
    @DisplayName("customer 에 따라 d-day 혜택 가능 여부 판단")
    @CsvSource(value = {
            "3:양송이수프-1:false",  // 금액 조건 X
            "27:양송이수프-2:false", // d-day 조건 X
            "5:양송이수프-2:true"
    }, delimiter = ':')
    void canCustomerReceiveDDayEvent(int day, String stringOrder, boolean answer) {
        Date date = new Date(day);
        Orders orders = ordersInputParser.parse(stringOrder);
        OrderHistory orderHistory = new OrderHistory(date, orders);

        boolean result = EventPolicyType.DDAY.canAddEvent(orderHistory);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("customer 에 따라 평일 혜택 가능 여부 판단")
    @CsvSource(value = {
            "3:양송이수프-1:false",      // 금액 조건 X
            "10:양송이수프-2:false",     // 디저트 메뉴 X
            "16:초코케이크-2:false",       // 평일 X
            "16:양송이수프-2:false",     // 디저트 메뉴 X, 평일 X
            "20:초코케이크-2:true"
    }, delimiter = ':')
    void canCustomerReceiveWeekdayEvent(int day, String stringOrder, boolean answer) {
        Date date = new Date(day);
        Orders orders = ordersInputParser.parse(stringOrder);
        OrderHistory orderHistory = new OrderHistory(date, orders);

        boolean result = EventPolicyType.WEEKDAY.canAddEvent(orderHistory);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("customer 에 따라 주말 혜택 가능 여부 판단")
    @CsvSource(value = {
            "3:양송이수프-1:false",      // 금액 조건 X
            "9:양송이수프-2:false",     // 메인 메뉴 X
            "12:바비큐립-1:false",       // 주말 X
            "12:양송이수프-2:false",     // 메인 메뉴 X, 주말 X
            "16:바비큐립-2:true"
    }, delimiter = ':')
    void canCustomerReceiveWeekendEvent(int day, String stringOrder, boolean answer) {
        Date date = new Date(day);
        Orders orders = ordersInputParser.parse(stringOrder);
        OrderHistory orderHistory = new OrderHistory(date, orders);

        boolean result = EventPolicyType.WEEKEND.canAddEvent(orderHistory);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("customer 에 따라 특별 혜택 가능 여부 판단")
    @CsvSource(value = {
            "3:양송이수프-1:false",      // 금액 조건 X
            "12:바비큐립-1:false",       // 특별한 날 X
            "25:바비큐립-2:true"
    }, delimiter = ':')
    void canCustomerReceiveSpecialEvent(int day, String stringOrder, boolean answer) {
        Date date = new Date(day);
        Orders orders = ordersInputParser.parse(stringOrder);
        OrderHistory orderHistory = new OrderHistory(date, orders);

        boolean result = EventPolicyType.SPECIAL_DAY.canAddEvent(orderHistory);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("customer 에 따라 증정 혜택 가능 여부 판단")
    @CsvSource(value = {
            "3:양송이수프-1:false",      // 금액 조건 X
            "12:바비큐립-3:true"
    }, delimiter = ':')
    void canCustomerReceiveGiveawayEvent(int day, String stringOrder, boolean answer) {
        Date date = new Date(day);
        Orders orders = ordersInputParser.parse(stringOrder);
        OrderHistory orderHistory = new OrderHistory(date, orders);

        boolean result = EventPolicyType.GIVEAWAY.canAddEvent(orderHistory);

        assertThat(result).isEqualTo(answer);
    }
}