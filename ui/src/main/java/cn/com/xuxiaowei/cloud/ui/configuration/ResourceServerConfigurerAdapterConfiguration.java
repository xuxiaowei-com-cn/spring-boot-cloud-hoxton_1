package cn.com.xuxiaowei.cloud.ui.configuration;

import cn.com.xuxiaowei.cloud.ui.properties.PatchcaDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        // 全自动区分计算机和人类的图灵测试 不需要授权
        AntPathRequestMatcher patchcaAnt = new AntPathRequestMatcher(patchcaDefaultProperties.getUrl(), HttpMethod.GET.toString());
        // 登录请求（POST）不需要授权
        AntPathRequestMatcher loginAnt = new AntPathRequestMatcher("/login", HttpMethod.POST.toString());
        expressionInterceptUrlRegistry.requestMatchers(patchcaAnt, loginAnt).permitAll();

        // 排除 全自动区分计算机和人类的图灵测试 的地址需要授权
        NegatedRequestMatcher patchcaNegated = new NegatedRequestMatcher(patchcaAnt);
        // 排除 登录请求（POST）的地址需要授权
        NegatedRequestMatcher loginNegated = new NegatedRequestMatcher(loginAnt);
        expressionInterceptUrlRegistry.requestMatchers(patchcaNegated, loginNegated).authenticated();

    }


}
