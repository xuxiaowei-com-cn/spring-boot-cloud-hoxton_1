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

import cn.com.xuxiaowei.passport.entity.TestPassport;
import cn.com.xuxiaowei.passport.service.ITestPassportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/test/passport")
public class TestPassportRestController {

    private ITestPassportService testPassportService;

    @Autowired
    public void setTestPassportService(ITestPassportService testPassportService) {
        this.testPassportService = testPassportService;
    }

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
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "00000");
        map.put("msg", String.format("Passport 接收到参数：%s", testMsg));
        map.put("Passport Session ID", session.getId());
        return map;
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
        log.info("Passport 服务-测试阻塞：{} ms", mills);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Passport 服务-测试阻塞完成";
    }

    /**
     * 查询数据
     *
     * @param request  请求
     * @param response 响应
     * @param session  Session
     * @return 返回 查询数据结果
     */
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<TestPassport> testPassportList = testPassportService.list();
        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put("data", data);
        map.put("code", "00000");
        map.put("msg", "查询成功");
        data.put("testPassportList", testPassportList);
        return map;
    }

}
