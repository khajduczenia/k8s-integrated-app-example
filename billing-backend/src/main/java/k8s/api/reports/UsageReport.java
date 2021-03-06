package k8s.api.reports;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Value
@Builder
public class UsageReport implements Serializable {

    private String resource;
    private Date start;
    private Date end;
    private BigDecimal totalUsage;
    private String timestamp;

}
