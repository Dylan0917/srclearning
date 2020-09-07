### Maven好处

- 一步构建：一个命令即可启动。
- 依赖管理：对jar包统一管理，避免冲突等问题
- 跨平台：可在windows及linux下运行
- 有利于提高大型项目的团队开发效率
- 越来越多的公司使用maven

maven项目采用“**约定优于配置**”的原则：

- src/main/java：约定用于存放源代码，
- src/test/java：用于存放单元测试代码，（测试代码的包应该和被测试代码包结构保持一致，方便测试查找）
- src/target：用于存放编译、打包后的输出文件。

这是全世界maven项目的通用约定，请记住这些固定的目录结构。

├───src
│  ├───main
│  │  └───java
│  │    └───cnblogs
│  └───test
│    └───java
│      └───cnblogs
└───target
  └───classes
    └───cnblogs

注：上面带红色的目录名

2、工程示例

![img](E:\myProject\srclearning\Maven学习\1375038-20180515144306985-930275388.png)

### 常用命令

maven命令语句

```
mvn -v 查看maven版本
mvn compile 用来将src/main/java下的文件编译为class文件，并输出到target中。
mvn test test用来将src/main/test下的文件进行编译，同时执行一次
mvn package 打包,将项目进行打包，如果是jar打包为jar，war打包为war。

mvn clean 删除编译产生的target文件夹
mvn install 安装jar包到本地仓库中
```



### maven依赖的范围

1. 编译依赖范围（compile），该范围就是默认依赖范围，此依赖范围对 于编译、测试、运行三种classpath都有效，举个简单的例子，假如项目中有spring-core的依赖，那么spring-core不管是在编译，测试，还是运行都会被用到，因此spring-core必须是编译范围（构件默认的是编译范围，所以依赖范围是编译范围的无须显示指定）

```xml
<dependency>
  	  <groupId>org.springframework</groupId>
  	  <artifactId>spring-core</artifactId>
  	  <version>2.5</version>
  	  <scope>compile</scope> <!--默认为该依赖范围，无须显示指定--〉
</dependency>
```



2. 测试依赖范围(test)，顾名思义就是针对于测试的，使用此依赖范围的依赖，只对测试classpath有效，在编译主代码和项目运行时，都将无法使用该依赖，最典型的例子就是 Junit, 构件在测试时才需要，所以它的依赖范围是测试，因此它的依赖范围需要显示指定为<scope>test</scope> ,当然不显示指定依赖范围也不会报错，但是该依赖会被加入到编译和运行的classpath中,造成不必要的浪费 。

```xml
 <dependency>
  	  <groupId>junit</groupId>
  	  <artifactId>junit</artifactId>
  	  <version>4.7</version>
  	 <scope>test</scope>
</dependency>
```

3. 已提供依赖范围(provided),使用该依赖范围的maven依赖，只对编译和测试的classpath有效，对运行的classpath无效，典型的例子就是servlet-api， 编译和测试该项目的时候需要该依赖，但是在运行时，web容器已经提供的该依赖，所以运行时就不再需要此依赖，如果不显示指定该依赖范围，并且容器依赖的版本和maven依赖的版本不一致的话，可能会引起版本冲突，造成不良影响。

例如：servlet-api

```xml
 <dependency>
  	  <groupId>javax-servlet</groupId>
  	  <artifactId>servlet-api</artifactId>
  	  <version>2.0</version>
  	 <scope>provided</scope>
</dependency>
```

4. 运行时依赖范围(runtime),使用该依赖范围的maven依赖，只对测试和运行的classpath有效，对编译的classpath无效，典型例子就是JDBC的驱动实现，项目主代码编译的时候只需要JDK提供的JDBC接口，只有在测试和运行的时候才需要实现上述接口的具体JDBC驱动。

5. 系统依赖范围（system）,该依赖与classpath的关系与 provided依赖范围完全一致，但是系统依赖范围必须通过配置systemPath元素来显示指定依赖文件的路径，此类依赖不是由maven仓库解析的，而且往往与本机系统绑定，可能造成构件的不可移植，因此谨慎使用，systemPath元素可以引用环境变量：

```xml
<dependency>
    <groupId>javax.sql</groupId>
    <artifactId>jdbc-stext</artifactId>
    <version>2.0</version>
    <scope>system</scope>
    <systemPath>${java.home}/lib/rt.jar</systemPath> 
</dependency>
```

6. 导入依赖范围(import),该依赖范围不会对三种classpath产生影响，该依赖范围只能与dependencyManagement元素配合使用，其功能为将目标pom文件中dependencyManagement的配置导入合并到当前pom的dependencyManagement中。有关dependencyManagement的功能请了解maven继承特性

# **1.maven依赖的几个特性**

  **1.1 依赖范围 -scope标签**

  maven在构建过程有3套classpath,我们会根据配置依赖的范围 依赖不同的classpath,如下图：

  ![img](E:\myProject\srclearning\Maven学习\8251e826-f574-39a2-b9c1-a9d51dad01db.png)

**compile：**默认是compile,对 编译 测试 运行 都有效

**provided:**对编译和测试classpath有效，运行的时候不需要加入，例如 jsp 依赖 searvlet api ，比如我们在编译和测试的时候有效但是在运行的时候  容器已经提供servletapi,如果加入会造成冲突

**runtime:**只在测试和运行时 有效，比较典型的例子 jdbc api，只有在启动代码测试或者运行的时候才会启用

**test：**只会在测试时有效，比较典型例子 就是junit ，只有再测试的时候 才会启用

 

  **1.2 依赖传递**

  比如我们引入某一个依赖spring-test,依赖传递特性会很方便帮助我们下来它相关的依赖，而不必有时会因为引入jar有问题而烦恼，但是也有弊端，存在一些不必要的依赖，可能会造成冲突。

  ![img](E:\myProject\srclearning\Maven学习\7243cb94-97f4-3eae-b867-36da367d95e0.png)

  **1.3 依赖排除 -exclusion标签**

  依赖排除的特性 也是为了解决依赖冲突的一个方法，很方便去除依赖传递过程中不必要的依赖。在下面依赖冲冲突会用到 该标签。

  ![img](E:\myProject\srclearning\Maven学习\b62470bb-481b-3df9-82e0-1f192a084b04.png)

  **1.4 依赖冲突产生原因**

  使用maven久了会发现存在依赖冲突的问题,由于依赖的传递特性会引入很多隐式的依赖和现有显示jar版本   所冲突，从而造成版本冲突的问题。要解决这个问题，首先就是要查看pom.xml显式和隐式的依赖类包，    然后通过这个类包树找出我们不想要的依赖类包，手工将其排除在外就可以了。

 

# **2.依赖冲突的解决**

   

  **2.1两个基本原则：**

  **1).短路优先原则**

​    A->B->logback-1.0.jar
​    A->logback-1.1.jar

 

   **2).先声明先优先原则(先解析先引用)**
    与项目A pom中配置 引用坐标的顺序有关,如果依赖B在C前的话 就优先B，反之...

​    A->B->logback-1.0.jar
​    A->C->logback-1.1.jar

 

  ***\*2.2 演示两个原则\**
**

  **1).创建三个maven工程** 

​    maven-01,maven-02,maven-03

  **2).三个工程依赖结构:**  

   maven-01依赖 spring-test,maven-02,maven-03 (maven-02/03需要首先提交本地仓库，maven-01才能找到 ，可以参考寻找构件过程：[1.3 仓库寻找构件过程](http://yanan0628.iteye.com/blog/2270362)) ;

   maven-02依赖commons-logging-1.1.1;

   maven-03工程依赖 commons-logging-1.1.3

   3).看下myEclipse或者执行mvn dependency:tree 查看依赖树：

 

   **myeclispe:依赖树**

  ![点击查看原始大小图片](E:\myProject\srclearning\Maven学习\e7370735-51fe-3da7-8e4e-252156737e5f.png)

  

 **4).冲突解决办法：**

 

  4.1  pom配置1:

  

Xml代码 ![收藏代码](http://yanan0628.iteye.com/images/icon_star.png)

1. **<****dependency****>** 
2.   **<****groupId****>**org.springframework****groupId****>** 
3.   **<****artifactId****>**spring-test****artifactId****>** 
4.   **<****version****>**4.2.2.RELEASE****version****>** 
5.   <!-- 依赖排除 可以排除对commons-logging 的依赖 
6.   **<****exclusions****>** 
7. ​    **<****exclusion****>** 
8. ​      **<****groupId****>**commons-logging****groupId****>** 
9. ​      **<****artifactId****>**commons-logging****artifactId****>** 
10. ​    ****exclusion****>** 
11.   ****exclusions****>** 
12.   --**>** 
13. ****dependency****>** 
14.  
15. <!-- 添加对maven-02依赖 --> 
16. **<****dependency****>** 
17.   **<****groupId****>**com.sohu.train****groupId****>** 
18.   **<****artifactId****>**maven-02****artifactId****>** 
19.   **<****version****>**1.0-SNAPSHORT****version****>** 
20. ****dependency****>** 

 

 

 短路优先原则：

​     maven-01->spring-test->spring-core->commons-loggings-1.2(依赖深度3)

​     maven-01->maven-02->commons-loggings-1.1.1(依赖深度2)

​     所以maven01工程依赖的commons-loggings-1.1.1

 ![点击查看原始大小图片](E:\myProject\srclearning\Maven学习\bd02a995-d7a2-344e-8dcf-ed8fcfd07252.png)

 

  4.2 pom配置2:

  

Xml代码 ![收藏代码](http://yanan0628.iteye.com/images/icon_star.png)

1. **<****dependency****>** 
2.   **<****groupId****>**org.springframework****groupId****>** 
3.   **<****artifactId****>**spring-test****artifactId****>** 
4.   **<****version****>**4.2.2.RELEASE****version****>** 
5.   <!-- 依赖排除 --> 
6.   **<****exclusions****>** 
7. ​    **<****exclusion****>** 
8. ​      **<****groupId****>**commons-logging****groupId****>** 
9. ​      **<****artifactId****>**commons-logging****artifactId****>** 
10. ​    ****exclusion****>** 
11.   ****exclusions****>** 
12. ****dependency****>** 
13.  
14. <!-- 添加对maven-03依赖 --> 
15. **<****dependency****>** 
16.   **<****groupId****>**com.sohu.train****groupId****>** 
17.   **<****artifactId****>**maven-03****artifactId****>** 
18.   **<****version****>**0.0.1-SNAPSHOT****version****>** 
19. ****dependency****>** 
20.  
21. <!-- 添加对maven-02依赖 --> 
22. **<****dependency****>** 
23.   **<****groupId****>**com.sohu.train****groupId****>** 
24.   **<****artifactId****>**maven-02****artifactId****>** 
25.   **<****version****>**1.0-SNAPSHORT****version****>** 
26. ****dependency****>** 

 

  先引用先优先的原则：

​     maven-01->spring-test->spring-core

​     maven-01->maven-02->commons-logging-1.1.1

​     maven-01->maven-03->commons-logging-1.1.3

 如果pom先依赖maven-02则 依赖commons-logging-1.1.1 依赖；反之，如果pom先依赖maven-03则 依赖commons-logging-1.1.3 依赖