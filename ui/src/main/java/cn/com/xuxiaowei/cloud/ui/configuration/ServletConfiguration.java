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
package cn.com.xuxiaowei.cloud.ui.configuration;

import cn.com.xuxiaowei.cloud.ui.properties.PatchcaDefaultProperties;
import cn.com.xuxiaowei.cloud.ui.servlet.PatchcaHttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServlet;

/**
 * Servlet、Filter 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ServletConfiguration {

    private PatchcaDefaultProperties patchcaDefaultProperties;

    private PatchcaHttpServlet patchcaHttpServlet;

    @Autowired
    public void setPatchcaDefaultProperties(PatchcaDefaultProperties patchcaDefaultProperties) {
        this.patchcaDefaultProperties = patchcaDefaultProperties;
    }

    @Autowired
    public void setPatchcaHttpServlet(PatchcaHttpServlet patchcaHttpServlet) {
        this.patchcaHttpServlet = patchcaHttpServlet;
    }

    /**
     * 设置 全自动区分计算机和人类的图灵测试 的 URL
     *
     * @return 返回 已经设置了 URL 的 全自动区分计算机和人类的图灵测试 {@link ServletRegistrationBean}
     */
    @Bean
    public ServletRegistrationBean<HttpServlet> patchcaHttpServletBean() {
        return new ServletRegistrationBean<>(patchcaHttpServlet, patchcaDefaultProperties.getUrl());
    }

}
