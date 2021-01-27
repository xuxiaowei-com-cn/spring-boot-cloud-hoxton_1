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
package cn.com.xuxiaowei.cloud.ui.test.hystrix;

import cn.com.xuxiaowei.cloud.ui.test.entity.TestWwwPassport;
import cn.com.xuxiaowei.cloud.ui.test.exception.TestPassportException;
import cn.com.xuxiaowei.cloud.ui.test.feign.TestPassportService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.*;

/**
 * 测试 登录模块 Hystrix
 *
 * @author xuxiaowei
 * @see DefaultProperties 类级别注解
 * @see HystrixCommand#fallbackMethod() 后备方法
 * @see HystrixCommand#commandProperties() Hystrix 命令属性
 * @see HystrixPropertiesManager#EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS 执行隔离线程超时（毫秒）
 * @see HystrixCommand#threadPoolKey() 线程池的名称，该方法超时时间较长，在默认情况下，所有的 Hystrix 命令都将共享同一个线程池来处理请求。
 * 这个线程池共有10个线程。
 * 在存在大量请求的情况下，一个服务出现性能问题将导致 java 容器崩溃。
 * 舱壁模式将远程资源调用隔离在他们自己的线程池中，以便控制单个表现不佳的服务，而不会使容器崩溃。
 * @see HystrixCommand#threadPoolProperties 定义线程池
 * @see HystrixPropertiesManager#CORE_SIZE 设置线程池的大小。
 * 自定义线程池的大小。
 * Netflix 推荐公式：
 * 服务在健康状态下每秒支撑的最大请求 x 第 99 百分位延迟时间（以秒为单位） + 用于缓冲的少量额外线程
 * @see HystrixPropertiesManager#MAX_QUEUE_SIZE 线程池的队列中繁忙时允许堵塞的最大请求。
 * 一旦请求超过队列大小，线程池的任何其他请求豆浆失败，直至队列中有空间。
 * 1、如果将值设置为 -1，则将使用 java {@link SynchronousQueue} 来保存所有传入的请求。
 * 同步队列本质上会强制要求正在处理的请求数量永远不能超过线程池中可用线程的数量。
 * 如果将 {@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 设置为大于 1 的值将导致 Hystrix 使用 Java {@link LinkedBlockingDeque}。
 * {@link LinkedBlockingDeque} 的使用允许开发人员即使所有线程都在忙于处理请求，也能对请求进行排队。
 * 2、{@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 属性只能在线程池初次初始化时设置（例如，在应用程序启动时）。
 * Hystrix 允许通过 {@link HystrixPropertiesManager#QUEUE_SIZE_REJECTION_THRESHOLD} 属性来动态更改队列的大小，但只有在 {@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 属性的值大于 0 时，才能设置此属性。
 * @since 0.0.1
 */
@Slf4j
@Service
public class TestPassportHystrixService {

    private TestPassportService passportService;

    @Autowired
    public void setPassportService(TestPassportService passportService) {
        this.passportService = passportService;
    }

    /**
     * 测试 登录模块 参数接收、保存数据 服务实现
     *
     * @param wwwPassport 登录模块测试表，必填，否则调用失败
     * @return 返回 测试 登录模块 结果
     */
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Map<String, Object> save(TestWwwPassport wwwPassport) {
        return passportService.save(wwwPassport);
    }

    /**
     * 测试 登录模块 参数接收、保存数据 异常数据
     *
     * @param wwwPassport 登录模块测试表
     * @return 返回 异常 结果
     * @throws TestPassportException 测试登录模块 异常
     */
    public Map<String, Object> saveFallback(TestWwwPassport wwwPassport) throws TestPassportException {
        throw new TestPassportException();
    }

    /**
     * 测试阻塞
     * <p>
     * 超时：5s
     *
     * @param mills 阻塞，毫秒
     * @return 返回 测试阻塞 结果
     * @see HystrixCommand#fallbackMethod() 后备方法
     * @see HystrixCommand#commandProperties() Hystrix 命令属性
     * @see HystrixPropertiesManager#EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS 执行隔离线程超时（毫秒）
     * @see HystrixCommand#threadPoolKey() 线程池的名称，该方法超时时间较长，在默认情况下，所有的 Hystrix 命令都将共享同一个线程池来处理请求。
     * 这个线程池共有10个线程。
     * 在存在大量请求的情况下，一个服务出现性能问题将导致 java 容器崩溃。
     * 舱壁模式将远程资源调用隔离在他们自己的线程池中，以便控制单个表现不佳的服务，而不会使容器崩溃。
     * @see HystrixCommand#threadPoolProperties 定义线程池
     * @see HystrixPropertiesManager#CORE_SIZE 设置线程池的大小。
     * 自定义线程池的大小。
     * Netflix 推荐公式：
     * 服务在健康状态下每秒支撑的最大请求 x 第 99 百分位延迟时间（以秒为单位） + 用于缓冲的少量额外线程
     * @see HystrixPropertiesManager#MAX_QUEUE_SIZE 线程池的队列中繁忙时允许堵塞的最大请求。
     * 一旦请求超过队列大小，线程池的任何其他请求豆浆失败，直至队列中有空间。
     * 1、如果将值设置为 -1，则将使用 java {@link SynchronousQueue} 来保存所有传入的请求。
     * 同步队列本质上会强制要求正在处理的请求数量永远不能超过线程池中可用线程的数量。
     * 如果将 {@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 设置为大于 1 的值将导致 Hystrix 使用 Java {@link LinkedBlockingDeque}。
     * {@link LinkedBlockingDeque} 的使用允许开发人员即使所有线程都在忙于处理请求，也能对请求进行排队。
     * 2、{@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 属性只能在线程池初次初始化时设置（例如，在应用程序启动时）。
     * Hystrix 允许通过 {@link HystrixPropertiesManager#QUEUE_SIZE_REJECTION_THRESHOLD} 属性来动态更改队列的大小，但只有在 {@link HystrixPropertiesManager#MAX_QUEUE_SIZE} 属性的值大于 0 时，才能设置此属性。
     */
    @HystrixCommand(fallbackMethod = "readTimeoutFallback",
            commandProperties = {
                    @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000")
            },
            threadPoolKey = "passport_read_timeout_thread_pool",
            threadPoolProperties = {
                    @HystrixProperty(name = CORE_SIZE, value = "30"),
                    @HystrixProperty(name = MAX_QUEUE_SIZE, value = "10")
            }
    )
    public String readTimeout(@RequestParam int mills) {
        log.info("进入测试阻塞");
        String msg = passportService.readTimeout(mills);
        log.info("完成测试阻塞");
        return msg;
    }

    /**
     * 测试阻塞 异常数据
     *
     * @param mills 阻塞，毫秒
     * @return 返回 异常 结果
     */
    public String readTimeoutFallback(@RequestParam int mills) {
        String msg = String.format("测试阻塞：%s ms 发生了熔断", mills);
        log.error(msg);
        return msg;
    }

}
