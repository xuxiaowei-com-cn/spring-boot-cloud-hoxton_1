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
package cn.com.xuxiaowei.ui.controller;

import cn.com.xuxiaowei.ui.service.hystrix.TesstPassportHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 测试 登录 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/test/passport")
public class TestPassportRestController {

    private TesstPassportHystrixService tesstPassportHystrixService;

    @Autowired
    public void setTesstPassportHystrixService(TesstPassportHystrixService tesstPassportHystrixService) {
        this.tesstPassportHystrixService = tesstPassportHystrixService;
    }

    /**
     * 测试 登录模块 接口
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @return 返回 测试 登录模块 结果
     */
    @RequestMapping(value = "/testMsg")
    public Map<String, Object> testMsg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("session id：{}", session.getId());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowFormat = now.format(dateTimeFormatter);
        return tesstPassportHystrixService.testMsg(nowFormat);
    }

    /**
     * 测试 登录模块 参数接收
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @param testMsg  测试参数，必填
     * @return 返回 测试 登录模块 结果
     */
    @RequestMapping(value = "/testMsg", params = {"testMsg"})
    public Map<String, Object> testMsg(HttpServletRequest request, HttpServletResponse response, HttpSession session, String testMsg) {

        log.info("session id：{}", session.getId());

        return tesstPassportHystrixService.testMsg(testMsg);
    }

}
