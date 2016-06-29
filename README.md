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


