# USEDBOOKS
## My environment
Java 21
Spring Boot 3.1.5

## Database Console
### 访问H2控制台

当你启动Spring Boot应用后，使用浏览器访问 [your-server-url]/h2-console。例如，如果你在本地运行，那么URL就是 http://localhost:8080/h2-console。

### 登录H2控制台

使用配置中定义的JDBC URL、用户名和密码登录。基于上面的配置，JDBC URL是jdbc:h2:mem:testdb，用户名是sa，密码为空。
