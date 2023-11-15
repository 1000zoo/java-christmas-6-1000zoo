package christmas.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.parser.OrdersInputParser;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersDtoTest {

    private final static Menu menu = new Menu();
    private static Orders orders;

    @BeforeEach
    void setUp() {
        OrdersInputParser parser = new OrdersInputParser(menu);
        orders = parser.parse("해산물파스타-2,제로콜라-1");
    }

    @Test
    @DisplayName("orders 불변성 테스트")
    void isOrdersUnmodifiable() {
        OrdersDto ordersDto = orders.toDto();
        List<OrderDto> ordersList = ordersDto.orderDtoList();

        assertThrows(
                UnsupportedOperationException.class,
                () -> ordersList.add(
                        new Order(menu.getInformationOf("타파스"), 2).toDTO()
                )
        );
    }
}
