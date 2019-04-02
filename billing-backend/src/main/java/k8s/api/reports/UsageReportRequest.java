package k8s.api.reports;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Value
@Builder
public class UsageReportRequest implements Serializable {

    private String resource;
    private Date start;
    private Date end;

}
