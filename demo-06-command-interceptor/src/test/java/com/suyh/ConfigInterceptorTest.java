package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

@Slf4j
public class ConfigInterceptorTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-interceptor.cfg.xml");

    @Test
    @Deployment(resources = {"my-process-interceptor.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());
    }
}
