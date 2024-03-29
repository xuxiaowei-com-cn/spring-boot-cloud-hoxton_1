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
import cn.com.xuxiaowei.cloud.ui.test.hystrix.TestPassportHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    private TestPassportHystrixService passportHystrixService;

    @Autowired
    public void setPassportHystrixService(TestPassportHystrixService passportHystrixService) {
        this.passportHystrixService = passportHystrixService;
    }

    /**
     * 测试 登录模块 参数接收、保存数据 接口
     *
     * @param request     请求
     * @param response    响应
     * @param session     session
     * @param wwwPassport 登录模块测试表，必填，否则调用失败
     * @return 返回 测试 登录模块 结果
     */
    @RequestMapping(value = "/save")
    public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                    @RequestBody TestWwwPassport wwwPassport) {
        return passportHystrixService.save(wwwPassport);
    }

    /**
     * 测试阻塞
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @param mills    阻塞，毫秒
     * @return 返回 测试阻塞 结果
     */
    @RequestMapping("/read-timeout")
    public String readTimeout(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam int mills) {
        return passportHystrixService.readTimeout(mills);
    }

}
