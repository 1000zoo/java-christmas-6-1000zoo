package christmas.controller;

import christmas.parser.InputParser;
import christmas.view.input.InputView;
import christmas.view.output.Output;

public class InputController<R> {

    private final InputParser<R> parser;
    private final String message;

    public InputController(InputParser<R> parser, String message) {
        this.parser = parser;
        this.message = message;
    }

    public R readLine() {
        Output.printMessage(message);
        while (true) {
            try {
                return parser.parse(InputView.readLine());
            } catch (IllegalArgumentException exception) {
                Output.printErrorMessage(exception.getMessage());
            }
        }
    }
}
