package christmas.parser;

public abstract class InputParser<R> implements Parser<String, R> {

    @Override
    public R parse(String input) {
        validate(input);
        return convert(input);
    }

    public void clear() {
    }

    protected abstract void validate(String input);

    protected abstract R convert(String input);
}
