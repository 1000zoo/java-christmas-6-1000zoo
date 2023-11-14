package christmas.dto;

import christmas.domain.Badge;

public record CustomerDTO(int afterDiscountPrice, Badge badge) {
}
