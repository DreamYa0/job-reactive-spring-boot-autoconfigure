package com.g7.framework.job.reactive;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.g7.framework.job.reactive.properties.ZookeeperProperties;
import com.g7.framework.job.reactive.rdb.JobEventRdbConfiguration;
import com.g7.framework.job.reactive.service.ElasticJobReactiveService;
import com.g7.framework.job.reactive.util.SpringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 任务自动配置
 */
@AutoConfiguration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class JobReactiveAutoConfiguration implements EnvironmentAware {

    private final ZookeeperProperties zookeeperProperties;
    private Environment environment;

    public JobReactiveAutoConfiguration(ZookeeperProperties zookeeperProperties) {
        this.zookeeperProperties = zookeeperProperties;
    }

    /**
     * 初始化Zookeeper注册中心
     * @return
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(zookeeperProperties.getServers(),
                zookeeperProperties.getNamespace());
        zkConfig.setBaseSleepTimeMilliseconds(zookeeperProperties.getBaseSleepTimeMilliseconds());
        zkConfig.setConnectionTimeoutMilliseconds(zookeeperProperties.getConnectionTimeoutMilliseconds());
        zkConfig.setDigest(zookeeperProperties.getDigest());
        zkConfig.setMaxRetries(zookeeperProperties.getMaxRetries());
        zkConfig.setMaxSleepTimeMilliseconds(zookeeperProperties.getMaxSleepTimeMilliseconds());
        zkConfig.setSessionTimeoutMilliseconds(zookeeperProperties.getSessionTimeoutMilliseconds());
        return new ZookeeperRegistryCenter(zkConfig);
    }

    @Bean
    public JobEventRdbConfiguration jobEventRdbConfiguration() {
        return new JobEventRdbConfiguration();
    }

    @Bean
    public ElasticJobReactiveService jobService() {
        return new ElasticJobReactiveService();
    }

    @Bean(name = "JobSpringUtils")
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
