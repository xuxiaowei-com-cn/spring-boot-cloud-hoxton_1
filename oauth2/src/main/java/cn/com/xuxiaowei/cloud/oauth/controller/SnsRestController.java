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
package cn.com.xuxiaowei.cloud.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 社交资源
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/sns")
public class SnsRestController {

    /**
     * 根据 scope 获取用户详细信息的资源
     */
    @RequestMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> userinfo(OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> map = new HashMap<>(8);

        String username = oAuth2Authentication.getName();

        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Set<String> scopes = oAuth2Request.getScope();

        boolean userinfo = scopes.contains("snsapi_userinfo");

        map.put("username", username);
        map.put("name", "徐晓伟");

        return ResponseEntity.ok(map);
    }

}
