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
package cn.com.xuxiaowei.cloud.ui.passport.controller;

import cn.com.xuxiaowei.cloud.ui.passport.hystrix.PassportHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Passport
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/passport")
public class PassportRestController {

    private PassportHystrixService passportHystrixService;

    @Autowired
    public void setPassportHystrixService(PassportHystrixService passportHystrixService) {
        this.passportHystrixService = passportHystrixService;
    }

    /**
     * Session ID
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @return 返回 Session ID
     */
    @RequestMapping("/sessionId")
    public Map<String, Object> sessionId(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return passportHystrixService.sessionId(session);
    }

}
