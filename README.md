# mytest

## 初始化
1. 使用命令`gradle init --type basic`生成项目
1. 然后添加如下task，初始化java项目(生成src目录)

		//gradlew -q initProject
		//等同 "mkdir src\main\java"
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
```java
@Bean
CommandLineRunner init() {
	return (args) -> {
        FileSystemUtils.deleteRecursively(new File(FileUploadController.ROOT));

        Files.createDirectory(Paths.get(FileUploadController.ROOT));
	};
}
```	





