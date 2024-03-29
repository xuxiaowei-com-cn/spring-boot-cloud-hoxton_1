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
package cn.com.xuxiaowei.cloud.ui.test.feign;

import cn.com.xuxiaowei.cloud.ui.test.entity.TestWwwPassport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 测试 登录模块 接口
 *
 * @author xuxiaowei
 * @see FeignClient#contextId() 防止出现相同的 {@link FeignClient#value()} 时异常
 * @since 0.0.1
 */
@FeignClient(value = "passport", contextId = "testPassportService")
public interface TestPassportService {

    /**
     * 测试 登录模块 参数接收、保存数据
     *
     * @param wwwPassport 登录模块测试表，必填，否则调用失败
     * @return 返回 登录模块 测试结果
     */
    @RequestMapping("/test/passport/save")
    Map<String, Object> save(@RequestBody TestWwwPassport wwwPassport);

    /**
     * 测试阻塞
     *
     * @param mills 阻塞，毫秒
     * @return 返回 测试阻塞 结果
     */
    @RequestMapping("/test/passport/read-timeout")
    String readTimeout(@RequestParam(value = "mills") int mills);

}
