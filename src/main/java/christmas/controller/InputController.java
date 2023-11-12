package christmas.controller;

import christmas.parser.InputParser;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class InputController<R> {


    private final InputParser<R> parser;
    private final String message;
    private final InputView inputView;

    public InputController(InputParser<R> parser, String message) {
        this.parser = parser;
        this.message = message;
        inputView = new InputView();
    }

    public R readLine() {
        OutputView.printMessage(message);
        while (true) {
            try {
                return parser.parse(inputView.readLine());
            } catch (IllegalArgumentException exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
