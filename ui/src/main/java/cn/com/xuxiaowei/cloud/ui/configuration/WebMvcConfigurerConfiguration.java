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

import cn.com.xuxiaowei.cloud.ui.properties.CorsDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebMvcConfigurerConfiguration implements WebMvcConfigurer {

    private CorsDefaultProperties corsDefaultProperties;

    @Autowired
    public void setCorsDefaultProperties(CorsDefaultProperties corsDefaultProperties) {
        this.corsDefaultProperties = corsDefaultProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 允许路径，需要提供协议、端口
        registry.addMapping(corsDefaultProperties.getAddMapping())
                // 允许跨域的域
                .allowedOrigins(corsDefaultProperties.getAllowedOrigins())
                // 默认为 GET, HEAD, POST
                // 可以为 GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
                .allowedMethods(corsDefaultProperties.getAllowedMethods())
                // 默认为 1800秒（30分钟）
                .maxAge(corsDefaultProperties.getMaxAge())
                // 浏览器是否应将凭据（如Cookie和跨域请求）
                .allowCredentials(corsDefaultProperties.isAllowCredentials());

    }

}
