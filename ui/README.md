# UI（界面模块 Ribbon、Feign）
 
## dependencies

- Spring Cloud Config
    - Config Client
        - 提供配置服务器中的配置

- Spring Cloud Discovery
    - Enable Discovery Client
        - 提供 @EnableEurekaClient 注解
        - 包含 Hystrix 依赖

- Spring Cloud Routing
    - Zuul[Maintenance]
        - 提供 @EnableZuulProxy 注解
        - 包含 Ribbon 依赖
        - 包含 Hystrix 依赖
    - OpenFeign
        - 提供 @EnableFeignClients 注解
        - 包含 Ribbon 依赖
