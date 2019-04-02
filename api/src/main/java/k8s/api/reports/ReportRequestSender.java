package k8s.api.reports;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.AsyncAmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ReportRequestSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AsyncRabbitTemplate asyncAmqpTemplate;


    @Value("${rabbitmq.topic}")
    private String topicName;

    public void sendReportRequest(UsageReportRequest request) {
        log.debug("sending message to exchange...");
        amqpTemplate.convertAndSend(topicName, "request.report", request);
    }

    public CompletableFuture<UsageReport> sendReportRequestAndReceive(UsageReportRequest request) {
        AsyncRabbitTemplate.RabbitConverterFuture<UsageReport> future =
                asyncAmqpTemplate.convertSendAndReceive(topicName, "request.report", request);

        future.addCallback(
                new ListenableFutureCallback<UsageReport>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error in reading response", ex);
                    }

                    @Override
                    public void onSuccess(UsageReport result) {
                        log.info("received result: {}", result);
                    }
                }
        );

        return future.completable();
    }

}