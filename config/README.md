# Config（配置 Config Server）

## dependencies

- Spring Cloud Config
    - Config Server
        - 提供 @EnableConfigServer 注解

- Spring Cloud Discovery
    - Eureka Discovery Client
        - 提供 @EnableEurekaClient 注解，使用 Eureka 注册服务。
        - 包含 Hystrix 依赖
        - 包含 Ribbon 依赖
