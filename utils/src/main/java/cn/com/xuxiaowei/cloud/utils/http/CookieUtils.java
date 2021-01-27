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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class CookieUtils {

    /**
     * 根据 请求、cookieName 获取 Cookie
     *
     * @param request    请求
     * @param cookieName Cookie Name
     * @return 返回 根据 请求、cookieName 获取的 Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        if (cookieName != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                return null;
            } else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(cookieName)) {
                        return cookie;
                    }
                }
            }
        }
        return null;
    }

}
