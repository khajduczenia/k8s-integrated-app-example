package test.marketplace.examples.k8s.api;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${rabbitmq.topic}")
    private String topicName;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicName);
    }

}
