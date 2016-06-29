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