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

import cn.com.xuxiaowei.cloud.utils.security.filter.CsrfCookieBeforeOncePerRequestFilter;
import cn.com.xuxiaowei.cloud.utils.security.properties.CsrfCookieDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * WebSecurity 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private CsrfTokenRepository csrfTokenRepository;

    private CsrfCookieDefaultProperties csrfCookieDefaultProperties;

    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource;

    @Autowired
    public void setCsrfTokenRepository(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Autowired
    public void setCsrfCookieDefaultProperties(CsrfCookieDefaultProperties csrfCookieDefaultProperties) {
        this.csrfCookieDefaultProperties = csrfCookieDefaultProperties;
    }

    @Autowired
    public void setUrlBasedCorsConfigurationSource(UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource) {
        this.urlBasedCorsConfigurationSource = urlBasedCorsConfigurationSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CSRF 配置
        http.csrf().csrfTokenRepository(csrfTokenRepository);
        http.addFilterBefore(new CsrfCookieBeforeOncePerRequestFilter(csrfTokenRepository, csrfCookieDefaultProperties),
                CsrfFilter.class);

        // Security CORS 跨域配置
        http.cors().configurationSource(urlBasedCorsConfigurationSource);

    }

}
