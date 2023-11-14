package christmas.view.output;

import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;
import christmas.constant.SummaryMessage;
import christmas.dto.OrderDto;
import christmas.dto.OrderHistoryDto;
import christmas.dto.OrdersDto;

public class OrderHistoryPrinter extends OutputView {

    public void printOrderHistory(OrderHistoryDto orderHistoryDTO) {
        printDateResult(orderHistoryDTO.day());
        printOrdersResult(orderHistoryDTO.ordersDto());
    }

    private void printDateResult(int day) {
        printMessage(ResultFormat.DATE_FORMAT.getFormatMessage(day));
    }

    private void printOrdersResult(OrdersDto ordersDto) {
        printMessage(SummaryMessage.ORDER_MENU.getMessage());
        ordersDto.orderDtoList().forEach(this::printOrderResult);
        printTotalAmount(ordersDto.totalPrice());
    }

    private void printOrderResult(OrderDto orderDTO) {
        String orderResult = fit(ResultFormat.ORDER_FORMAT, orderDTO.menuName(), orderDTO.quantity());
        printMessage(orderResult);
    }

    private void printTotalAmount(int amount) {
        printMessage(SummaryMessage.TOTAL_AMOUNT.getMessage());
        printMessage(KoreanWonFormat.PRICE.getAmountMessage(amount));
    }
}
