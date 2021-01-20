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
package cn.com.xuxiaowei.ui;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * UI(Ribbon、Feign)(界面模块) 程序执行入口
 * <p>
 * 通过 {@link EnableEurekaClient} 开启 Eureka 客户端
 * 通过 {@link EnableFeignClients} 开启 feign 客户端支持
 * 通过 {@link EnableCircuitBreaker} 开启 CircuitBreaker 的支持
 * 通过 {@link EnableZuulProxy} 开启网关代理的支持
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#how-to-include-hystrix">how-to-include-hystrix</a>
 * @see <a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#netflix-zuul-reverse-proxy">netflix-zuul-reverse-proxy</a>
 * @since 0.0.1
 */
@GlobalTransactional
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableZuulProxy
@SpringBootApplication
public class UiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

}
