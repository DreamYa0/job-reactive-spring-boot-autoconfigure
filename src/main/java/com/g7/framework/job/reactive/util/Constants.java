package com.g7.framework.job.reactive.util;

/**
 * Created by dreamyao on 2018/11/9.
 */
public interface Constants {

    /***作业类型配置**********/
    String JOB_TYPE_SIMPLE = "SIMPLE";
    String JOB_TYPE_DATA_FLOW = "DATAFLOW";
    String JOB_TYPE_SCRIPT = "SCRIPT";

    /***历史清理作业配置*****/

    /**
     * 历史清理作业名称
     */
    String CLEAN_JOB_NAME = "CleanHistoryDataJob";

    /**
     * 自定义异常类
     */
    String JOB_EXCEPTION_HANDLER = "com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler";

    /**
     * 自定义业务处理线程池
     */
    String EXECUTOR_SERVICE_HANDLER = "com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler";


}
