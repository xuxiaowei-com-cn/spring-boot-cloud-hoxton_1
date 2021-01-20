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
package cn.com.xuxiaowei.ui.test.service.impl;

import cn.com.xuxiaowei.ui.test.entity.WwwPassport;
import cn.com.xuxiaowei.ui.test.hystrix.PassportHystrixService;
import cn.com.xuxiaowei.ui.test.hystrix.WwwHystrixService;
import cn.com.xuxiaowei.ui.test.service.ITestService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class TestServiceImpl implements ITestService {

    private PassportHystrixService passportHystrixService;

    private WwwHystrixService wwwHystrixService;

    @Autowired
    public void setPassportHystrixService(PassportHystrixService passportHystrixService) {
        this.passportHystrixService = passportHystrixService;
    }

    @Autowired
    public void setWwwHystrixService(WwwHystrixService wwwHystrixService) {
        this.wwwHystrixService = wwwHystrixService;
    }

    /**
     * 测试 分布式事务 seata
     *
     * @param wwwPassport 网站模块测试表
     * @return 返回 分布式事务 seata 结果
     */
    @Override
    @Transactional
    @GlobalTransactional
    public Map<String, Object> seataSave(WwwPassport wwwPassport) {
        Map<String, Object> map = new HashMap<>(4);

        Map<String, Object> saveWww = wwwHystrixService.save(wwwPassport);
        Map<String, Object> savePassport = passportHystrixService.save(wwwPassport);

        map.put("code", "00000");
        map.put("msg", "保存成功");

        map.put("saveWww", saveWww);
        map.put("savePassport", savePassport);

        return map;
    }

}
