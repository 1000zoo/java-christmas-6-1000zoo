package christmas.dto;

import christmas.vo.MenuInformation;

public record PoliciesRequestDto(
        int countDessert,
        int countMain,
        int day,
        MenuInformation giveawayMenu
) {
}
