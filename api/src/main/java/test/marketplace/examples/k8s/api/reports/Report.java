package test.marketplace.examples.k8s.api.reports;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {

    private String content;

}