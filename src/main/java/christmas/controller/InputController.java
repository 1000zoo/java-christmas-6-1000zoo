package christmas.controller;

import christmas.parser.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class InputController<R> {

    private final InputParser<R> parser;
    private final String message;
    private final InputView inputView;
    private final OutputView outputView;

    public InputController(InputParser<R> parser, String message) {
        this.parser = parser;
        this.message = message;
        inputView = new InputView();
        outputView = new OutputView();
    }

    public R readLine() {
        outputView.printMessage(message);
        while (true) {
            try {
                return parser.parse(inputView.readLine());
            } catch (IllegalArgumentException exception) {
                parser.clear();
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
