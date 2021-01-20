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
package cn.com.xuxiaowei.ui.test.hystrix;

import cn.com.xuxiaowei.ui.test.entity.WwwPassport;
import cn.com.xuxiaowei.ui.test.exception.TestPassportException;
import cn.com.xuxiaowei.ui.test.service.PassportService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 测试 登录模块 Hystrix
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class PassportHystrixService {

    private PassportService passportService;

    @Autowired
    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

    /**
     * 测试 登录模块 参数接收、保存数据 服务实现
     *
     * @param wwwPassport 登录模块测试表，必填，否则调用失败
     * @return 返回 测试 登录模块 结果
     */
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Map<String, Object> save(WwwPassport wwwPassport) {
        return passportService.save(wwwPassport);
    }

    /**
     * 测试 登录模块 参数接收、保存数据 异常数据
     *
     * @param wwwPassport 登录模块测试表
     * @return 返回 异常 结果
     * @throws TestPassportException 测试登录模块 异常
     */
    public Map<String, Object> saveFallback(WwwPassport wwwPassport) throws TestPassportException {
        throw new TestPassportException();
    }

    /**
     * 测试阻塞
     *
     * @param mills 阻塞，毫秒
     * @return 返回 测试阻塞 结果
     */
    @HystrixCommand(fallbackMethod = "readTimeoutFallback")
    public String readTimeout(@RequestParam int mills) {
        return passportService.readTimeout(mills);
    }

    /**
     * 测试阻塞 异常数据
     *
     * @param mills 阻塞，毫秒
     * @return 返回 异常 结果
     */
    public String readTimeoutFallback(@RequestParam int mills) {
        return String.format("测试阻塞：%s ms 发生了熔断", mills);
    }

}
