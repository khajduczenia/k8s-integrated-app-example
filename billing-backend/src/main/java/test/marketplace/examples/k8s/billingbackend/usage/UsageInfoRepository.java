package test.marketplace.examples.k8s.billingbackend.usage;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface UsageInfoRepository extends CrudRepository<UsageInfo, UsageInfoId> {

    List<UsageInfo> findByUsageDateBetween(Date start, Date end);

    List<UsageInfo> findByResourceAndUsageDateBetween(String resource, Date start, Date end);

    @Query("SELECT SUM(u.usage) FROM UsageInfo u WHERE u.usageDate BETWEEN ?1 AND ?2")
    BigDecimal findSumOfTotalUsageBetween(Date start, Date end);

    @Query("SELECT SUM(u.usage) FROM UsageInfo u WHERE u.resource = ?1 AND u.usageDate BETWEEN ?2 AND ?3")
    BigDecimal findSumOfResourceUsageBetween(String resource, Date start, Date end);

}
