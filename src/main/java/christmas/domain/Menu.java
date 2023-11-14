package christmas.domain;

import christmas.configuration.GiveawayMenu;
import christmas.constant.ErrorMessage;
import christmas.parser.RepositoryParser;
import christmas.repository.MenuRepository;
import christmas.vo.MenuInformation;
import java.util.Map;

public class Menu {

    private final Map<String, MenuInformation> repository;

    public Menu() {
        MenuRepository menuRepository = new MenuRepository(new RepositoryParser());
        repository = menuRepository.loadRepository();
        setGiveawayMenu(menuRepository.loadGiveawayMenuName());
    }

    private void setGiveawayMenu(String menuName) {
        GiveawayMenu.INSTANCE.init(getInformationOf(menuName));
    }

    public boolean containsKey(String name) {
        return repository.containsKey(name);
    }

    public MenuInformation getInformationOf(String name) {
        if (!containsKey(name)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        return repository.get(name);
    }
}
