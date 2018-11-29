package test.marketplace.examples.k8s.api.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportRequestSender sender;

    @GetMapping("/request/{resource}")
    public void request(@PathVariable String resource) {
        Date start = Date.from(LocalDate.of(2018, 1, 1).atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date end = Date.from(LocalDate.of(2019, 1, 1).atStartOfDay(ZoneId.of("UTC")).toInstant());
        UsageReportRequest request = UsageReportRequest.builder()
                .resource(resource)
                .start(start)
                .end(end)
                .build();
        sender.sendReportRequest(request);
    }

    @GetMapping("/request-async/{resource}")
    public CompletableFuture<UsageReport> requestAsync(
            @PathVariable String resource) {
        Date start = Date.from(LocalDate.of(2018, 1, 1).atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date end = Date.from(LocalDate.of(2019, 1, 1).atStartOfDay(ZoneId.of("UTC")).toInstant());
        UsageReportRequest request = UsageReportRequest.builder()
                .resource(resource)
                .start(start)
                .end(end)
                .build();
        return sender.sendReportRequestAndReceive(request);
    }

}