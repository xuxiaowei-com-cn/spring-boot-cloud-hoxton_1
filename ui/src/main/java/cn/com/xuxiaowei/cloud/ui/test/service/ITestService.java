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
package cn.com.xuxiaowei.cloud.ui.test.service;

import cn.com.xuxiaowei.cloud.ui.test.entity.TestWwwPassport;

import java.util.Map;

/**
 * 测试接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface ITestService {

    /**
     * 测试 分布式事务 seata
     *
     * @param wwwPassport 网站模块测试表
     * @return 返回 分布式事务 seata 结果
     */
    Map<String, Object> seataSave(TestWwwPassport wwwPassport);

}
