package hello.kafka.order.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.kafka.common.message.Message;
import hello.kafka.order.domain.OrderProductMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaOrderProductMessageSender implements OrderProductMessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(String topic, Message message) {
        try {
            String jsonData = objectMapper.writeValueAsString(message);

            kafkaTemplate.send(topic, jsonData);
            log.info("Order Service send Event Message to Product Service : {}", jsonData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
