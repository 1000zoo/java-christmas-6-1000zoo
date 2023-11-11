package christmas.view.input;

import christmas.view.output.Output;

public abstract class Input<T> {
    public T readLine() {
        while (true) {
            try {
                String line = InputView.readLine();
                return convert(line);
            } catch (IllegalArgumentException exception) {
                Output.printErrorMessage(exception.getMessage());
            }
        }
    }

    protected abstract T convert(String line);
}
