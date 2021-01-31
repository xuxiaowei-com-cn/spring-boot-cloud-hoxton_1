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
package cn.com.xuxiaowei.cloud.utils.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * {@link HttpServletRequest} Header 修改类
 *
 * @author xuxiaowei
 * @see HttpServletRequest
 * @since 0.0.1
 */
public class HeaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 新 header 内容
     */
    private final Map<String, String> HEADER_MAP;

    /**
     * 原始请求
     */
    private final HttpServletRequest request;

    /**
     * 构造包装给定请求的请求对象。
     *
     * @param request 包装请求
     * @throws IllegalArgumentException 如果请求为空
     */
    public HeaderHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);

        HEADER_MAP = new HashMap<>();
        this.request = request;

        // 将原始 Headers 放入新 Headers（Map）中
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            String headerValue = request.getHeader(headerName);
            HEADER_MAP.put(headerName, headerValue);
        }

    }

    @Override
    public String getHeader(String name) {
        return HEADER_MAP.get(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        String enumeration = HEADER_MAP.get(name);
        Set<String> set = new HashSet<>();
        if (enumeration != null) {
            set.add(enumeration);
        }
        Enumeration<String> headerValues = request.getHeaders(name);
        if (headerValues.hasMoreElements()) {
            set.add(headerValues.nextElement());
        }
        return Collections.enumeration(set);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> set = HEADER_MAP.keySet();
        return Collections.enumeration(set);
    }

    /**
     * @see HttpServletRequest#getIntHeader(String)
     */
    @Override
    public int getIntHeader(String name) {
        String headerValue = getHeader(name);
        if (headerValue == null) {
            return -1;
        } else {
            return parseInt(headerValue, 10);
        }
    }

    /**
     * 此方法用于添加 Header
     *
     * @param name  需要添加的 Header name
     * @param value 需要添加的 Header value
     */
    public void addHeader(String name, String value) {
        HEADER_MAP.put(name, value);
    }

    /**
     * 此方法用于删除 Header
     *
     * @param name 需要删除的 Header name
     */
    public void removeHeader(String name) {
        HEADER_MAP.remove(name);
    }

}
