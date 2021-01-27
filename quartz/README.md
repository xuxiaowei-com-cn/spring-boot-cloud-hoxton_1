# Quartz（服务模块）

## dependencies

- Developer Tools
    - Lombok
    - Spring Configuration Processor

- Web
    - Spring Web
        - 提供 Web 服务

- I/O
    - Quartz Scheduler

- Modules
    - mybatis
        - 提供持久层统一配置
    - client-pom
        - 作为配置服务器的客户端所需依赖

## SQL

- 参见 quartz-x.x.x.jar 中的 org.quartz.impl.jdbcjobstore
    - 根据数据库选择对应的 SQL
