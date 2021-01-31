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
package cn.com.xuxiaowei.cloud.utils.security.filter;

import cn.com.xuxiaowei.cloud.utils.http.CookieUtils;
import cn.com.xuxiaowei.cloud.utils.http.HeaderHttpServletRequestWrapper;
import cn.com.xuxiaowei.cloud.utils.security.properties.CsrfCookieDefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CSRF Cookie 策略 运行前 Filter
 * <p>
 * 提交数据时，在 {@link CsrfFilter} 中验证 CSRF 时，
 * 使用 <code>request.getHeader(csrfToken.getHeaderName())</code> 或 <code>request.getParameter(csrfToken.getParameterName())</code>
 * 获取 CSRF 参数为空，在此类中，将 Cookie 中只读的 CSRF 参数放入 Header 中用于验证
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class CsrfCookieBeforeOncePerRequestFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository csrfTokenRepository;

    private final CsrfCookieDefaultProperties csrfCookieDefaultProperties;

    public CsrfCookieBeforeOncePerRequestFilter(CsrfTokenRepository csrfTokenRepository,
                                                CsrfCookieDefaultProperties csrfCookieDefaultProperties) {
        Assert.notNull(csrfTokenRepository, "CsrfTokenRepository 不能为 null");
        Assert.notNull(csrfCookieDefaultProperties, "CsrfCookieDefaultProperties 不能为 null");
        this.csrfTokenRepository = csrfTokenRepository;
        this.csrfCookieDefaultProperties = csrfCookieDefaultProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 获取 Session 中的 CSRF
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);

        if (csrfToken == null) {
            log.debug("CsrfToken 为空");
        } else {

            // CSRF 请求头的 name
            String headerName = csrfToken.getHeaderName();

            String cookieName = csrfCookieDefaultProperties.getCookieName();
            Cookie cookie = CookieUtils.getCookie(request, cookieName);

            if (cookie == null) {
                log.warn("Csrf Cookie 值为空");
            } else {

                // HttpServletRequest 设置为可修改 Header 的请求
                HeaderHttpServletRequestWrapper headerHttpServletRequestWrapper = new HeaderHttpServletRequestWrapper(request);

                // 修改 Header（CSRF Header）
                headerHttpServletRequestWrapper.addHeader(headerName, cookie.getValue());

                // 使用携带 CSRF Header 的请求
                filterChain.doFilter(headerHttpServletRequestWrapper, response);

                return;
            }

        }

        // 执行后续程序
        filterChain.doFilter(request, response);

    }

}
