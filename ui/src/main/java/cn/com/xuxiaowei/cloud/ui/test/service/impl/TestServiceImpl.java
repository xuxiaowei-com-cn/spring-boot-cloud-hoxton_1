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
package cn.com.xuxiaowei.cloud.ui.test.service.impl;

import cn.com.xuxiaowei.cloud.ui.test.entity.TestWwwPassport;
import cn.com.xuxiaowei.cloud.ui.test.hystrix.TestPassportHystrixService;
import cn.com.xuxiaowei.cloud.ui.test.hystrix.TestWwwHystrixService;
import cn.com.xuxiaowei.cloud.ui.test.service.ITestService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class TestServiceImpl implements ITestService {

    private TestPassportHystrixService passportHystrixService;

    private TestWwwHystrixService wwwHystrixService;

    @Autowired
    public void setPassportHystrixService(TestPassportHystrixService passportHystrixService) {
        this.passportHystrixService = passportHystrixService;
    }

    @Autowired
    public void setWwwHystrixService(TestWwwHystrixService wwwHystrixService) {
        this.wwwHystrixService = wwwHystrixService;
    }

    /**
     * 测试 分布式事务 seata
     *
     * @param wwwPassport 网站模块测试表
     * @return 返回 分布式事务 seata 结果
     */
    @DS("master")
    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public Map<String, Object> seataSave(TestWwwPassport wwwPassport) {

        log.info("当前 XID: {}", RootContext.getXID());

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
