package k8s.billingbackend.usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UsageInfoService implements IUsageInfoService {

    @Autowired
    private UsageInfoRepository repository;

    @Override
    public void save(UsageInfo info) {
        repository.save(info);
    }

    @Override
    public List<UsageInfo> findAllBetween(Date start, Date end) {
        return repository.findByUsageDateBetween(start, end);
    }

    @Override
    public List<UsageInfo> findResourceBetween(String resource, Date start, Date end) {
        return repository.findByResourceAndUsageDateBetween(resource, start, end);
    }

    @Override
    public BigDecimal findResourceUsageBetween(String resource, Date start, Date end) {
        return repository.findSumOfResourceUsageBetween(resource, start, end);
    }

}
