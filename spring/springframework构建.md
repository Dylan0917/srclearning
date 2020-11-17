### 修改仓库配置

```xml
repositories {
			maven { url 'https://maven.aliyun.com/repository/public/' }
			maven { url 'https://maven.aliyun.com/repository/spring/'}
			mavenCentral()
			maven { url "https://repo.spring.io/libs-spring-framework-build" }
		}
```

Precompile `spring-oxm` with  ./gradlew :spring-oxm:compileTestJava



注意

引入插件（optional(project(":spring-context"))等）

```
configure(moduleProjects) { project ->
   apply from: "${rootDir}/gradle/spring-module.gradle"
}
ext {
	moduleProjects = subprojects.findAll { it.name.startsWith("spring-") }
	javaProjects = subprojects - project(":framework-bom")
	withoutJclOverSlf4j = {
		exclude group: "org.slf4j", name: "jcl-over-slf4j"
	}
}
```

```
apply plugin: 'org.springframework.build.compile'
apply plugin: 'org.springframework.build.optional-dependencies'
```