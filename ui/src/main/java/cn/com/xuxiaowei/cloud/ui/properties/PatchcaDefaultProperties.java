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
package cn.com.xuxiaowei.cloud.ui.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全自动区分计算机和人类的图灵测试 默认 属性文件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "servlet.patchca.default")
public class PatchcaDefaultProperties {

    /**
     * 字符范围
     */
    private String characters;

    /**
     * 字符长度最大值
     */
    private Integer maxLength;

    /**
     * 字符长度最小值
     */
    private Integer minLength;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 字体最小
     */
    private Integer minSize;

    /**
     * 字体最大
     */
    private Integer maxSize;

    /**
     * Session Name
     */
    private String sessionName;

    /**
     * 格式
     */
    private String format;

    /**
     * 随机字体种类，最小值 1 ，最大值 5
     */
    private Integer random;

    /**
     * 参数名
     */
    private String parameterName;

    /**
     * 地址
     */
    private String url;

}
