package k8s.api;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.topic}")
    private String topicName;

    @Value("${rabbitmq.queue.replies}")
    private String repliesQueueName;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(topicName);
    }

    @Bean
    Queue queue() {
        return new Queue(repliesQueueName, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with("response.report");
    }

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(repliesQueueName);
        return new AsyncRabbitTemplate(rabbitTemplate, container);

    }
}
