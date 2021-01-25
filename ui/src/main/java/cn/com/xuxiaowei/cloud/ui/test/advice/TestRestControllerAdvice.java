/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.cloud.ui.test.advice;

import cn.com.xuxiaowei.cloud.ui.test.exception.TestPassportException;
import cn.com.xuxiaowei.cloud.ui.test.exception.TestWwwException;
import io.seata.common.exception.FrameworkException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 异常拦截
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestControllerAdvice(basePackages = {"cn.com.xuxiaowei.cloud.ui.test"})
public class TestRestControllerAdvice {

    /**
     * 测试网站模块 异常
     *
     * @param e 异常
     * @return 返回 异常数据
     */
    @ExceptionHandler(TestWwwException.class)
    public Map<String, Object> testWwwException(TestWwwException e) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "A001");
        map.put("msg", e.getMessage());
        return map;
    }

    /**
     * 测试登录模块 异常
     *
     * @param e 异常
     * @return 返回 异常数据
     */
    @ExceptionHandler(TestPassportException.class)
    public Map<String, Object> testPassportException(TestPassportException e) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "B001");
        map.put("msg", e.getMessage());
        return map;
    }

    /**
     * 分布式事务 异常
     *
     * @param e 异常
     * @return 返回 分布式事务 异常数据
     */
    @ExceptionHandler(FrameworkException.class)
    public Map<String, Object> frameworkException(FrameworkException e) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "C001");
        map.put("msg", e.getMessage());
        return map;
    }

}