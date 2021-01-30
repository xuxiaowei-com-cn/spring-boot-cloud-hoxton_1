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
package cn.com.xuxiaowei.cloud.utils.security.configuration;

import cn.com.xuxiaowei.cloud.utils.security.properties.CorsDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Security CORS 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Profile("cors")
@Configuration
public class SecurityCorsConfiguration {

    private CorsDefaultProperties corsDefaultProperties;

    @Autowired
    public void setCorsDefaultProperties(CorsDefaultProperties corsDefaultProperties) {
        this.corsDefaultProperties = corsDefaultProperties;
    }

    /**
     * Security 跨域配置
     * <p>
     * 若在 {@link WebMvcConfigurer} 设置无效，需要在 {@link WebSecurityConfigurerAdapter} 或 ResourceServerConfigurerAdapter 中设置
     *
     * @return 返回 Security 跨域配置
     */
    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(corsDefaultProperties.getAllowedOrigins()));
        corsConfiguration.setAllowCredentials(corsDefaultProperties.isAllowCredentials());
        corsConfiguration.setAllowedMethods(Arrays.asList(corsDefaultProperties.getAllowedMethods()));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration(corsDefaultProperties.getAddMapping(), corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

}
