package christmas.repository;

import christmas.parser.Parser;
import christmas.vo.MenuInformation;
import java.util.List;
import java.util.Map;

public record MenuRepository(Parser<List<String>, Map<String, MenuInformation>> parser) {
    private final static List<String> menuList = List.of(
            "양송이수프/6000/APPETIZER",
            "타파스/5500/APPETIZER",
            "시저샐러드/8000/APPETIZER",
            "티본스테이크/55000/MAIN",
            "바비큐립/54000/MAIN",
            "해산물파스타/35000/MAIN",
            "크리스마스파스타/25000/MAIN",
            "초코케이크/15000/DESSERT",
            "아이스크림/5000/DESSERT",
            "제로콜라/3000/DRINK",
            "레드와인/60000/DRINK",
            "샴페인/25000/DRINK"
    );
    private final static String giveawayMenuName = "샴페인";

    public Map<String, MenuInformation> loadRepository() {
        return parser.parse(menuList);
    }

    public String loadGiveawayMenuName() {
        return giveawayMenuName;
    }
}
