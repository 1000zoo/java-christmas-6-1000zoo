package christmas.parser;

import christmas.domain.MenuInformation;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryParser implements Parser<List<String>, Map<String, MenuInformation>> {

    private final static String DELIMITER = "/";

    private final Map<String, MenuInformation> repository;

    public RepositoryParser() {
        repository = new HashMap<>();
    }

    @Override
    public Map<String, MenuInformation> parse(List<String> input) {
        input.forEach(this::putMenu);
        return Collections.unmodifiableMap(repository);
    }

    private void putMenu(String menu) {
        List<String> menuInformations = split(menu);
        MenuInformation menuInformation = MenuInformation.of(menuInformations);
        repository.put(menuInformation.name(), menuInformation);
    }

    private List<String> split(String input) {
        return List.of(input.split(DELIMITER));
    }
}
