package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    Map<String, MenuInformation> repository;

    public Menu() {
        repository = new HashMap<>();
    }

    private void setRepository() {

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
        return null;
    }
}
