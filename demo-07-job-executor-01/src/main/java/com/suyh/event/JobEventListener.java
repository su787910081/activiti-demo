package com.suyh.event;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;

/**
 * 对自定义事件的监听
 * 在有些时候我们可以直接使用activiti 提供的一个抽象类(BaseEntityEventListener)来做事件监听处理
 *
 */
@Slf4j
@NoArgsConstructor
public class JobEventListener implements ActivitiEventListener {
    private String from = "配置文件";

    public JobEventListener(String from) {
        this.from = from;
    }

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();
        String name = eventType.name();
        if (name.startsWith("TIMER") || name.startsWith("JOB")) {
            log.info("通过[{}] 监听到事件 {}     {}", from, eventType, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
