# mytest

## 初始化
1. 使用命令`gradle init --type basic`生成项目
1. 然后添加如下task，初始化java项目(生成src目录)

		//gradlew -q initProject
		//等同 "mkdir src\main\java"在mac上要是"mkdir -p src/main/java"
		task initProject () << {
		
			project.sourceSets*.allSource.srcDirTrees.flatten().dir.each { dir ->
				dir.mkdirs()
			}
		}
1. 最后参考 [https://spring.io/guides/gs/gradle/](https://spring.io/guides/gs/gradle/)，学习gradle的基本使用


## Building a RESTful Web Service [参考](https://spring.io/guides/gs/rest-service/)

- 如果用jcenter要加

	    repositories {
	        jcenter()
	        maven { url "http://repo.spring.io/snapshot" }
	        maven { url "http://repo.spring.io/milestone" }
	    }

- 运行：`gradlew bootRun`
- 运行：或者和普通java app一样，运行Application 的 main方法就可以


## Consuming a RESTful Web Service [参考](https://spring.io/guides/gs/consuming-rest/)

- 和上一个相比，dependencies

		dependencies {
		    compile("org.springframework.boot:spring-boot-starter-web")
		    testCompile('org.springframework.boot:spring-boot-starter-test')
		}
变为：

		dependencies {
		    compile("org.springframework.boot:spring-boot-starter")
		    compile("org.springframework:spring-web")
		    compile("com.fasterxml.jackson.core:jackson-databind")
		    testCompile("junit:junit")
		}

- 运行：和上一个相同


## Detecting a Device [参考](https://spring.io/guides/gs/device-detection/)

其实就是根据UA来判断浏览器类型


## Accessing Relational Data using JDBC with Spring [参考](https://spring.io/guides/gs/relational-data-access/)

用`JdbcTemplate`操作H2数据库


## Uploading Files [参考](https://spring.io/guides/gs/uploading-files/)

上传文件到指定目录

- `templates/uploadForm.html`用到模板语言`Thymeleaf`
- An optional message at the top where Spring MVC writes a `flash-scoped messages`
- 目录下文件列表(Files工具类的使用)
```java
Files.walk(Paths.get(ROOT))
	.filter(path -> !path.equals(Paths.get(ROOT)))
	.map(path -> Paths.get(ROOT).relativize(path))
	.map(path -> linkTo(methodOn(FileUploadController.class).getFile(path.toString())).withRel(path.toString()))
	.collect(Collectors.toList())
```	
- `@Autowired`在构造方法`FileUploadController(ResourceLoader resourceLoader)`上的使用
- `application.properties`配置
- init在application启动的执行顺序

```
@Bean
CommandLineRunner init() {
	return (args) -> {
        FileSystemUtils.deleteRecursively(new File(FileUploadController.ROOT));

        Files.createDirectory(Paths.get(FileUploadController.ROOT));
	};
}
```	
## Mybatis
[参考](http://blog.didispace.com/springbootmybatis/)

1. 增加依赖

	```
	dependencies {
 	    compile("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.1.1")
    	compile("mysql:mysql-connector-java:5.1.21")
	}
	```
1. 配置mysql的连接配置

	```
	spring:
    	datasource:
        	driverClassName: com.mysql.jdbc.Driver
        	url: jdbc:mysql://localhost:3306/mytest
        	username: root
	```
1. 创建UserInfo表
2. 创建UserInfo映射的操作UserMapper
3. 关于test的修改

	```
        /**
         * 再次测试先删除旧的数据
         SET SQL_SAFE_UPDATES = 0;
         delete from mytest.UserInfo where name = 'AAA';
         * */
        @RunWith(SpringRunner.class)
        @SpringBootTest
        public class ApplicationTests {
        }
    ```
1. 增删改查的restful api
## @JsonView
[参考](http://www.jianshu.com/p/633d83dd303b)

1. 定义标识类
2. 通过@JsonView注解需要输出的字段
3. 通过@JsonView注解Controllor中相应的方法


## WebConfig


1. 基于RegistrationBean配置Servelt、Filter、Listener [参考](http://www.tianshouzhi.com/api/tutorials/springboot/89)
2. 基于注解的配置[参考](http://blog.csdn.net/catoop/article/details/50501686)
	
```
@SpringBootApplication
@ServletComponentScan//新增加
public class Application {
}
```
```
@WebServlet(urlPatterns="/demoservlet2", description="Servlet的说明") // 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.MyServlet2
public class DemoServlet2 extends HttpServlet {
}
```

## build war


1. 创建一个可部署的war文件

	产生一个可部署war包的第一步是提供一个SpringBootServletInitializer子类，并覆盖它的configure方法。这充分利用了Spring框架对Servlet 3.0的支持，并允许你在应用通过servlet容器启动时配置它。通常，你只需把应用的主类改为继承SpringBootServletInitializer即可：
1. `apply plugin: 'war'`

	```
	dependencies {
    	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
	}
	```
	
## [actuator](http://www.jianshu.com/p/734519d3c383)
1. 依赖`compile("org.springframework.boot:spring-boot-starter-actuator")`
2. 查看各种状态如：http://localhost:8080/env

## PropertySource
使用`@PropertySource`注解加载`*.properties`中的配置
[参考](http://wenrisheng.iteye.com/blog/2231807)
[参考](http://9leg.com/spring/2015/02/12/spring-propertysource-value-annotations-example.html)
