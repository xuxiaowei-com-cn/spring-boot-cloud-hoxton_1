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
package cn.com.xuxiaowei.ui.test.controller;

import cn.com.xuxiaowei.ui.test.entity.WwwPassport;
import cn.com.xuxiaowei.ui.test.service.hystrix.PassportHystrixService;
import cn.com.xuxiaowei.ui.test.service.hystrix.WwwHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RestController
@RequestMapping("/test")
public class TestRestController {

    private WwwHystrixService wwwHystrixService;

    private PassportHystrixService passportHystrixService;

    @Autowired
    public void setWwwHystrixService(WwwHystrixService wwwHystrixService) {
        this.wwwHystrixService = wwwHystrixService;
    }

    @Autowired
    public void setPassportHystrixService(PassportHystrixService passportHystrixService) {
        this.passportHystrixService = passportHystrixService;
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
    @Transactional
    @RequestMapping(value = "/seata/save")
    public Map<String, Object> seataSave(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                         @RequestBody WwwPassport wwwPassport) {
        Map<String, Object> map = new HashMap<>(4);

        Map<String, Object> saveWww = wwwHystrixService.save(wwwPassport);
        Map<String, Object> savePassport = passportHystrixService.save(wwwPassport);

        map.put("saveWww", saveWww);
        map.put("savePassport", savePassport);

        return map;
    }

}
