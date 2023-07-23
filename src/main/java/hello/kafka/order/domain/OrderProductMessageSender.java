package hello.kafka.order.domain;

import hello.kafka.common.message.Message;

public interface OrderProductMessageSender {
    void send(String topic, Message message);
}
