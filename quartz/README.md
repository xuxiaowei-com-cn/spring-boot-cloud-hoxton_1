# Quartz（服务模块）

## dependencies

- Developer Tools
    - Lombok
    - Spring Configuration Processor

- Web
    - Spring Web
        - 提供 Web 服务

- Template Engines
    - Thymeleaf

- I/O
    - Quartz Scheduler

- Spring Cloud Config
    - Config Client
        - 提供读取配置服务器中的配置

- Spring Cloud Discovery
    - Enable Discovery Client
        - 提供 @EnableEurekaClient 注解

- Modules
    - mybatis
        - 提供持久层统一配置

## SQL

- 参见 quartz-x.x.x.jar 中的 org.quartz.impl.jdbcjobstore
    - 根据数据库选择对应的 SQL