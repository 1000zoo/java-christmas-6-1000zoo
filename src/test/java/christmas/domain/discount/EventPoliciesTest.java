package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.controller.EventPoliciesController;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderHistory;
import christmas.domain.Orders;
import christmas.vo.Date;
import christmas.vo.MenuInformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventPoliciesTest {

    private final static Menu menu = new Menu();
    private final static MenuInformation menuInformation = menu.getInformationOf("샴페인");

    private static OrderHistory orderHistory;

    private static void setUp(int day, String menuName, int quantity) {
        Date date = new Date(day);
        orderHistory = new OrderHistory(date, createOrdersOf(menuName, quantity));
    }

    private static Orders createOrdersOf(String menuName, int quantity) {
        return new Orders(List.of(new Order(menu.getInformationOf(menuName), quantity)));
    }

    @ParameterizedTest
    @DisplayName("경우의 수 별로 총 얼마의 할인을 받는 지 테스트")
    @CsvSource(value = {
            "31:양송이수프:1:0",     // 혜택 없음
            "30:양송이수프:2:0",     // 혜택 없음
            "31:양송이수프:2:1000",     // 별표 할인 (1000)
            "24:양송이수프:2:4300",     // 별표 할인 (1000) + 디데이 할인 (3300)
            "22:티본스테이크:3:9169",   // 주말할인 (2023 * 3) + 디데이할인 (3100) + 샴페인 증정 (0)
            "24:초코케이크:10:24530",    // 평일할인 (20230) + 별표할인 (1000) + 디데이할인 (3300) + 샴페인 증정 (0)
    }, delimiter = ':')
    void discountAmount(int day, String menuName, int quantity, int answer) {
        setUp(day, menuName, quantity);

        EventPoliciesController controller = new EventPoliciesController(orderHistory);
        EventPolicies policies = controller.createEventPolicies();

        int discountAmount = policies.getDiscountAmount();

        assertThat(discountAmount).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("경우의 수 별로 총 얼마의 혜택을 받는 지 테스트")
    @CsvSource(value = {
            "31:양송이수프:1:0",     // 혜택 없음
            "30:양송이수프:2:0",     // 혜택 없음
            "31:양송이수프:2:1000",     // 별표 할인 (1000)
            "24:양송이수프:2:4300",     // 별표 할인 (1000) + 디데이 할인 (3300)
            "22:티본스테이크:3:34169",   // 주말할인 (2023 * 3) + 디데이할인 (3100) + 샴페인 증정 (25000)
            "24:초코케이크:10:49530",    // 평일할인 (20230) + 별표할인 (1000) + 디데이할인 (3300) + 샴페인 증정 (25000)
    }, delimiter = ':')
    void benefitAmount(int day, String menuName, int quantity, int answer) {
        setUp(day, menuName, quantity);

        EventPoliciesController controller = new EventPoliciesController(orderHistory);
        EventPolicies policies = controller.createEventPolicies();

        int discountAmount = policies.getTotalBenefit();

        assertThat(discountAmount).isEqualTo(answer);
    }
}