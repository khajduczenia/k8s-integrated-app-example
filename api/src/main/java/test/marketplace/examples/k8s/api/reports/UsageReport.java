package test.marketplace.examples.k8s.api.reports;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
@Builder
public class UsageReport {

    private String resource;
    private Date start;
    private Date end;
    private BigDecimal totalUsage;

}