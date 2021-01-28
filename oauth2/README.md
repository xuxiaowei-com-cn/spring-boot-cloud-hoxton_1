# OAuth2（服务模块）

## dependencies

- Developer Tools
    - Lombok
    - Spring Configuration Processor

- Web
    - Spring Web
        - 提供 Web 服务

- SQL
    - JDBC API
    - MyBatis Framework
    - MS SQL Server Driver
    - MySQL Driver
    - Oracle Driver

- Modules
    - client-pom
        - 作为配置服务器的客户端所需依赖
    - oauth2-pom
        - 作为需要权限的模块所需依赖

## SQL

- spring-security-core-*.*.*.RELEASE.jar
    - org.springframework.security.core.userdetails.jdbc
        - users.ddl
