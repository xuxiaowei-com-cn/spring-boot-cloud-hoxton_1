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
package cn.com.xuxiaowei.cloud.ui.test.controller;

import cn.com.xuxiaowei.cloud.ui.test.entity.TestWwwPassport;
import cn.com.xuxiaowei.cloud.ui.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 测试 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/test")
public class TestRestController {

    private ITestService testService;

    @Autowired
    public void setTestService(ITestService testService) {
        this.testService = testService;
    }

    /**
     * 测试 分布式事务 seata
     *
     * @param request     请求
     * @param response    响应
     * @param session     session
     * @param wwwPassport 网站模块测试表，必填，否则调用失败
     * @return 返回 测试 分布式事务 seata 结果
     */
    @RequestMapping(value = "/seata/save")
    public Map<String, Object> seataSave(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                         @RequestBody TestWwwPassport wwwPassport) {
        return testService.seataSave(wwwPassport);
    }

}
