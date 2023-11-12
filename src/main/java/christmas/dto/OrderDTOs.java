package christmas.dto;

import java.util.List;

public record OrderDTOs(List<OrderDTO> orderDTOs, int totalPrice) {
}
