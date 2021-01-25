# Discovery（服务发现 Eureka Server）

## 文档

- [netflix-eureka-server-starter](https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#netflix-eureka-server-starter)

## dependencies

- Spring Cloud Discovery
    - Eureka Server
        - 提供 @EnableEurekaServer 注解
        - 包含 Ribbon 依赖

## 说明

- 每次服务注册需要 30s 的时间才能显示在 Eureka 服务中，因为 Eureka 需要从服务接收 3 次连续心跳包 ping，每次心跳包 ping 间隔 10m，然后才能使用这个服务。
- /eureka/apps
    - app
        - 服务的查找键
    - ipAddr
        - 组织服务实例的IP地址
    - status
        - 服务的状态
- /eureka/apps/服务的查找键
    - 查看单个服务的信息
