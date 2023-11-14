package christmas.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @ParameterizedTest
    @DisplayName("유효하지 않은 날짜 예외처리 테스트")
    @ValueSource(ints = {-1, 0, 32})
    void invalidDay(int day) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Date(day)
        );
    }

    @Test
    @DisplayName("평일 확인")
    void normalDayTest() {
        Date nonNormalDay = new Date(1);
        Date normalDay = new Date(6);

        boolean falseResult = nonNormalDay.isWeekday();
        boolean trueResult = normalDay.isWeekday();

        assertThat(falseResult).isEqualTo(false);
        assertThat(trueResult).isEqualTo(true);
    }

    @Test
    @DisplayName("주말 확인")
    void weekendTest() {
        Date noWeekend = new Date(24);
        Date weekend = new Date(8);

        boolean falseResult = noWeekend.isWeekend();
        boolean trueResult = weekend.isWeekend();

        assertThat(falseResult).isEqualTo(false);
        assertThat(trueResult).isEqualTo(true);
    }

    @Test
    @DisplayName("특별한 날 확인")
    void specialTest() {
        Date noSpecialDay = new Date(22);
        Date specialDay = new Date(25);

        boolean falseResult = noSpecialDay.isSpecialDay();
        boolean trueResult = specialDay.isSpecialDay();

        assertThat(falseResult).isEqualTo(false);
        assertThat(trueResult).isEqualTo(true);
    }

    @Test
    void test() {
        LocalDate date = LocalDate.of(2023, 12, 0);
    }
}
