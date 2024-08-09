## 项目启动配置
- 先创建数据库
```mysql
create database library
```
- 连接创建好的数据库
```yml
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8&rewriteBatchedStatements=true
username: xxx
password: xxx
type: com.zaxxer.hikari.HikariDataSource

```
- 启动项目