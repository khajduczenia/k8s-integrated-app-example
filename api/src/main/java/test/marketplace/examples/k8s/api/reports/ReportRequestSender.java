package test.marketplace.examples.k8s.api.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReportRequestSender {

    private Logger logger = LoggerFactory.getLogger(ReportRequestSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.topic}")
    private String topicName;

    public void sendReportRequest() {
        logger.debug("sending message to exchange...");
        amqpTemplate.convertAndSend(topicName, "report.request",
                "hello world at " + LocalDateTime.now());
    }

}