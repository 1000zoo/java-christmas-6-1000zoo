package christmas.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateInputParserTest {

    @ParameterizedTest
    @DisplayName("유효하지 않은 날짜 테스트")
    @ValueSource(strings = {"-1", "a", "가", "32", "0"})
    void invalidDate(String stringDate) {
        DateInputParser parser = new DateInputParser();
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(stringDate)
        );
    }

}