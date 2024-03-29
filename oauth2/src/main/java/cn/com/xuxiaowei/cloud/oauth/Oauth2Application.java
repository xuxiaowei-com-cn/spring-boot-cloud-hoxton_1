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
package cn.com.xuxiaowei.cloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

import java.security.Principal;
import java.util.Map;

/**
 * OAuth2 服务 程序执行入口
 *
 * @author xuxiaowei
 * @see TokenEndpoint#getAccessToken(Principal, Map)
 * @see TokenEndpoint#postAccessToken(Principal, Map)
 * @see BaseClientDetails#BaseClientDetails(String, String, String, String, String, String) 若 authorizedGrantTypes 为空，
 * 设置默认值："authorization_code", "refresh_token"
 * @since 0.0.1
 */
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }

}
