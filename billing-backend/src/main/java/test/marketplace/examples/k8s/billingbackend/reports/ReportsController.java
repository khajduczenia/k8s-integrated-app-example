package test.marketplace.examples.k8s.billingbackend.reports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import test.marketplace.examples.k8s.api.reports.UsageReport;
import test.marketplace.examples.k8s.api.reports.UsageReportRequest;
import test.marketplace.examples.k8s.billingbackend.usage.IUsageInfoService;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@Slf4j
public class ReportsController {

    @Autowired
    private IUsageInfoService usageInfoService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.topic}")
    private String topicName;

    @RabbitListener(queues = "${rabbitmq.queue.requests}")
    public UsageReport receiveReportRequest(UsageReportRequest request) {
        log.info("Received report request: {}", request);
        return buildUsageReport(request.getResource(), request.getStart(), request.getEnd());
    }

    private UsageReport buildUsageReport(String resource, Date start, Date end) {
        BigDecimal usage = usageInfoService.findResourceUsageBetween(resource, start, end);
        return UsageReport.builder()
                .resource(resource)
                .start(start)
                .end(end)
                .totalUsage(usage)
                .build();
    }

    private void sendUsageReportToTopic(UsageReport report) {
        amqpTemplate.convertAndSend(topicName, "response.report", report);
    }

}
