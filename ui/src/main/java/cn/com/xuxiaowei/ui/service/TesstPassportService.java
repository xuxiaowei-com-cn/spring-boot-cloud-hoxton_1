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
package cn.com.xuxiaowei.ui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 测试 登录模块 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@FeignClient("passport")
public interface TesstPassportService {

    /**
     * 测试 登录模块 参数接收
     *
     * @param testMsg 测试参数，必填，否则调用失败
     * @return 返回 登录模块 测试结果
     */
    @RequestMapping("/test/testMsg")
    Map<String, Object> testMsg(String testMsg);

}
