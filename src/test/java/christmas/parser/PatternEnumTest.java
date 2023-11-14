package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PatternEnumTest {

    @ParameterizedTest
    @DisplayName("양의 정수 정규식 테스트")
    @CsvSource(value = {
            "1:true",
            "123786:true",
            "-100:false",
            "a:false",
    }, delimiter = ':')
    void integerPattern(String input, boolean answer) {
        boolean result = PatternEnum.POSITIVE_INTEGER.matches(input);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("Order 형식 정규식 테스트")
    @CsvSource(value = {
            "양송이수프-3:true", "볶음밥-30:true",
            "abc-3:false", "양송이수프-a:false", "산타-🎅🏻:false",
            "라면--9:false", "볶음밥--:false", "라면-12-:false",
            "-집-0:false", "--볶음밥-3:false", "라면-13--:false"
    }, delimiter = ':')
    void orderPattern(String input, boolean answer) {
        boolean result = PatternEnum.ORDER.matches(input);

        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @DisplayName("Orders 형식 정규식 테스트")
    @CsvSource(value = {
            "양송이수프-3,티본스테이크-10:true", "해물파스타-30:true",
            "회-10,,:false", "초밥-3,,연어-5:false", "파스타-8,:false",
            "스테이크-a:false"
    }, delimiter = ':')
    void ordersPattern(String input, boolean answer) {
        boolean result = PatternEnum.ORDERS.matches(input);

        assertThat(result).isEqualTo(answer);
    }
}