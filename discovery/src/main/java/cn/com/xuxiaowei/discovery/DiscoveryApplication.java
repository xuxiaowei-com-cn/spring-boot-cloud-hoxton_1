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
package cn.com.xuxiaowei.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.EurekaController;

/**
 * 服务发现 程序执行入口
 * <p>
 * Spring Cloud 使用注解 {@link EnableEurekaServer} 的方式提供 Eureka 服务端
 *
 * @author xuxiaowei
 * @see EurekaController
 * @see <a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#spring-cloud-running-eureka-server">spring-cloud-running-eureka-server</a>
 * @since 0.0.1
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

}
