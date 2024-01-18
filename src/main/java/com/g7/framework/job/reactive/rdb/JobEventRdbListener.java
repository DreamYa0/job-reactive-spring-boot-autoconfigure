package com.g7.framework.job.reactive.rdb;

import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbIdentity;
import com.dangdang.ddframe.job.event.type.JobExecutionEvent;
import com.dangdang.ddframe.job.event.type.JobStatusTraceEvent;

/**
 * Created by dreamyao on 2018/11/13.
 */
public final class JobEventRdbListener extends JobEventRdbIdentity implements JobEventListener {

    @Override
    public void listen(JobExecutionEvent jobExecutionEvent) {

    }

    @Override
    public void listen(JobStatusTraceEvent jobStatusTraceEvent) {

    }
}
