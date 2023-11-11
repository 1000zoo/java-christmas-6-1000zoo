package christmas.parser;

public interface Parser<I, T> {
    T parse(I input);
}
