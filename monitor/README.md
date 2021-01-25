# Monitor（监控 DashBoard）

## dependencies

- Spring Cloud Circuit Breaker
    - Hystrix Dashboard[Maintenance]
        - 提供 @EnableHystrixDashboard 注解
        - 包含 Hystrix 依赖
    - Turbine[Maintenance]
        - 提供 @EnableTurbine、@EnableEurekaClient 注解
        - 包含 Enable Discovery Client 依赖
            - 包含 Hystrix 依赖
            - 包含 Ribbon 依赖
