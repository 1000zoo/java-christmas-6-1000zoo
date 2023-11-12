package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.discount.EventPolicies;
import christmas.domain.discount.SpecialEventPolicy;
import christmas.vo.Date;
import christmas.vo.MenuInformation;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventPoliciesControllerTest {

    private final static Menu menu = new Menu();

    private final static Arguments hasZeroPolicy =
            arguments(new Customer(new Date(31), createOrdersOf("양송이수프", 2)), 0);
    private final static Arguments hasOnePolicy =
            arguments(new Customer(new Date(30), createOrdersOf("양송이수프", 2)), 1);
    private final static Arguments hasTwoPolicy =
            arguments(new Customer(new Date(31), createOrdersOf("양송이수프", 2)), 2);
    private final static Arguments hasThreePolicy =
            arguments(new Customer(new Date(24), createOrdersOf("양송이수프", 2)), 3);
    private final static Arguments hasFourPolicy =
            arguments(new Customer(new Date(24), createOrdersOf("티본스테이크", 3)), 4);

    private static Orders createOrdersOf(String menuName, int quantity) {
        return new Orders(List.of(new Order(menu.getInformationOf(menuName), quantity)));
    }

    private static Stream<Arguments> provideCustomerDataAndSizeOfPolicy() {
        return Stream.of(
                hasZeroPolicy,
                hasOnePolicy,   // 주말할인
                hasTwoPolicy,   // 평일할인 + 별표할인
                hasThreePolicy, // 평일할인 + 별표할인 + 디데이할인
                hasFourPolicy   // 평일할인 + 별표할인 + 디데이할인 + 증정이벤트
        );
    }

    private static int countPolicies(EventPolicies policies) {
        int count = 0;
        for (SpecialEventPolicy policy : policies) {
            count++;
        }
        return count;
    }

    @ParameterizedTest
    @DisplayName("경우의 수 별로 총 몇개의 혜택을 받는 지 테스트")
    @MethodSource("provideCustomerDataAndSizeOfPolicy")
    void countPolicySize(Customer customer, int answer) {
        MenuInformation menuInformation = menu.getInformationOf("샴페인");
        EventPoliciesController controller = new EventPoliciesController(customer, menuInformation);
        EventPolicies policies = controller.createEventPolicies();

        int size = countPolicies(policies);

        assertThat(size).isEqualTo(answer);
    }

}