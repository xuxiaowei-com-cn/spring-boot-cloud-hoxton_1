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
package cn.com.xuxiaowei.passport.service;

import cn.com.xuxiaowei.passport.entity.TestPassport;

import java.util.List;

/**
 * 测试登录模块 服务 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface TestPassportService {

    /**
     * 获取所有数据
     *
     * @return 返回 所有数据
     */
    List<TestPassport> list();

}
