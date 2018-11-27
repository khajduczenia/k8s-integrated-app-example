package test.marketplace.examples.k8s.api.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportRequestSender sender;

    @GetMapping("/request")
    public Report request() {
        sender.sendReportRequest();
        return Report.builder()
                .content("TODO Report to be implemented")
                .build();
    }

}