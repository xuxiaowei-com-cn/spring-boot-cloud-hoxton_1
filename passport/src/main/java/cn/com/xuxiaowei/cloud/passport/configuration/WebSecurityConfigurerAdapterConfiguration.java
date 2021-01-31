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
package cn.com.xuxiaowei.cloud.passport.configuration;

import cn.com.xuxiaowei.cloud.passport.properties.HttpSecurityFormLoginDefaultProperties;
import cn.com.xuxiaowei.cloud.utils.security.filter.CsrfCookieBeforeOncePerRequestFilter;
import cn.com.xuxiaowei.cloud.utils.security.properties.CsrfCookieDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

/**
 * WebSecurity 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    private CsrfTokenRepository csrfTokenRepository;

    private CsrfCookieDefaultProperties csrfCookieDefaultProperties;

    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource;

    private HttpSecurityFormLoginDefaultProperties httpSecurityFormLoginDefaultProperties;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    @Autowired
    public void setHttpSecurityFormLoginDefaultProperties(HttpSecurityFormLoginDefaultProperties httpSecurityFormLoginDefaultProperties) {
        this.httpSecurityFormLoginDefaultProperties = httpSecurityFormLoginDefaultProperties;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    /**
     * @see super#configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Form Login 配置
        http.formLogin()
                // 登录页面
                .loginPage(httpSecurityFormLoginDefaultProperties.getLoginPage())
                // 登录请求
                .loginProcessingUrl(httpSecurityFormLoginDefaultProperties.getLoginProcessingUrl())
                // 登录成功
                .successForwardUrl(httpSecurityFormLoginDefaultProperties.getSuccessForwardUrl())
                // 登录失败
                .failureForwardUrl(httpSecurityFormLoginDefaultProperties.getFailureForwardUrl())
                .permitAll()
        ;

        // 权限配置
        http.authorizeRequests().anyRequest().authenticated();

        // CSRF 配置
        http.csrf().csrfTokenRepository(csrfTokenRepository);
        http.addFilterBefore(new CsrfCookieBeforeOncePerRequestFilter(csrfTokenRepository, csrfCookieDefaultProperties),
                CsrfFilter.class);

        // Security CORS 配置
        http.cors().configurationSource(urlBasedCorsConfigurationSource);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 用户密码编辑器
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 查询登录用户
        auth.userDetailsService(jdbcDaoImpl()).passwordEncoder(delegatingPasswordEncoder);

    }

    /**
     * 登录时查询用户
     */
    public JdbcDaoImpl jdbcDaoImpl() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

}
