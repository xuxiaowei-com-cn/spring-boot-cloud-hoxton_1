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
package cn.com.xuxiaowei.cloud.oauth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ResourceServerConfigurerAdapterConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 1、默认拦截所有请求
     * 2、若禁用父类中的 <code>http.authorizeRequests().anyRequest().authenticated();</code>，
     * 需要至少设置一个地址增加资源权限，如：
     * <code>
     * http.antMatcher("/sns/**").authorizeRequests().antMatchers(HttpMethod.GET, "/sns/userinfo").access("#oauth2.hasAnyScope('snsapi_base','snsapi_userinfo')")
     * </code>
     * 3、若未设置任何资源权限，将会出现异常：Caused by: java.lang.IllegalStateException: Cannot apply org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer@6b37df8e to already built object
     * 4、配置 {@link ResourceServerConfigurerAdapter} 时需要使用 {@link EnableResourceServer}，
     * 在 {@link EnableResourceServer} 中使用了 {@link ResourceServerConfiguration}，
     * 而 {@link ResourceServerConfiguration} 的 {@link Order} 为 3，
     * 故 {@link ResourceServerConfigurerAdapter}的{@link Order} 也为 3。
     * <p>
     * 而 {@link WebSecurityConfigurerAdapter} 的 {@link Order} 为 100，
     * 同时使用 {@link WebSecurityConfigurerAdapter} 和 {@link ResourceServerConfigurerAdapter} 时，
     * 需要为 {@link ResourceServerConfigurerAdapter#configure(HttpSecurity)} 配置 <code>http.antMatcher("/sns/**")</code>，
     * 否则 {@link WebSecurityConfigurerAdapter} 优先级低，将不起作用。
     * <p>
     * 注意：
     * 使用{@link Order}调整{@link WebSecurityConfigurerAdapter}、{@link ResourceServerConfigurerAdapter}的优先级也可行，
     * 但是不推荐，有风险。
     * 需要某些路径具有某些权限（特性）就直接去配置它们，不要牵扯别的模块。
     * <p>
     * 配置示例：
     * // 配置资源路径 /sns/** 需要的权限 scope
     * http.antMatcher("/sns/**").authorizeRequests()
     * .antMatchers(HttpMethod.GET, "/sns/userinfo").access("#oauth2.hasAnyScope('snsapi_base','snsapi_userinfo')")
     * .antMatchers(HttpMethod.POST, "/sns/userinfo").access("#oauth2.hasAnyScope('snsapi_base','snsapi_userinfo')")
     * ;
     *
     * @see super#configure(HttpSecurity)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
