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
package cn.com.xuxiaowei.passport.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestRestController {

    /**
     * 测试 参数接收
     *
     * @param request  请求
     * @param response 响应
     * @param session  session
     * @param testMsg  测试参数
     * @return 返回 测试结果
     */
    @RequestMapping("/testMsg")
    public Map<String, Object> testMsg(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       @RequestBody String testMsg) {

        log.info("session id：{}", session.getId());

        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "00000");
        map.put("msg", String.format("Passport 接收到参数：%s", testMsg));
        return map;
    }

}
