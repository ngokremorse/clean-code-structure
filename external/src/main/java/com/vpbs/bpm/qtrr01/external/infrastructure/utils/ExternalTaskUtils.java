package com.vpbs.bpm.qtrr01.external.infrastructure.utils;

import org.camunda.bpm.client.task.ExternalTask;

public class ExternalTaskUtils {

    public static Integer getRetries(ExternalTask externalTask, Integer maxRetries) {
        Integer retries = externalTask.getRetries();
        if(retries == null) {
            return  maxRetries;
        }
        return retries - 1;
    }
}
