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

import static cn.com.xuxiaowei.cloud.utils.Constants.NO_CACHE;
import static cn.com.xuxiaowei.cloud.utils.Constants.NO_CACHE_NO_STORE;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

/**
 * 图片工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ImageUtils {

    /**
     * 设置图片验证码的响应
     * <p>
     * 禁止图片验证码缓存
     *
     * @param response 响应
     */
    public static void setResponseHeaders(HttpServletResponse response) {
        // 发送到客户端的响应的内容类型
        response.setContentType(IMAGE_PNG_VALUE);

        // 设置具有给定名称和值的响应标头。
        // 如果已设置标头，则新值将覆盖前一个标头。
        // Cache-control：指定所有缓存机制在整个请求/响应链中必须服从的指令。
        // 这些指令指定用于阻止缓存对请求或响应造成不利干扰的行为。
        // 这些指令通常覆盖默认缓存算法。
        // 缓存指令是单向的，即请求中存在一个指令并不意味着响应中将存在同一个指令。
        // no-cache：必须先与服务器确认返回的响应是否被更改，然后才能使用该响应来满足后续对同一个网址的请求。
        // 因此，如果存在合适的验证令牌 (ETag)，no-cache 会发起往返通信来验证缓存的响应，如果资源未被更改，可以避免下载。
        // no-store：所有内容都不会被缓存到缓存或 Internet 临时文件中
        response.setHeader(CACHE_CONTROL, NO_CACHE_NO_STORE);
        response.setHeader(PRAGMA, NO_CACHE);

        // 时间戳
        long time = System.currentTimeMillis();

        // 设置具有给定名称和日期值的响应标头。
        // 日期以自纪元以来的毫秒数指定。
        // 如果已设置标头，则新值将覆盖前一个标头。
        // Last-Modified：在浏览器第一次请求某一个URL时，服务器端的返回状态会是200，内容是客户端请求的资源，
        // 同时有一个Last-Modified的属性标记此文件在服务器端最后被修改的时间。
        response.setDateHeader(LAST_MODIFIED, time);
        response.setDateHeader(DATE, time);
        // Expires：Expires是RFC 2616（HTTP/1.0）协议中和网页缓存相关字段。
        // 用来控制缓存的失效日期，要注意的是，HTTP/1.0有一个功能比较弱的缓存控制机制：Pragma，使用HTTP/1.0的缓存将忽略Expires和Cache-Control头。
        response.setDateHeader(EXPIRES, time);
    }

}