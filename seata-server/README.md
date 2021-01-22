# seata-server 配置

## [seata](https://github.com/seata/seata)

- 事务模式
    - [Seata AT 模式](https://seata.io/zh-cn/docs/dev/mode/at-mode.html)
    - [动态数据源：Seata事务](https://dynamic-datasource.com/guide/tx/Seata.html)
- 使用 MySQL 数据库
- [事务信息用 db 存储](https://github.com/seata/seata/blob/develop/script/server/db/)
- [各业务数据库中新建表](https://github.com/seata/seata/blob/develop/script/client/)
- 注册中心
    - 使用 eureka
    - eureka 的 application = "seata"
