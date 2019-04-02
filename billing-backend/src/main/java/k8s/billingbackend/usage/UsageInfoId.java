package k8s.billingbackend.usage;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsageInfoId implements Serializable {

    private Date usageDate;
    private String resource;

}
