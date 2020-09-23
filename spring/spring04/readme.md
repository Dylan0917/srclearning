## @Autowired 注入bean的过程

注释进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个。当找不到一个匹配的 Bean 时，Spring 容器将抛出 BeanCreationException 异常，并指出必须至少拥有一个匹配的 Bean。

@Autowired 默认是按照byType进行注入的，如果发现找到多个bean，则，又按照byName方式比对，如果还有多个，则报出异常。

```java
例子：

//ExamUserMapper是一个接口
//type可以说是ExamUserMapper
//name可以说是examUserMapper
@Autowired 
private ExamUserMapper examUserMapper;
```

步骤：

　　1. spring先找类型type为ExamUserMapper的bean

　　2. 如果存在且唯一，则OK；

　　3. 如果不唯一，在结果集里，寻找name为examUserMapper的bean。因为bean的name有唯一性，所以，到这里应该能确定是否存在满足要求的bean了

**@Autowired也可以手动指定按照byName方式注入，使用@Qualifier标签，例如：**

```java
@Autowired
@Qualifier(value = "car1")//指定id
private Car car;
```

pring 允许我们通过在使用@Autowired注解时，配合@Qualifier注解完成bean的默认byName注

注释指定注入 Bean 的名称，这样歧义就消除了，可以通过下面的方法解决异常