## 20200908

#### bean的scop属性

##### singleton作用域(scope 默认值)

==当一个bean的作用域设置为singleton, 那么Spring IOC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例==。换言之，当把 一个bean定义设置为singleton作用域时，Spring IOC容器只会创建该bean定义的唯一实例。这个单一实例会被存储到单例缓存（singleton cache）中，并且所有针对该bean的后续请求和引用都 将返回被缓存的对象实例，这里要注意的是singleton作用域和GOF设计模式中的单例是完全不同的，单例设计模式表示一个ClassLoader中 只有一个class存在，而这里的singleton则表示一个容器对应一个bean，也就是说当一个bean被标识为singleton时 候，spring的IOC容器中只会存在一个该bean。

```xml
配置实例：
<bean id="role" class="spring.chapter2.maryGame.Role" scope="singleton"/>
```

##### prototype

==prototype作用域部署的bean，每一次请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）都会产生一个新的bean实例，相当与一个new的操作==，对于prototype作用域的bean，有一点非常重要，那就是Spring不能对一个prototype bean的整个生命周期负责，容器在初始化、配置、装饰或者是装配完一个prototype实例后，将它交给客户端，随后就对该prototype实例不闻不问了。不管何种作用域，容器都会调用所有对象的初始化生命周期回调方法，而对prototype而言，任何配置好的析构生命周期回调方法都将不会被调用。 清除prototype作用域的对象并释放任何prototype bean所持有的昂贵资源，都是客户端代码的职责。（让Spring容器释放被singleton作用域bean占用资源的一种可行方式是，通过使用 bean的后置处理器，该处理器持有要被清除的bean的引用。）

```xml
<bean  id="Person" class="org.example.Person" init-method="initMethod" destroy-method="destoryMethod" scope="prototype"></bean>
```

如果加入了scope="prototype"表示多例模式，==该模式下不会调用析构函数。==

##### request

request表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP request内有效，配置实例：request、session、global session使用的时候首先要在初始化web的web.xml中做如下配置：如果你使用的是Servlet 2.4及以上的web容器，那么你仅需要在web应用的XML声明文件web.xml中增加下述ContextListener即可

```xml
<web-app>
   ...
  <listener>
<listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
  </listener>
   ...
</web-app>
```

如果是Servlet2.4以前的web容器,那么你要使用一个javax.servlet.Filter的实现：

```xml
<web-app>
 ..
 <filter> 
    <filter-name>requestContextFilter</filter-name> 
    <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
 </filter> 
 <filter-mapping> 
    <filter-name>requestContextFilter</filter-name> 
    <url-pattern>/*</url-pattern>
 </filter-mapping>
   ...
</web-app>
```

#####  session

session作用域表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP session内有效，配置实例：配置实例：和request配置实例的前提一样，配置好web启动文件就可以如下配置：

```xml
<bean id="role" class="spring.chapter2.maryGame.Role" scope="session"/>
```



##### global session

global session作用域类似于标准的HTTP Session作用域，不过它仅仅在基于portlet的web应用中才有意义。Portlet规范定义了全局Session的概念，它被所有构成某个 portlet web应用的各种不同的portlet所共享。在global session作用域中定义的bean被限定于全局portlet Session的生命周期范围内。如果你在web中使用global session作用域来标识bean，那么web会自动当成session类型来使用。配置实例：和request配置实例的前提一样，配置好web启动文件就可以如下配置：

```xml
<bean id="role" class="spring.chapter2.maryGame.Role" scope="global session"/>
```

##### 自定义bean装配作用域

在spring2.0中作用域是可以任意扩展的，你可以自定义作用域，甚至你也可以重新定义已有的作用域（但是你不能覆盖singleton和 prototype），spring的作用域由接口org.springframework.beans.factory.config.Scope来定 义，自定义自己的作用域只要实现该接口即可



```java
package org.springframework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

/**
 * Simple implementation of the {@link AliasRegistry} interface.
 * Serves as base class for
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 * implementations.
 *
 * @author Juergen Hoeller
 * @since 2.5.2
 */
public class SimpleAliasRegistry implements AliasRegistry {

   /** Map from alias to canonical name */
   private final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);


   @Override
   public void registerAlias(String name, String alias) {
      Assert.hasText(name, "'name' must not be empty");
      Assert.hasText(alias, "'alias' must not be empty");
      if (alias.equals(name)) {
         this.aliasMap.remove(alias);
      }
      else {
         String registeredName = this.aliasMap.get(alias);
         if (registeredName != null) {
            if (registeredName.equals(name)) {
               // An existing alias - no need to re-register
               return;
            }
            if (!allowAliasOverriding()) {
               throw new IllegalStateException("Cannot register alias '" + alias + "' for name '" +
                     name + "': It is already registered for name '" + registeredName + "'.");
            }
         }
         checkForAliasCircle(name, alias);
         this.aliasMap.put(alias, name);
      }
   }

   /**
    * Return whether alias overriding is allowed.
    * Default is {@code true}.
    */
   protected boolean allowAliasOverriding() {
      return true;
   }

   /**
    * Determine whether the given name has the given alias registered.
    * @param name the name to check
    * @param alias the alias to look for
    * @since 4.2.1
    */
   public boolean hasAlias(String name, String alias) {
      for (Map.Entry<String, String> entry : this.aliasMap.entrySet()) {
         String registeredName = entry.getValue();
         if (registeredName.equals(name)) {
            String registeredAlias = entry.getKey();
            return (registeredAlias.equals(alias) || hasAlias(registeredAlias, alias));
         }
      }
      return false;
   }

   @Override
   public void removeAlias(String alias) {
      String name = this.aliasMap.remove(alias);
      if (name == null) {
         throw new IllegalStateException("No alias '" + alias + "' registered");
      }
   }

   @Override
   public boolean isAlias(String name) {
      return this.aliasMap.containsKey(name);
   }

   @Override
   public String[] getAliases(String name) {
      List<String> result = new ArrayList<>();
      synchronized (this.aliasMap) {
         retrieveAliases(name, result);
      }
      return StringUtils.toStringArray(result);
   }

   /**
    * Transitively retrieve all aliases for the given name.
    * @param name the target name to find aliases for
    * @param result the resulting aliases list
    */
   private void retrieveAliases(String name, List<String> result) {
      this.aliasMap.forEach((alias, registeredName) -> {
         if (registeredName.equals(name)) {
            result.add(alias);
            retrieveAliases(alias, result);
         }
      });
   }

   /**
    * Resolve all alias target names and aliases registered in this
    * factory, applying the given StringValueResolver to them.
    * <p>The value resolver may for example resolve placeholders
    * in target bean names and even in alias names.
    * @param valueResolver the StringValueResolver to apply
    */
   public void resolveAliases(StringValueResolver valueResolver) {
      Assert.notNull(valueResolver, "StringValueResolver must not be null");
      synchronized (this.aliasMap) {
         Map<String, String> aliasCopy = new HashMap<>(this.aliasMap);
         for (String alias : aliasCopy.keySet()) {
            String registeredName = aliasCopy.get(alias);
            String resolvedAlias = valueResolver.resolveStringValue(alias);
            String resolvedName = valueResolver.resolveStringValue(registeredName);
            if (resolvedAlias == null || resolvedName == null || resolvedAlias.equals(resolvedName)) {
               this.aliasMap.remove(alias);
            }
            else if (!resolvedAlias.equals(alias)) {
               String existingName = this.aliasMap.get(resolvedAlias);
               if (existingName != null) {
                  if (existingName.equals(resolvedName)) {
                     // Pointing to existing alias - just remove placeholder
                     this.aliasMap.remove(alias);
                     break;
                  }
                  throw new IllegalStateException(
                        "Cannot register resolved alias '" + resolvedAlias + "' (original: '" + alias +
                        "') for name '" + resolvedName + "': It is already registered for name '" +
                        registeredName + "'.");
               }
               checkForAliasCircle(resolvedName, resolvedAlias);
               this.aliasMap.remove(alias);
               this.aliasMap.put(resolvedAlias, resolvedName);
            }
            else if (!registeredName.equals(resolvedName)) {
               this.aliasMap.put(alias, resolvedName);
            }
         }
      }
   }

   /**
    * Check whether the given name points back to the given alias as an alias
    * in the other direction already, catching a circular reference upfront
    * and throwing a corresponding IllegalStateException.
    * @param name the candidate name
    * @param alias the candidate alias
    * @see #registerAlias
    * @see #hasAlias
    */
   protected void checkForAliasCircle(String name, String alias) {
      if (hasAlias(alias, name)) {
         throw new IllegalStateException("Cannot register alias '" + alias +
               "' for name '" + name + "': Circular reference - '" +
               name + "' is a direct or indirect alias for '" + alias + "' already");
      }
   }

   /**
    * Determine the raw name, resolving aliases to canonical names.
    * @param name the user-specified name
    * @return the transformed name
    */
   public String canonicalName(String name) {
      String canonicalName = name;
      // Handle aliasing...
      String resolvedName;
      do {
         resolvedName = this.aliasMap.get(canonicalName);
         if (resolvedName != null) {
            canonicalName = resolvedName;
         }
      }
      while (resolvedName != null);
      return canonicalName;
   }

}
```



#### bean的生命周期 初始化方法配置 销毁方法配置



内部bean和外部bean

```xml
<bean  id="Person" class="org.example.Person" init-method="initMethod" destroy-method="destoryMethod" >
    <property name="name" value="cs"></property>
    <property name="car">
        <bean class="org.example.Car" />
    </property>
</bean>
```



分模块配置bean 使用import标签引入

```xml
<import resource="applicationContext.xml"></import>
```