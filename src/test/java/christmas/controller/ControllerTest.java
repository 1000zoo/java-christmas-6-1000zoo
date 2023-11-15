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
    @ValueSource(strings = {"1234567891011", "0", "-1", "32", "\s", "a", "가", "🎅🏻"})
    void invalidDateInput(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @DisplayName("주문 예외: 올바른 형식이 아닐 때")
    @ValueSource(strings = {
            "티라미수-3",   // 메뉴판에 없는 경우
            "제로콜라-10",  // 음료만 시킨 경우
            "타파스-1,양송이수프-10,타파스-1", // 중복 메뉴가 있는 경우
            "크리스마스파스-0", "타파스-1,제로콜라-0,티본스테이크-2",   // 0이 포함된 경우
            "아이스크림--", "바비큐립-1-", "티본스테이크-3,,", "타파스-31",
            "해산물파스타-1,티본스테이크-2,"    // 주문 형식이 다른 경우
    })
    void invalidOrdersInput(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Controller controller = new Controller();
        controller.start();
    }
}