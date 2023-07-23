package hello.kafka.product.domain;

import java.util.List;

public interface EventProductRepository {
    EventProduct save(EventProduct product);
    EventProduct findById(Long id);

    List<EventProduct> findAll();
}
