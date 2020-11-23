```javascript
function sendAjaxtest() {
      var params = '{"uid": "1","userName": "测试商品"}';
      $.ajax({
          type:"post",
          url:"/s01/c02/m01",
         contentType:"application/json;charset=UTF-8",
         data:JSON.stringify(params),//方法用于将 JavaScript 值转换为 JSON 字符串
          success:function (data) {
              alert(data.name);
          }
      });
}
```



实体类中加入构造方法

```java
public User(String json) throws IOException {
    User param = new ObjectMapper().readValue(json, User.class);
   uid = param.getUid();
   userName=param.getUserName();
}
```



Controller



```java
/*
问题 org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json;charset=UTF-8' not supported 去掉@RequestParam
*问题 [org.springframework.http.converter.HttpMessageNotWritableException: No converter found for return value of type: class java.util.HashMap] 缺少jar包
@RequestBody必须加
* */
@RequestMapping(value = "/m01",method = RequestMethod.POST)
@ResponseBody
public Map m01(@RequestBody User user){
    HashMap ret = new HashMap();
    ret.put("name",user.getUserName());
    return ret;
}
```

jar 包

```java
   // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    compile group: 'com.fasterxml.jackson.core', name: 'jackson-core', version: '2.11.0'
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.11.0'
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    compile group: 'com.fasterxml.jackson.core', name: 'jackson-annotations', version: '2.11.0'
```