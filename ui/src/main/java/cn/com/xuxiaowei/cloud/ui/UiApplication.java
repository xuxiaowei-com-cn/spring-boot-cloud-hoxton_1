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
package cn.com.xuxiaowei.cloud.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * UI(Ribbon、Feign)(界面模块) 程序执行入口
 * <p>
 * 通过 {@link EnableResourceServer} 开启资源保护
 * 通过 {@link EnableEurekaClient} 开启 Eureka 客户端。告诉服务，它应该使用 Eureka 服务发现代理注册本身，并且服务调用是使用服务发现来“查找”远程服务的位置的。
 * 通过 {@link EnableFeignClients} 开启 feign 客户端支持
 * 通过 {@link EnableCircuitBreaker} 开启 CircuitBreaker 的支持。使服务能够使用 Hystrix 和 Ribbon 库。
 * 通过 {@link EnableZuulProxy} 开启网关代理的支持
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#how-to-include-hystrix">how-to-include-hystrix</a>
 * @see <a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#netflix-zuul-reverse-proxy">netflix-zuul-reverse-proxy</a>
 * @since 0.0.1
 */
@EnableResourceServer
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
