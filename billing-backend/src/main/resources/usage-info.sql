CREATE TABLE IF NOT EXISTS usage_info(
    usage_date timestamp,
    resource varchar(100),
    usage decimal(12, 4)
);

INSERT INTO usage_info(usage_date, resource, usage) VALUES ('2018-12-01 10:00:00', 'cluster1', 20.213);
INSERT INTO usage_info(usage_date, resource, usage) VALUES ('2018-12-01 10:01:00', 'cluster1', 121.3);
INSERT INTO usage_info(usage_date, resource, usage) VALUES ('2018-12-01 10:02:00', 'cluster1', 19.1233);
INSERT INTO usage_info(usage_date, resource, usage) VALUES ('2018-12-01 10:02:00', 'cluster2', 1.3212);
INSERT INTO usage_info(usage_date, resource, usage) VALUES ('2018-12-01 10:03:00', 'cluster2', 10);