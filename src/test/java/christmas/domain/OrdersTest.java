package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.vo.MenuInformation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {

    private Orders orders;

    @BeforeEach
    void setUp() {
        List<Order> list = new ArrayList<>();
        list.add(new Order(MenuInformation.of(List.of("양송이수프", "6000", "APPETIZER")), 2));
        list.add(new Order(MenuInformation.of(List.of("티본스테이크", "55000", "MAIN")), 3));
        list.add(new Order(MenuInformation.of(List.of("초코케이크", "15000", "DESSERT")), 1));

        orders = new Orders(list);
    }

    @Test
    @DisplayName("총 주문 수량 테스트")
    void orderQuantities() {
        int quantities = orders.calculateTotalQuantities();
        assertThat(quantities).isEqualTo(6);
    }

    @Test
    @DisplayName("총 주문 금액 테스트")
    void totalPrice() {
        int totalPrice = orders.calculateTotalPrice();
        int answerPrice = 6000 * 2 + 55000 * 3 + 15000;
        assertThat(totalPrice).isEqualTo(answerPrice);
    }
}