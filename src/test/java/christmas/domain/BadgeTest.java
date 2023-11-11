package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {

    private static Stream<Arguments> provideBadgeData() {
        return Stream.of(
                arguments(Badge.NONE, 1000),
                arguments(Badge.STAR, 5000),
                arguments(Badge.TREE, 10000),
                arguments(Badge.SANTA, 20000)
        );
    }

    @ParameterizedTest
    @DisplayName("금액 별 배지 부여 테스트")
    @MethodSource("provideBadgeData")
    void getBadgeTest(Badge answerBadge, int amount) {
        Badge resultBadge = Badge.findByAmount(amount);

        assertThat(resultBadge).isEqualTo(answerBadge);
    }
}