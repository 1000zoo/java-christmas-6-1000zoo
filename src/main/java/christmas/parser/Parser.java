package christmas.parser;

public interface Parser<T, R> {
    R parse(T input);
}
