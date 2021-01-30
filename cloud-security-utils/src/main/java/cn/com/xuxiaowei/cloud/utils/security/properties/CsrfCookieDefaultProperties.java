package cn.com.xuxiaowei.cloud.utils.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * CSRF 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "csrf.cookie.default")
public class CsrfCookieDefaultProperties {

    /**
     * CSRF Cookie 是否为只读
     */
    private boolean cookieHttpOnly;

    /**
     * CSRF header 参数名
     */
    private String headerName;

    /**
     * CSRF Cookie 名称
     */
    private String cookieName;

    /**
     * CSRF 参数名
     */
    private String parameterName;

    /**
     * CSRF Cookie 路径
     */
    private String cookiePath;

}
