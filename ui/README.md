# UI（界面模块 Ribbon、Feign）
 
## dependencies

- Developer Tools
    - Lombok
    - Spring Configuration Processor

- Spring Cloud Config
    - Config Client
        - 提供读取配置服务器中的配置

- Spring Cloud Discovery
    - Enable Discovery Client
        - 提供 @EnableEurekaClient 注解
        - 包含 Hystrix 依赖
        - Discovery
            - 使用 DiscoveryClient 和标准的 Spring RestTemplate 类来调用组织服务
        - Rest
            - 使用增强的 Spring RestTemplate 来调用基于 Ribbon 的服务

- Spring Cloud Routing
    - Zuul[Maintenance]
        - 提供 @EnableZuulProxy 注解
        - 包含 Ribbon 依赖
        - 包含 Hystrix 依赖
    - OpenFeign
        - 提供 @EnableFeignClients 注解
        - 包含 Ribbon 依赖
        - Feign
            - 使用 Netflix 的 Feign 客户端库来通过 Ribbon 调用服务

- Modules
    - redis
        - 提供 Redis、Session共享 统一配置
    - mybatis
        - 提供持久层统一配置
