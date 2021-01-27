# spring-boot-cloud-hoxton

## 模块

- cloud-admin-vue
    - 后台管理
- config
    - 配置 Config Server
- discovery
    - 服务发现 Eureka Server
- doc
    - 文档
- monitor
    - 监控 DashBoard
- mybatis
    - 提供持久层统一配置
- oauth2
    - OAuth2 认证
- passport
    - 登录 服务模块
- quartz
    - quartz 服务模块
- redis
    - Redis/Session 配置
- ui
    - 界面模块 Ribbon、Feign
- utils
    - 工具包模块
- www
    - 网站（域名） 服务模块

## 配置与启动

### 配置

1. 新建业务数据库：spring-boot-cloud-hoxton
    1. 业务表参见各模块中的 sql 文件夹
    1. [业务中回滚日志表](https://github.com/seata/seata/tree/develop/script/client/at/db) ，分布式事务使用 AT。
1. 配置 seata 服务
    1. 新建 seata 数据库，[seata 数据库表](https://github.com/seata/seata/tree/develop/script/server/db)
    1. [配置 seata 服务](/doc/seata-server)

### 启动

1. discovery（服务发现 Eureka Server）
    - 启动成功后可访问 Eureka 服务
1. config（配置 Config Server）
    - 启动成功后可在 Eureka 服务中查看到本模块
1. seata（分布式事务服务器）
    - 启动成功后可在 Eureka 服务中查看到本模块
1. 推荐 ui 模块在最后启动，其他启动顺序可随机

## 文档

- [Spring Cloud](https://spring.io/projects/spring-cloud)
    - [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
        - [Spring Cloud Config Current](https://docs.spring.io/spring-cloud-config/docs/2.2.6.RELEASE/reference/html/)
    - [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
        - [Spring Cloud Netflix Current](https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/)

- seata
- [文档](https://seata.io/zh-cn)
- [github](https://github.com/seata/seata)

## 说明

- Archaius
- Eureka
- Feign
- Hystrix
- Ribbon
- Spring Cloud Bus
- Spring Cloud CLI
- Spring Cloud Config
    - 配置管理
    - Consul
        - 是一种开源的服务发现工具，允许服务实力向该服务注册自己。服务客户端可以向 Consul 咨询服务实例的的位置。Consul 还包括可以被 Spring Cloud Config
          使用的基础键值存储的数据库，能够用来存储应用程序的配置数据。
    - Eureka
        - 是一个开源的 Netflix 项目，像 Consul 一样，提供类似的服务发现的功能。Eureka 同样有一个可以被 Spring Cloud Config 使用的键值数据库。
- Spring Cloud Connectors
- Spring Cloud Consul
- Spring Cloud Data Flow
- Spring Cloud Netflix
    - Eureka
        - 服务发现模式
    - Zuul
        - 服务路由模式
    - Ribbon
        - 客户端负载均衡
    - Hystrix
        - 断路器模式
        - 后备模式
        - 舱壁模式
- Spring Cloud Security
    - OAuth2
        - 授权
        - 验证
        - 凭证管理和传播
    - JWT
        - 凭证管理和传播
- Spring Cloud Sleuth
    - 异步消息处理
    - 日志关联
    - 日志聚合（与Papertrail）
    - Zipkin
        - 微服务跟踪
- Spring Cloud Stream
- Spring Cloud Task
- Spring Cloud Zookeeper
- Zuul
