package christmas.dto;

import java.util.List;

public record OrdersDto(List<OrderDto> orderDtoList, int totalPrice) {
}
