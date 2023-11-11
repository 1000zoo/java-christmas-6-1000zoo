package christmas.parser;

import christmas.domain.MenuInformation;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RepositoryParserTest {

    private Parser<List<String>, Map<String, MenuInformation>> parser;

    @BeforeEach
    private void before() {
        parser = new RepositoryParser();
    }

    @Test
    @DisplayName("문자열 리포지토리 -> 객체 변환 테스트")
    void parserTest() {
        List<String> stringRepository = List.of(
                "양송이수프/6000/APPETIZER"
        );
        Map<String, MenuInformation> menu = parser.parse(stringRepository);

        Assertions.assertThat(menu.size()).isEqualTo(1);
        System.out.println("menu = " + menu);
    }
}