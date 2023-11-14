package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ControllerTest extends NsTest {

    @ParameterizedTest
    @DisplayName("날짜 예외: 유효한 정수 입력이 아닐 경우")
    @ValueSource(strings = {"1234567891011", "0", "-1", "32"})
    void integerButInvalidDate(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("날짜 예외: 입력이 정수가 아닐 경우")
    @ValueSource(strings = {"\s", "a", "가", "🎅🏻"})
    void noIntegerDate(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Controller controller = new Controller();
        controller.start();
    }
}