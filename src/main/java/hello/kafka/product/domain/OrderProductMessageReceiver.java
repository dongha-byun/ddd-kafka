package hello.kafka.product.domain;

public interface OrderProductMessageReceiver {

    void updateProductQuantityByOrderCanceled(String data);
}
