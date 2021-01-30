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

import cn.com.xuxiaowei.cloud.utils.security.properties.CsrfCookieDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

/**
 * Security CSRF 策略 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Profile("csrf-cookie")
@EnableWebSecurity
@Configuration
public class WebSecurityConfigurerAdapterCookieCsrfConfiguration extends WebSecurityConfigurerAdapter {

    private CsrfCookieDefaultProperties csrfCookieDefaultProperties;

    @Autowired
    public void setCsrfCookieDefaultProperties(CsrfCookieDefaultProperties csrfCookieDefaultProperties) {
        this.csrfCookieDefaultProperties = csrfCookieDefaultProperties;
    }

    /**
     * CSRF 策略
     * <p>
     * 默认为懒加载 {@link LazyCsrfTokenRepository}，会出现浏览器首次打开时，提交不了数据
     * <p>
     * 此 Cookie 默认为 HttpOnly = true（不可读取），
     * 请勿修改为 HttpOnly = false（可读取），否则安全性无法保障
     * <p>
     *
     * @return 返回可使用 {@link Autowired} 的 CSRF 策略
     * @see CsrfFilter
     */
    @Bean
    public CookieCsrfTokenRepository cookieCsrfTokenRepository() {
        CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
        cookieCsrfTokenRepository.setCookieName(csrfCookieDefaultProperties.getCookieName());
        cookieCsrfTokenRepository.setCookieHttpOnly(csrfCookieDefaultProperties.isCookieHttpOnly());
        cookieCsrfTokenRepository.setHeaderName(csrfCookieDefaultProperties.getHeaderName());
        cookieCsrfTokenRepository.setCookiePath(csrfCookieDefaultProperties.getCookiePath());
        cookieCsrfTokenRepository.setParameterName(csrfCookieDefaultProperties.getParameterName());
        return cookieCsrfTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CSRF 策略（默认为懒加载）
        http.csrf().csrfTokenRepository(cookieCsrfTokenRepository());

    }

}
