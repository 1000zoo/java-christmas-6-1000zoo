package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.dto.PoliciesRequestDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekdayDiscountPolicyTest {

    private static Stream<Arguments> provideOrdersData() {
        return Stream.of(
                arguments(5, 10115)
        );
    }

    @ParameterizedTest
    @DisplayName("평일 할인 정책 테스트")
    @MethodSource("provideOrdersData")
    void discountAmount(int countDessert, int answer) {
        PoliciesRequestDto policiesRequestDto = new PoliciesRequestDto(countDessert, 0, 0);
        SpecialEventPolicy policy = new WeekdayDiscountPolicy(policiesRequestDto);

        int discount = policy.getDiscountAmount();

        assertThat(discount).isEqualTo(answer);
    }
}
