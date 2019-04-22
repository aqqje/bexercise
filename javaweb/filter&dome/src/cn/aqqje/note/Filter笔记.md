### filter 笔记
#### 1 Filter

##### 1.1 开始
```text
     接口: javax.servlet.Filter 
     方法: 
        init() :在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次。用于加载资源
        doFilter():每一次请求被拦截资源时，会执行。执行多次
        destroy():在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法。只执行一次。用于释放资源
```
##### 1.2 过滤器细节
- web.xml配置	
```xml
    <filter>
        <filter-name>demo1</filter-name>
        <filter-class>cn.itcast.web.filter.FilterDemo1</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>demo1</filter-name>
        <!-- 拦截路径 -->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
- 注解
```java
    @WebFilter("/*")
```
##### 1.3 过滤器执行流程	
```text
    1. 执行过滤器
    2. 执行放行后的资源
    3. 回来执行过滤器放行代码下边的代码

```
##### 1.4 过滤器配置详解
```text
    拦截路径配置：
        1. 具体资源路径： /index.jsp   只有访问index.jsp资源时，过滤器才会被执行
        2. 拦截目录： /user/*	访问/user下的所有资源时，过滤器都会被执行
        3. 后缀名拦截： *.jsp		访问所有后缀名为jsp资源时，过滤器都会被执行
        4. 拦截所有资源：/*		访问所有资源时，过滤器都会被执行
        拦截方式配置：资源被访问的方式
        注解配置：
            设置dispatcherTypes属性
                1. REQUEST：默认值。浏览器直接请求资源
                2. FORWARD：转发访问资源
                3. INCLUDE：包含访问资源
                4. ERROR：错误跳转资源
                5. ASYNC：异步访问资源
        web.xml配置
            设置<dispatcher></dispatcher>标签即可
```
##### 1.5 过滤器链(配置多个过滤器)	
```text
    执行顺序：如果有两个过滤器：过滤器1和过滤器2
        1. 过滤器1
        2. 过滤器2
        3. 资源执行
        4. 过滤器2
        5. 过滤器1 

    过滤器先后顺序问题：
        1. 注解配置：按照类名的字符串比较规则比较，值小的先执行
            如： AFilter 和 BFilter，AFilter就先执行了。
        2. web.xml配置： <filter-mapping>谁定义在上边，谁先执行
```
##### 1.6 应用
- 登录验证
```java
if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/RESOURCES/css/") || uri.contains("/RESOURCES/js/") ){/*需要放行的资源*/
    filterChain.doFilter(req,res);
    }else{
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            filterChain.doFilter(req,res);
        }else{
            request.setAttribute("loginErrorMsg", "您未登录,请登录!");
            request.getRequestDispatcher("/login.jsp").forward(request,res);
        }
    }
```
- 敏感词汇过滤:使用代理模式
```java
if(method.getName().equals("getParameter")){
    String value = (String)method.invoke(req,args);
    if(value != null){
        for (String str: mgch){
            if(value.contains(str)){
                value = value.replaceAll(str, "***");
            }
        }
    }
    return value;
}
```