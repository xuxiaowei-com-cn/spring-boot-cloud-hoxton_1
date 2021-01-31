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
import cn.com.xuxiaowei.cloud.utils.http.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试 登录模块 Hystrix
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class PassportHystrixService {

    private PassportService passportService;

    @Autowired
    public void setPassportService(PassportService passportService) {
        this.passportService = passportService;
    }

    /**
     * Session ID
     *
     * @param session HttpSession
     * @return 返回 Session ID
     */
    @HystrixCommand(fallbackMethod = "sessionIdFallback")
    public Map<String, Object> sessionId(HttpSession session) {
        Map<String, Object> map = new HashMap<>(4);
        String response = passportService.sessionId();
        map.put("msg", "测试模块正常");
        map.put("ui Session ID", session.getId());
        map.put("passport", response);
        return map;
    }

    /**
     * Session ID
     *
     * @param session HttpSession
     * @return 返回 Session ID
     */
    public Map<String, Object> sessionIdFallback(HttpSession session) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("msg", "测试模块异常");
        map.put("ui Session ID", session.getId());
        return map;
    }

    /**
     * 登录模块 登录页面
     *
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 记住我
     * @return 返回 登录模块 登录页面
     */
    @HystrixCommand(fallbackMethod = "loginFallback")
    public Response login(String username, String password, Boolean rememberMe) {
        return passportService.login(username, password, rememberMe);
    }

    public Response loginFallback(String username, String password, Boolean rememberMe) {
        Response response = new Response();
        response.setCode("A001");
        response.setMsg("登录模块异常");
        return response;
    }

}
