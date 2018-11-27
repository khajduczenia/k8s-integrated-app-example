package test.marketplace.examples.k8s.billingbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;

public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
    }

}
