package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConfigJobTaskTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-job.cfg.xml");

    @Test
    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    public void test() throws InterruptedException {
        // 流程定义了定时任务启动，那么这里就不需要手动启动了。
        log.info("start");
        List<Job> jobList = activitiRule.getManagementService()
                .createTimerJobQuery().listPage(0, 100);
        for (Job job : jobList) {
            log.info("定时任务 {}，重试次数 = {}", job, job.getRetries());
        }
        log.info("jobList.size = {}", jobList.size());
        TimeUnit.SECONDS.sleep(100);
        log.info("end");
    }
}
