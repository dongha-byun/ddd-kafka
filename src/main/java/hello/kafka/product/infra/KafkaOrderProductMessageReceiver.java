package hello.kafka.product.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.kafka.common.message.MessageTopicConstants;
import hello.kafka.order.message.OrderCanceledMessage;
import hello.kafka.product.domain.EventProduct;
import hello.kafka.product.domain.EventProductRepository;
import hello.kafka.product.domain.OrderProductMessageReceiver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class KafkaOrderProductMessageReceiver implements OrderProductMessageReceiver {

    private final EventProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = MessageTopicConstants.ORDER_CANCEL_TOPIC)
    public void updateProductQuantityByOrderCanceled(String data) {
        log.info("Product Service receive message from Order Service : {}", data);

        try {
            OrderCanceledMessage message = objectMapper.readValue(data, OrderCanceledMessage.class);
            EventProduct product = productRepository.findById(message.getProductId());
            product.increaseQuantity(message.getQuantity());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
