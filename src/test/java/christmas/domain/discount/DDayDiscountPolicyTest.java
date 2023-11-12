package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.vo.Date;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DDayDiscountPolicyTest {

    private static Stream<Arguments> provideDateData() {
        return Stream.of(
                arguments(new Date(1), 1000),
                arguments(new Date(2), 1100),
                arguments(new Date(25), 3400),
                arguments(new Date(26), 0)
        );
    }

    @ParameterizedTest
    @DisplayName("디데이 할인 정책 테스트")
    @MethodSource("provideDateData")
    void discountAmount(Date date, int answer) {
        SpecialEventPolicy policy = new DDayDiscountPolicy(date);
        int discount = policy.getDiscountAmount();
        assertThat(discount).isEqualTo(answer);
    }

}