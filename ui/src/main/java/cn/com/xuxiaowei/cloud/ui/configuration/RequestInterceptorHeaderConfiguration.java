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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 发送至服务时，携带 header
 *
 * @author xuxiaowei
 * @see feign.auth.BasicAuthRequestInterceptor 拦截器，添加使用HTTP基本身份验证所需的请求标头。
 * @see org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties Feign编码属性。
 * @see org.springframework.cloud.openfeign.encoding.FeignContentGzipEncodingInterceptor 通过指定Content-Encoding标头来启用HTTP请求有效负载压缩。
 * @see org.springframework.cloud.openfeign.encoding.FeignAcceptGzipEncodingInterceptor  通过指定Accept-Encoding标头来启用HTTP响应有效负载压缩。 尽管这还不意味着请求将被压缩，但它要求远程服务器了解标头并配置为压缩响应。 仍然不能基于媒体类型匹配和其他因素（如响应内容长度）来压缩所有响应。
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class RequestInterceptorHeaderConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        // RequestContextHolder.getRequestAttributes() 不为空
        // hystrix.command.default.execution.isolation.strategy = SEMAPHORE
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }

        // 分布式事务传递 TX_XID
        String xid = RootContext.getXID();
        if (StringUtils.isNotEmpty(xid)) {
            template.header(RootContext.KEY_XID, xid);
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                // 过滤 Content-Length，防止接收不到响应
                if (!HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(headerName)) {
                    template.header(headerName, headerValue);
                }
            }
        }

    }

}
