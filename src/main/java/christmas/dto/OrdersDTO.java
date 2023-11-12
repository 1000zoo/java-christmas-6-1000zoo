package christmas.dto;

import christmas.domain.Order;
import java.util.List;

public record OrdersDTO(List<Order> orders) {
}
