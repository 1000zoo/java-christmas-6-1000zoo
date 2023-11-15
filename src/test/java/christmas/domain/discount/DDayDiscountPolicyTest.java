package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.dto.PoliciesRequestDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DDayDiscountPolicyTest {

    private static Stream<Arguments> provideDateData() {
        return Stream.of(
                arguments(1, 1000),
                arguments(2, 1100),
                arguments(25, 3400)
        );
    }

    @ParameterizedTest
    @DisplayName("디데이 할인 정책 테스트")
    @MethodSource("provideDateData")
    void discountAmount(int day, int answer) {
        PoliciesRequestDto policiesRequestDto = new PoliciesRequestDto(0, 0, day);
        SpecialEventPolicy policy = new DDayDiscountPolicy(policiesRequestDto);
        int discount = policy.getDiscountAmount();
        assertThat(discount).isEqualTo(answer);
    }

    @Test
    @DisplayName("크리스마스 이후에는, 애초에 이벤트에 해당되지 않으므로, 테스트 제거")
    void afterChristmasCantBeExist() {
    }
}
