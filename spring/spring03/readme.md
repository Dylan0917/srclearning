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



### 给属性赋值（p标签）

```xml
<bean id="person01" class="org.example.Person" p:age="21"></bean>
```



### spel表达式



```xml
<bean id="person03" class="org.example.PersonStaticFactory" factory-method="newPersonIns">
    <constructor-arg index="0" value="#{2}"></constructor-arg> <!--spel-->
    <constructor-arg index="1" value="公孙离"></constructor-arg>
    <constructor-arg index="2" value="24"></constructor-arg>
</bean>
```



####  表示字面量

我们之前使用表达式表示了一个字面量的例子（#{1}），它实际上还可以表示浮点数、String和Boolean的类型，如下所示。当然这很简单，后面复杂的会用到它们。

```dart
#{3.1415926}    //浮点数
#{9.87E4}       //科学计数法表示98700
#{'Hello'}      //String 类型
#{false}        //Boolean 类型
```

#### 引用bean、属性和方法

SpEL可以通过bean的ID去引用这个bean，我们可以使用SpEL将一个bean装配到另外一个bean中，此时beanID作为SpEL表达式（本例还是用上面的sgtPeppers）

```bash
#{sgtPeppers}                   //使用这个bean
#{sgtPeppers.artist}            //引用bean中的属性
#{sgtPeppers.selectArtist()}    //引用bean中的方法
#{sgtPeppers.selectArtist().toUpperCase()}      //方法返回值的操作
```

#### 在表达式中使用类型

如果要在SpEL中访问类作用域的方法和常量的话，要依赖T()这个关键的运算符。例如，为了在SpEL中表达Java的Math类，需要按照如下的方式使用T()运算符。其中T()运算符的结果将会是一个Class类。如果需要的话，我们甚至可以将其装配到一个Class类型的bean属性中。但是T()运算符的真正价值在于它能够访问静态方法和常量

```cpp
T(java.lang.Math)   
T(java.lang.Math).PI        //引用PI的值
T(java.lang.Math).random()  //获取0-1的随机数
```

#### SpEL运算符

算符类型 运算符
 算术比较  +、-、*、/、%、^
 比较运算  <、>、==、<=、>=、lt、gt、rq、le、ge
 逻辑运算  and、or、not、|
 条件运算  ?: (ternary)、?:(Elvis)
 正则表达式 matches

```dart
#{2*T(java.lang.Math).PI * circle.radius}               //圆周长计算
#{T(java.lang.Math).PI * circle.radius^2}               //圆面积计算
#{disc.title + 'by' + disc.artist}                      // + 是连接符
#{counter.total == 100}  #{counter.total eq 100}        //判断是否一致，返回true和false
#{counter.total > 100 ? "Winner" : "Loser"}             //三元表达式 
#{disc.title ?: 'Rattle'}                   //Elvis，如果是null的话结果则为Rattle
#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\\.com'}  //正则表达式
```