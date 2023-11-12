package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendDiscountPolicyTest {

    private static Stream<Arguments> provideOrdersData() {
        return Stream.of(
                arguments(2, 4046)
        );
    }

    @ParameterizedTest
    @DisplayName("주말 할인 정책 테스트")
    @MethodSource("provideOrdersData")
    void discountAmount(int count, int answer) {
        SpecialEventPolicy policy = new WeekendDiscountPolicy(count);

        int discount = policy.getDiscountAmount();

        assertThat(discount).isEqualTo(answer);
    }
}