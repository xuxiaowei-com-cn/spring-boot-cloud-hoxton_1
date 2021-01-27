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
package cn.com.xuxiaowei.cloud.ui.passport.hystrix;

import cn.com.xuxiaowei.cloud.ui.passport.feign.PassportService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试 登录模块 Hystrix
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
@DefaultProperties(defaultFallback = "loginFallback")
public class PassportHystrixService {

    private PassportService passportService;

    @Autowired
    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

    /**
     * 登录模块 登录页面
     *
     * @return 返回 登录模块 登录页面
     */
    @HystrixCommand
    public String login() {
        return passportService.login();
    }

    public String loginFallback() {
        return "登录模块异常";
    }

}
