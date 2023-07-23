package hello.kafka.order.message;

import hello.kafka.common.message.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * 주문이 취소 되었을 때, 상품의 갯수를 재계산하기 위한 이벤트.
 */
@Slf4j
public class OrderCanceledMessage extends Message {
    private final Long productId;
    private final int quantity;

    public OrderCanceledMessage(Long productId, int quantity) {
        super();
        log.info("CREATE EVENT : OrderCancelEvent ==> productId={} / quantity={}", productId, quantity);
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderCancelEvent{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
