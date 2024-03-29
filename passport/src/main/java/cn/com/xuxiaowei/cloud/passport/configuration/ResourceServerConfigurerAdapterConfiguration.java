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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 资源服务器 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ResourceServerConfigurerAdapterConfiguration extends ResourceServerConfigurerAdapter {

    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource;

    @Autowired
    public void setUrlBasedCorsConfigurationSource(UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource) {
        this.urlBasedCorsConfigurationSource = urlBasedCorsConfigurationSource;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry
                = http.antMatcher("/**").authorizeRequests();

        // 登录请求（POST）不需要授权
        AntPathRequestMatcher loginAnt = new AntPathRequestMatcher("/login", HttpMethod.POST.toString());
        // Session ID 地址 不需要授权
        AntPathRequestMatcher sessionIdAnt = new AntPathRequestMatcher("/sessionId");
        expressionInterceptUrlRegistry.requestMatchers(loginAnt, sessionIdAnt).permitAll();

        // 排除 登录请求（POST）的地址需要授权
        NegatedRequestMatcher loginNegated = new NegatedRequestMatcher(loginAnt);
        // 排除 Session ID 地址 不需要授权
        NegatedRequestMatcher sessionIdNegated = new NegatedRequestMatcher(sessionIdAnt);
        expressionInterceptUrlRegistry.requestMatchers(loginNegated, sessionIdNegated).authenticated();

        // 添加一个地址及权限（由于优先级的问题，至少存在一个此配置，才能正常配置 Security，否则 Security 无效）
        http.antMatcher("/test-scope/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/test-scope/1")
                .access("#oauth2.hasAnyScope('test-scope_1')");

        // Security CORS 跨域配置
        http.cors().configurationSource(urlBasedCorsConfigurationSource);

    }

}
