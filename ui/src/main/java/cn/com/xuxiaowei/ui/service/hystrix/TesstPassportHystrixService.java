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
package cn.com.xuxiaowei.ui.service.hystrix;

import cn.com.xuxiaowei.ui.service.TesstPassportService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 登录模块 Hystrix
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class TesstPassportHystrixService {

    private TesstPassportService tesstPassportService;

    @Autowired
    public void setTesstPassportService(TesstPassportService tesstPassportService) {
        this.tesstPassportService = tesstPassportService;
    }

    /**
     * 测试 登录模块 参数接收 服务实现
     *
     * @param testMsg 测试参数，必填，否则调用失败
     * @return 返回 测试 登录模块 结果
     */
    @HystrixCommand(fallbackMethod = "testMsgFallback")
    public Map<String, Object> testMsg(String testMsg) {
        return tesstPassportService.testMsg(testMsg);
    }

    /**
     * 测试 登录模块 参数接收 异常数据
     *
     * @param testMsg 测试参数
     * @return 返回 异常 结果
     */
    public Map<String, Object> testMsgFallback(String testMsg) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "A001");
        map.put("msg", "调用失败，Passport Service 故障");
        return map;
    }

}
