package com.g7.framework.job.reactive.rdb;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbIdentity;

import java.io.Serializable;

/**
 * Created by dreamyao on 2018/11/13.
 */
public class JobEventRdbConfiguration extends JobEventRdbIdentity implements JobEventConfiguration, Serializable {

    private static final long serialVersionUID = 3344410699286435226L;

    @Override
    public JobEventListener createJobEventListener() {
        return new JobEventRdbListener();
    }
}
