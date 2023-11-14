package christmas.dto;

import christmas.domain.Badge;

public record CustomerDto(int afterDiscountPrice, Badge badge) {
}
