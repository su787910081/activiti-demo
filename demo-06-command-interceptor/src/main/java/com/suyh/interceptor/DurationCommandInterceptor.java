package com.suyh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;

/**
 * 实现一个执行时间的拦截器
 */
@Slf4j
public class DurationCommandInterceptor extends AbstractCommandInterceptor {
    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            this.getNext().execute(config, command);
        } finally {
            long last = System.currentTimeMillis();
            long duration = last - start;
            log.info("{} 执行时长 {} 毫秒", command.getClass().getSimpleName(), duration);
        }

        return null;
    }
}
