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

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 请求工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RequestUtils {

    /**
     * 请求参数集
     *
     * @param request 请求
     * @return 返回 请求参数集
     */
    public static String keyValue(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder keyValue = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            keyValue.append(key).append(":").append(Arrays.toString(value)).append("&");
        }
        return keyValue.toString();
    }

    /**
     * 请求参数集
     *
     * @param request 请求
     * @return 返回 请求参数集
     */
    public static String keyValueJson(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return JSONObject.toJSONString(parameterMap);
    }

    /**
     * 请求参数流
     *
     * @param request 请求
     * @return 返回 请求参数流
     * @throws IOException 读取流异常
     */
    public static String getInputStream(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        int contentLength = request.getContentLength();
        if (contentLength == -1) {
            return null;
        } else {
            byte[] bytes = new byte[contentLength];
            String characterEncoding = request.getCharacterEncoding();
            inputStream.read(bytes);
            return new String(bytes, characterEncoding);
        }
    }

    /**
     * 移除参数
     *
     * @param queryString 参数
     * @param queryNames  移除参数名
     * @return 返回移除结果
     */
    public static String removeQuery(String queryString, String... queryNames) {
        if (queryString != null && !"".equals(queryString)) {
            for (String queryName : queryNames) {
                queryString = queryString.replaceAll("&?" + queryName + "=[^&]*", "");
            }
        }
        return queryString;
    }

    /**
     * 获取 Header Map
     *
     * @param request 请求
     * @return 返回 Header Map
     */
    public static Map<String, String> getHeaderMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(8);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            map.put(headerName, headerValue);
        }
        return map;
    }

    /**
     * 获取 Headers Map
     *
     * @param request 请求
     * @return 返回 Headers Map
     */
    public static Map<String, List<String>> getHeadersMap(HttpServletRequest request) {
        Map<String, List<String>> map = new HashMap<>(8);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            List<String> headerValuesList = Collections.list(headerValues);
            map.put(headerName, headerValuesList);
        }
        return map;
    }

}
