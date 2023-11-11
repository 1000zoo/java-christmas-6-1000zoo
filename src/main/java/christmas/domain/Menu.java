package christmas.domain;

import christmas.parser.RepositoryParser;
import christmas.repository.MenuRepository;
import christmas.vo.MenuInformation;
import java.util.Map;

public class Menu {

    private final Map<String, MenuInformation> repository;

    public Menu() {
        MenuRepository menuRepository = new MenuRepository(new RepositoryParser());
        repository = menuRepository.loadRepository();
    }

    public boolean containsKey(String name) {
        return repository.containsKey(name);
    }

    private boolean notContainsKey(String name) {
        return !containsKey(name);
    }

    public MenuInformation getInformationOf(String name) {
        if (notContainsKey(name)) {
            throw new IllegalArgumentException();
        }
        return repository.get(name);
    }
}
