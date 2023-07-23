package hello.kafka.order.application;

import hello.kafka.common.message.MessageTopicConstants;
import hello.kafka.order.domain.EventOrder;
import hello.kafka.order.domain.EventOrderItem;
import hello.kafka.order.domain.EventOrderRepository;
import hello.kafka.order.domain.OrderProductMessageSender;
import hello.kafka.order.message.OrderCanceledMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventOrderService {
    private final EventOrderRepository orderRepository;
    private final OrderProductMessageSender orderProductMessageSender;

    public EventOrder save(EventOrder order) {
        return orderRepository.save(order);
    }

    public EventOrder findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<EventOrder> findAll() {
        return orderRepository.findAll();
    }

    public EventOrder cancel(Long id) {
        EventOrder order = findById(id);
        order.cancel();

        order.getItems()
                .forEach(
                        this::sendOrderCancelMessage
                );

        return order;
    }

    private void sendOrderCancelMessage(EventOrderItem item) {
        orderProductMessageSender.send(
                MessageTopicConstants.ORDER_CANCEL_TOPIC,
                new OrderCanceledMessage(item.getProductId(), item.getQuantity())
        );
    }
}
