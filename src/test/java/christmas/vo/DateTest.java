package christmas.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    @DisplayName("평일 확인")
    void normalDayTest() {
        Date nonNormalDay = new Date(1);
        Date normalDay = new Date(6);

        boolean falseResult = nonNormalDay.isNormalDay();
        boolean trueResult = normalDay.isNormalDay();

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
}
