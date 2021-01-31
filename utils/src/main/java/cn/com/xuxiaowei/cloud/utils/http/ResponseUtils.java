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

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 响应工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ResponseUtils {

    /**
     * 获取 Header Map
     *
     * @param response 响应
     * @return 返回 Header Map
     */
    public static Map<String, String> getHeaderMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>(8);
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            String headerValue = response.getHeader(headerName);
            map.put(headerName, headerValue);
        }
        return map;
    }

    /**
     * 获取 Headers Map
     *
     * @param response 响应
     * @return 返回 Headers Map
     */
    public static Map<String, List<String>> getHeadersMap(HttpServletResponse response) {
        Map<String, List<String>> map = new HashMap<>(8);
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            Collection<String> headerValues = response.getHeaders(headerName);
            List<String> headerValuesList = new ArrayList<>(headerValues);
            map.put(headerName, headerValuesList);
        }
        return map;
    }

}
