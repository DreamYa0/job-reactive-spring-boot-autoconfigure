package com.g7.framework.job.reactive.service;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.g7.framework.monitor.reactive.RobotNotificationService;
import com.g7.framwork.common.util.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author dreamyao
 * @title 任务调度抽象类
 * @date 2019/10/23 2:22 AM
 * @since 1.0.0
 */
public abstract class AbstractReactiveSimpleJob implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(AbstractReactiveSimpleJob.class);
    @Autowired
    protected RobotNotificationService notificationService;
    private static final String TRACE_ID = "X-B3-TraceId";
    private static final String SPAN_ID = "X-B3-SpanId";

    @Override
    public void execute(ShardingContext shardingContext) {

        if (logger.isDebugEnabled()) {
            logger.debug("start job job message is {}", JsonUtils.toJson(shardingContext));
        }

        final String traceId = MDC.get(TRACE_ID);
        final String spanId = MDC.get(SPAN_ID);

        Mono.defer(() -> {
                    try {
                        return doExecute(shardingContext);
                    } finally {
                        if (logger.isDebugEnabled()) {
                            logger.debug("job run complete job message is {}", JsonUtils.toJson(shardingContext));
                        }
                    }
                })
                .onErrorResume(throwable -> {
                    logger.error("[{}] 定时任务执行失败", this.getClass().getSimpleName(), throwable);
                    return notificationService.sendText(String.format("%s 定时任务执行失败 ",
                            this.getClass().getSimpleName()) + throwable.getMessage());
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    protected abstract Mono<Object> doExecute(ShardingContext shardingContext);
}
