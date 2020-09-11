### Spring中四种实例化bean的方式



**使用构造器实例化Bean**：Spring IoC容器即能使用默认空构造器也能使用有参数构造器两种方式创建Bean

```xml
<!-- 使用无参数构造器-->
<bean id="person01" class="org.example.Person"></bean>
<!-- 使用有参数构造器-->
<bean id="person02" class="org.example.Person">
    <constructor-arg name="age" value="12"></constructor-arg>
    <constructor-arg name="pid" value="1"></constructor-arg>
    <constructor-arg name="name" value="亚瑟"></constructor-arg>
</bean>
```

**使用静态工厂方式实例化Bean**，使用这种方式除了指定必须的class属性，还要指定factory-method属性来指定实例化Bean的方法，而且使用静态工厂方法也允许指定方法参数，spring IoC容器将调用此属性指定的方法来获取Bean

```xml
<!--静态工厂类-->
 <bean id="person03" class="org.example.PersonStaticFactory" factory-method="newPersonIns">
     <constructor-arg index="0" value="2"></constructor-arg>
     <constructor-arg index="1" value="公孙离"></constructor-arg>
     <constructor-arg index="2" value="24"></constructor-arg>
 </bean>
```

**使用实例工厂方法实例化Bean**，使用这种方式不能指定class属性，此时必须使用factory-bean属性来指定工厂Bean，factory-method属性指定实例化Bean的方法，而且使用实例工厂方法允许指定方法参数，方式和使用构造器方式一样.



```xml
<!--1、定义实例工厂bean-->
<bean id="personInstanceFactory" class="org.example.PersonInstanceFactory"></bean>
<!--使用实例工厂bean创建-->
<bean id="person04" factory-bean="personInstanceFactory" factory-method="newIns">
    <constructor-arg index="0" value="2"></constructor-arg>
    <constructor-arg index="1" value="公孙离"></constructor-arg>
    <constructor-arg index="2" value="24"></constructor-arg>
</bean>
```



**使用依赖注入**

静态工厂方法不需要创建工厂类的实例，实例工厂方法需要