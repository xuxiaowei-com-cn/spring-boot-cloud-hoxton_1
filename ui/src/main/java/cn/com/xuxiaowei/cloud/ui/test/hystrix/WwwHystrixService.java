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

import cn.com.xuxiaowei.cloud.ui.test.entity.WwwPassport;
import cn.com.xuxiaowei.cloud.ui.test.exception.TestWwwException;
import cn.com.xuxiaowei.cloud.ui.test.feign.WwwService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 测试 网站模块 Hystrix
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class WwwHystrixService {

    private WwwService wwwService;

    @Autowired
    public void setWwwService(WwwService wwwService) {
        this.wwwService = wwwService;
    }

    /**
     * 测试 网站模块 参数接收、保存数据 服务实现
     *
     * @param wwwPassport 网站模块测试表，必填，否则调用失败
     * @return 返回 测试 网站模块 结果
     */
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Map<String, Object> save(WwwPassport wwwPassport) {
        return wwwService.save(wwwPassport);
    }

    /**
     * 测试 网站模块 参数接收、保存数据 异常数据
     *
     * @param wwwPassport 网站模块测试表
     * @return 返回 异常 结果
     * @throws TestWwwException 测试网站模块 异常
     */
    public Map<String, Object> saveFallback(WwwPassport wwwPassport) throws TestWwwException {
        throw new TestWwwException();
    }

}
