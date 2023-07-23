package hello.kafka.product.application;

import hello.kafka.product.domain.EventProduct;
import hello.kafka.product.domain.EventProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventProductService {

    private final EventProductRepository productRepository;

    public EventProductService(EventProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public EventProduct save(EventProduct product) {
        return productRepository.save(product);
    }

    public EventProduct findById(Long id) {
        return productRepository.findById(id);
    }

    public List<EventProduct> findAll() {
        return productRepository.findAll();
    }
}
