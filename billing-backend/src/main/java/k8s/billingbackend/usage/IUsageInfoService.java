package k8s.billingbackend.usage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IUsageInfoService {

    void save(UsageInfo info);

    List<UsageInfo> findAllBetween(Date start, Date end);

    List<UsageInfo> findResourceBetween(String resource, Date start, Date end);

    BigDecimal findResourceUsageBetween(String resource, Date start, Date end);
}
