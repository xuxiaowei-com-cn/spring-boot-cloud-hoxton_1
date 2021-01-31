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
package cn.com.xuxiaowei.cloud.ui.passport.feign;

import cn.com.xuxiaowei.cloud.utils.http.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录模块 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@FeignClient("passport")
public interface PassportService {

    /**
     * 登录模块 登录页面
     *
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 记住我
     * @return 返回 登录模块 登录页面
     */
    @PostMapping("/login")
    Response login(@RequestParam String username, @RequestParam String password, @RequestParam Boolean rememberMe);

}
