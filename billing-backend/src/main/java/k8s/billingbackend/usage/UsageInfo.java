package k8s.billingbackend.usage;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "usage_info")
@IdClass(UsageInfoId.class)
public class UsageInfo implements Serializable {

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date usageDate;

    @Id
    private String resource;

    @Column(columnDefinition = "DECIMAL(12,4)")
    private BigDecimal usage;

}
