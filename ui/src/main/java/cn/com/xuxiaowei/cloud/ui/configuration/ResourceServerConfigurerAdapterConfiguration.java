package cn.com.xuxiaowei.cloud.ui.configuration;

import cn.com.xuxiaowei.cloud.ui.properties.PatchcaDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

/**
 * 资源服务器 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ResourceServerConfigurerAdapterConfiguration extends ResourceServerConfigurerAdapter {

    private PatchcaDefaultProperties patchcaDefaultProperties;

    @Autowired
    public void setPatchcaDefaultProperties(PatchcaDefaultProperties patchcaDefaultProperties) {
        this.patchcaDefaultProperties = patchcaDefaultProperties;
    }

    /**
     * @see super#configure(HttpSecurity)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry
                = http.antMatcher("/**").authorizeRequests();

        // 全自动区分计算机和人类的图灵测试
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(patchcaDefaultProperties.getUrl());
        expressionInterceptUrlRegistry.requestMatchers(antPathRequestMatcher).permitAll();

        // 排除 全自动区分计算机和人类的图灵测试 的地址需要授权
        expressionInterceptUrlRegistry.requestMatchers(new NegatedRequestMatcher(antPathRequestMatcher)).authenticated();

    }


}
