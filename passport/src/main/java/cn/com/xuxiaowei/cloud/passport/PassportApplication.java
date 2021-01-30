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
package cn.com.xuxiaowei.cloud.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 登录 程序执行入口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@EnableWebSecurity
@EnableResourceServer
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(value = {
        "cn.com.xuxiaowei.cloud.passport",
        "cn.com.xuxiaowei.cloud.mybatis",
        "cn.com.xuxiaowei.cloud.utils.security"
})
public class PassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassportApplication.class, args);
    }

}
