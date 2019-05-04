# spring mvc note
## 1 基本概念
### 1.1 三层架构和 MVC
#### 1.1.1 三层架构 
我们的开发架构一般都是基于两种形式，一种是 C/S 架构，也就是客户端/服务器，另一种是 B/S 架构，也就
是浏览器服务器。在 JavaEE 开发中，几乎全都是基于 B/S架构的开发。那么在 B/S架构中，系统标准的三层架构
包括：表现层、业务层、持久层。三层架构在我们的实际开发中使用的非常多，所以我们课程中的案例也都是基于
三层架构设计的。
##### 1.1.1.1 表现层
也就是我们常说的web层。它负责接收客户端请求，向客户端响应结果，通常客户端使用http协议请求
web 层，web 需要接收 http 请求，完成 http 响应。
表现层包括展示层和控制层：控制层负责接收请求，展示层负责结果的展示。
表现层依赖业务层，接收到客户端请求一般会调用业务层进行业务处理，并将处理结果响应给客户端。
表现层的设计一般都使用 MVC 模型。（MVC 是表现层的设计模型，和其他层没有关系）
##### 1.1.1.2 业务层
也就是我们常说的 service 层。它负责业务逻辑处理，和我们开发项目的需求息息相关。web 层依赖业
务层，但是业务层不依赖 web 层。
业务层在业务处理时可能会依赖持久层，如果要对数据持久化需要保证事务一致性。（也就是我们说的，
事务应该放到业务层来控制）
##### 1.1.1.3 持久层
也就是我们是常说的 dao 层。负责数据持久化，包括数据层即数据库和数据访问层，数据库是对数据进
行持久化的载体，数据访问层是业务层和持久层交互的接口，业务层需要通过数据访问层将数据持久化到数据库
中。通俗的讲，持久层就是和数据库交互，对数据库表进行曾删改查的。

#### 1.1.2 MVC 模型
MVC 全名是 Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，
是一种用于设计创建 Web 应用程序表现层的模式。MVC 中每个部分各司其职.
##### 1.1.1.1 Model（模型）
通常指的就是我们的数据模型。作用一般情况下用于封装数据。
##### 1.1.1.2 View（视图）
通常指的就是我们的 jsp 或者 html。作用一般就是展示数据的。
通常视图是依据模型数据创建的。
##### 1.1.1.3 Controller（控制器）
是应用程序中处理用户交互的部分。作用一般就是处理程序逻辑的。
它相对于前两个不是很好理解，这里举个例子：
例如：我们要保存一个用户的信息，该用户信息中包含了姓名，性别，年龄等等。
这时候表单输入要求年龄必须是 1~100 之间的整数。姓名和性别不能为空。并且把数据填充到模型之中。此时除了 js 的校验之外，服务器端也应该有数据准确性的校验，那么校验就是控制器的该做
的。当校验失败后，由控制器负责把错误页面展示给使用者。
如果校验成功，也是控制器负责把数据填充到模型，并且调用业务层实现完整的业务需求

### 1.2 SpringMVC 概述
#### 1.2.1 SpringMVC 是什么
SpringMVC 是一种基于 Java 的实现 MVC 设计模型的请求驱动类型的轻量级 Web 框架，属于 Spring
FrameWork 的后续产品，已经融合在 Spring Web Flow 里面。Spring 框架提供了构建 Web 应用程序的全功
能 MVC 模块。使用 Spring 可插入的 MVC 架构，从而在使用 Spring 进行 WEB 开发时，可以选择使用 Spring
的 Spring MVC 框架或集成其他 MVC 开发框架，如 Struts1(现在一般不用)，Struts2 等。
SpringMVC 已经成为目前最主流的 MVC 框架之一，并且随着 Spring3.0 的发布，全面超越 Struts2，成
为最优秀的 MVC 框架。
它通过一套注解，让一个简单的 Java 类成为处理请求的控制器，而无须实现任何接口。同时它还支持
RESTful 编程风格的请求
#### 1.2.1 SpringMVC 的优势
```text
1、清晰的角色划分：
    前端控制器（DispatcherServlet）
    请求到处理器映射（HandlerMapping）
    处理器适配器（HandlerAdapter）
    视图解析器（ViewResolver）
    处理器或页面控制器（Controller）
    验证器（ Validator）
    命令对象（Command 请求参数绑定到的对象就叫命令对象）
    表单对象（Form Object 提供给表单展示和提交到的对象就叫表单对象）。
2、分工明确，而且扩展点相当灵活，可以很容易扩展，虽然几乎不需要。
3、由于命令对象就是一个 POJO，无需继承框架特定 API，可以使用命令对象直接作为业务对象。
4、和 Spring 其他框架无缝集成，是其它 Web 框架所不具备的。
5、可适配，通过 HandlerAdapter 可以支持任意的类作为处理器。
6、可定制性，HandlerMapping、ViewResolver 等能够非常简单的定制。
7、功能强大的数据验证、格式化、绑定机制。
8、利用 Spring 提供的 Mock 对象能够非常简单的进行 Web 层单元测试。
9、本地化、主题的解析的支持，使我们更容易进行国际化和主题的切换。
10、强大的 JSP 标签库，使 JSP 编写更容易。
11、还有比如RESTful风格的支持、简单的文件上传、约定大于配置的契约式编程支持、基于注解的零配
置支持等等。
```
#### 1.2.2 SpringMVC 和 Struts2 优劣
```text
共同点：
    它们都是表现层框架，都是基于 MVC 模型编写的。
    它们的底层都离不开原始 ServletAPI。
    它们处理请求的机制都是一个核心控制器。
区别：
    Spring MVC 的入口是 Servlet, 而 Struts2 是 Filter
    Spring MVC 是基于方法设计的，而 Struts2 是基于类，Struts2 每次执行都会创建一个动作类。所
    以 Spring MVC 会稍微比 Struts2 快些。
    Spring MVC 使用更加简洁,同时还支持 JSR303, 处理 ajax 的请求更方便
    (JSR303 是一套 JavaBean 参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注
    解加在我们 JavaBean 的属性上面，就可以在需要校验的时候进行校验了。)
    Struts2 的 OGNL 表达式使页面的开发效率相比 Spring MVC 更高些，但执行效率并没有比 JSTL 提
    升，尤其是 struts2 的表单标签，远没有 html 执行效率高。
```
## 2 快速入门
### 2.1 示例
#### 2.1.1 加载依赖
- spring-webmvc
- spring-web
- spring-core
- spring-aop
- spring-context
- spring-beans
- spring-expression
#### 2.1.2 配置核心控制器 DispatcherServlet
```xml
<!--spring-mvc 前端控制器-->
<servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 配置初始化参数，用于读取 SpringMVC 的配置文件 -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <!-- 配置 servlet 的对象的创建时间点：应用加载时创建。
    取值只能是非 0 正整数，表示启动顺序 -->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```
#### 2.1.3 新建 spring mvc 配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--扫描组件-->
    <context:component-scan base-package="cn.introduction.controller"/>

    <mvc:view-controller path="*.do" view-name="../para" />

    <!--视图解析器-->
    <bean id="ViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--配置类型转化工厂-->
    <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean id="dateConverter" class="cn.introduction.converter.StringToDateConverter"/>
            </set>
        </property>
    </bean>

    <!--开启 spring-mvc 注解支持-->
    <mvc:annotation-driven conversion-service="conversionService"/>
</beans>
```
#### 2.1.4 编写控制器并使用注解配置
```java
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayhello.do")
    public String hello(){
        return "success";
    }
}
```
#### 2.1.5 测试(略)

### 2.2 快速入门的执行过程及原理
####  2.2.1 执行过程
![](img/spring%20mvc%20时势图.png)
```text
1、服务器启动，应用被加载。读取到 web.xml 中的配置创建 spring 容器并且初始化容器中的对象。
从入门案例中可以看到的是：HelloController 和 InternalResourceViewResolver，但是远不
止这些。
2、浏览器发送请求，被 DispatherServlet 捕获，该 Servlet 并不处理请求，而是把请求转发出去。转发
的路径是根据请求 URL，匹配@RequestMapping 中的内容。
3、匹配到了后，执行对应方法。该方法有一个返回值。
4、根据方法的返回值，借助 InternalResourceViewResolver 找到对应的结果视图。
5、渲染结果视图，响应浏览器。
```
####  2.2.2 执行过程
![](img/spring%20mvc%20请求响应流程.png)

## 3 spring mvc 相关组件
### 3.1 DispatcherServlet: 前端控制器
用户请求到达前端控制器，它就相当于 mvc 模式中的 c，dispatcherServlet 是整个流程控制的中心，由
它调用其它组件处理用户的请求，dispatcherServlet 的存在降低了组件之间的耦合性
### 3.2 HandlerMapping ：处理器映射器
HandlerMapping 负责根据用户请求找到 Handler 即处理器，SpringMVC 提供了不同的映射器实现不同的
映射方式，例如：配置文件方式，实现接口方式，注解方式等。
### 3.3 Handler: 处理器
它就是我们开发中要编写的具体业务控制器。由 DispatcherServlet 把用户请求转发到 Handler。由
Handler 对具体的用户请求进行处理。
### 3.4 HandlAdapter: 处理器适配器
通过 HandlerAdapter 对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处理
器进行执行。
### 3.5 View Resolver：视图解析器
View Resolver 负责将处理结果生成 View 视图，View Resolver 首先根据逻辑视图名解析成物理视图名
即具体的页面地址，再生成 View 视图对象，最后对 View 进行渲染将处理结果通过页面展示给用户。
### 3.6 View: 视图
SpringMVC 框架提供了很多的 View 视图类型的支持，包括：jstlView、freemarkerView、pdfView
等。我们最常用的视图就是 jsp。
一般情况下需要通过页面标签或页面模版技术将模型数据通过页面展示给用户，需要由程序员根据业务需求开
发具体的页面。
### 3.7 `<mvc:annotation-driven>` 说明
```text
在 SpringMVC 的各个组件中，处理器映射器、处理器适配器、视图解析器称为 SpringMVC 的三大组件。
使 用 <mvc:annotation-driven> 自动加载 RequestMappingHandlerMapping （处理映射器）和
RequestMappingHandlerAdapter（ 处理适配器 ） ， 可用在 SpringMVC.xml 配置文件中使用
<mvc:annotation-driven>替代注解处理器和适配器的配置。
它就相当于在 xml 中配置了：
```
```xml
    <!-- <mvc:annotation-driven> 如下配置-->
    <!-- Begin -->
    
    <!-- HandlerMapping -->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    
    <!-- HandlerAdapter -->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>
    <bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"/>
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
    
    <!-- HadnlerExceptionResolvers -->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver"/>
    <bean class="org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver"/>
    <bean class="org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver"/>
    <!-- End -->
```
- `注意：一般开发中，我们都需要写上此标签（虽然从入门案例中看，我们不写也行，随着课程的深入，该标签还有具体的使用场景）。`

- `明确：我们只需要编写处理具体业务的控制器以及视图。`

## 4 请求参数的绑定
### 4.1 绑定说明
#### 4.1.1 绑定的机制
```text
表单中请求参数都是基于 key=value 的。
SpringMVC 绑定请求参数的过程是通过把表单提交请求参数，作为控制器中方法参数进行绑定的。
例如 :
<a href="account/findById.do?id=10">findById</a>
    
@RequestMapping(value = "/findById.do")
    public String findById(String id){
        System.out.println(id);
        return "success";
    }
```
#### 4.1.2 支持的数据类型
```text
基本类型参数 ：
    包括基本类型和 String 类型
POJO  类型参数 ：
    包括实体类，以及关联的实体类
    数组和集合类型参数 ：
    包括 List 结构和 Map 结构的集合（包括数组）
SpringMVC 绑定请求参数是自动实现的，但是要想使用，必须遵循使用要求。
```
### 4.2 参数绑定的使用
#### 4.2.1 使用说明
```text
如果是基本类型或者 String  类型：
    要求我们的参数名称必须和控制器中方法的形参名称保持一致。(严格区分大小写)
    如果是 POJO  类型 ，或者 它的关联对象 ：
    要求表单中参数名称和 POJO 类的属性名称保持一致。并且控制器方法的参数类型是 POJO 类型。
    
如果是集合类型, 有两种方式 ：
第一种：
    要求集合类型的请求参数必须在 POJO 中。在表单中请求参数名称要和 POJO 中集合属性名称相同。
    给 List 集合中的元素赋值，使用下标。
    给 Map 集合中的元素赋值，使用键值对。
第二种：
    接收的请求参数是 json 格式数据。需要借助一个注解实现。
注意:它还可以实现一些数据类型自动转换。内置转换器全都在org.springframework.core.convert.support 包下。
    java.lang.Boolean -> java.lang.String : ObjectToStringConverter
    java.lang.Character -> java.lang.Number : CharacterToNumberFactory
    java.lang.Character -> java.lang.String : ObjectToStringConverter
    java.lang.Enum -> java.lang.String : EnumToStringConverter
    java.lang.Number -> java.lang.Character : NumberToCharacterConverter
    java.lang.Number -> java.lang.Number : NumberToNumberConverterFactory
    java.lang.Number -> java.lang.String : ObjectToStringConverter
    java.lang.String -> java.lang.Boolean : StringToBooleanConverter
    java.lang.String -> java.lang.Character : StringToCharacterConverter
    java.lang.String -> java.lang.Enum : StringToEnumConverterFactory
    java.lang.String -> java.lang.Number : StringToNumberConverterFactory
    java.lang.String -> java.util.Locale : StringToLocaleConverter
    java.lang.String -> java.util.Properties : StringToPropertiesConverter
    java.lang.String -> java.util.UUID : StringToUUIDConverter
    java.util.Locale -> java.lang.String : ObjectToStringConverter
    java.util.Properties -> java.lang.String : PropertiesToStringConverter
    java.util.UUID -> java.lang.String : ObjectToStringConverter
    ......
    如遇特殊类型转换要求，需要我们自己编写自定义类型转换器。
```
### 4.2.2 使用示例
#### 4.2.2.1 基本类型和 String 类型作为参数
```text
jsp:
    <a href="param/hrefpara.do?username=aqqje&password=123456">连接数数据绑定</a>

controller:
    @RequestMapping("/hrefpara.do")
        public String hrefpara(String username, String password){
            System.out.println("username:"+username+",password:"+password);
            return "success";
        }   
```
#### 4.2.2.2 POJO 类型作为参数 与 POJO 类中包含 集合类型参数
```text
pojo:
    private String name;
    private String password;
    private double money;
    private User user; // String name; int age; Date date;

    private List<User> list;
    private Map<String, User> map;
jsp:
    <form action="param/formtparaset.do" method="post">
        <%--pojo 基本与引用 属性--%>
        帐号名:<input type="text" name="name"><br/>
        帐号密码:<input type="password" name="password"><br/>
        帐号金额:<input type="text" name="money"><br/>
        用户姓名:<input type="text" name="user.name"><br/>
        用户年龄:<input type="text" name="user.age"><br/>
        <%--pojo list 属性--%>
        用户姓名:<input type="text" name="list[0].name"><br/>
        用户年龄:<input type="text" name="list[0].age"><br/>
        <%--pojo map 属性--%>
        用户姓名:<input type="text" name="map['one'].name"><br/>
        用户年龄:<input type="text" name="map['one'].age"><br/>
        <input type="submit" value="提交">
    </form>

controller:   
    @RequestMapping(value = "/formtparaset.do"/*, method = RequestMethod.POST*/)
        public String formtparaset(Account account){
            System.out.println(account);
            return "success";
        }
```
### 4.3 请求参数乱码问题
#### 4.3.1 post 请求方式
在 web.xml 中配置一个过滤器
```xml
<!-- 配置 springMVC 编码过滤器 -->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter
</filter-class>
<!-- 设置过滤器中的属性值 -->
<init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
</init-param>
<!-- 启动过滤器 -->
<init-param>
    <param-name>forceEncoding</param-name>
    <param-value>true</param-value>
</init-param>
</filter>
<!-- 过滤所有请求 -->
<filter-mapping>
<filter-name>CharacterEncodingFilter</filter-name>
<url-pattern>*.do</url-pattern>
</filter-mapping>
```
在 springmvc 的配置文件中可以配置，静态资源不过滤
```xml
<!-- location 表示路径，mapping 表示文件，**表示该目录下的文件以及子目录的文件 -->
<mvc:resources location="/css/" mapping="/css/**"/>
<mvc:resources location="/img/" mapping="/img/**"/>
<mvc:resources location="/js/" mapping="/img/**"/>
```
#### 4.3.2 get 请求方式
tomacat 对 GET和 POST 请求处理方式是不同的，GET请求的编码问题，要改 tomcat 的 server.xml
配置文件，如下:
```text
<Connector connectionTimeout="20000" port="8080"
protocol="HTTP/1.1" redirectPort="8443"/>
改为：
<Connector connectionTimeout="20000" port="8080"
protocol="HTTP/1.1" redirectPort="8443"
useBodyEncodingForURI="true"/>

如果遇到 ajax 请求仍然乱码，请把：useBodyEncodingForURI="true"改为 URIEncoding="UTF-8"即可。
```

### 4.4 特殊情况
#### 4.4.1 自定义类型转化器
##### 4.4.1.1 应用场景
```html
<form action="param/formtparadata.do" method="post">
    用户名:<input type="text" name="name"><br/>
    年龄:<input type="text" name="age"><br/>
    <%--
        2019-05-01: spring mvo 转化异常-------> 需要自定义类型转化器
        2018/02/03: spring mvc 支持转化
    --%>
    日期:<input type="text" name="date"><br/> 
    
    <input type="submit" value="提交">
</form>
```
##### 4.4.1.2 自定义类型转化器
```text
/**
 * 自定义类型转化器(需要实现 Converter 接口)
 */
public class StringToDateConverter implements Converter<String, Date> {//S:表示接受的类型，T：表示目标类型

    /**
     * 字符转日期转化器
     * @param dateStr
     * @return
     */
    @Override
    public Date convert(String dateStr) {
        return dateStrToDate(dateStr);
    }

    public static Date dateStrToDate(String dateStr){
        DateFormat df = null;
        Date date = null;
        // 字符串为空
        if(dateStr == null || "null".equals(dateStr) || "".equals( dateStr)){
            return date;
        }else{
            // 字符串不为空
            //2012-12-12类型
            if(dateStr.contains("-")){
                df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException("类型转化异常!");
                }
            }else if(dateStr.contains("/")){
                df = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException("类型转化异常!");
                }
            }
        }
        return date;
    }
}
```
##### 4.4.1.3 配置自定义类型转化器
```xml
<!-- 配置类型转换器工厂 -->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <!-- 给工厂注入一个新的类型转换器 -->
    <property name="converters">
        <set>
            <!-- 配置自定义类型转换器 -->
            <bean id="dateConverter" class="cn.introduction.converter.StringToDateConverter"/>
        </set>
    </property>
</bean>
```
##### 4.4.1.4 在 annotation-driven 标签中引用配置的类型转换服务使其生效
```xml
<!--引用自定义类型转换器-->
<mvc:annotation-driven conversion-service="conversionService"/>
```

#### 4.4.2 使用 ServletAPI 对象作为方法参数
```text
SpringMVC 还支持使用原始 ServletAPI 对象作为控制器方法的参数。支持原始 ServletAPI 对象有：
    HttpServletRequest
    HttpServletResponse
    HttpSession
    java.security.Principal
    Locale
    InputStream
    OutputStream
    Reader
    Writer
我们可以把上述对象，直接写在控制的方法参数中使用。
部分示例代码:
    jsp:
        <!-- 原始 ServletAPI 作为控制器参数 -->
        <a href="account/testServletAPI">测试访问 ServletAPI</a>
    controller:
        /**
        * 测试访问 testServletAPI
        * @return
        */
        @RequestMapping("/testServletAPI")
        public String testServletAPI(HttpServletRequest request,HttpServletResponse response,
        HttpSession session) {
            System.out.println(request);
            System.out.println(response);
            System.out.println(session);
            return "success";
        }
```
## 5 常用注解 
### 5.1 RequestMapping  
#### 5.1.1 使用说明 
```text
源码：
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Mapping
    public @interface RequestMapping {
    }
作用：
    用于建立请求 URL 和处理请求方法之间的对应关系。
出现位置：
    类上：
        请求 URL 的第一级访问目录。此处不写的话，就相当于应用的根目录。写的话需要以/开头。
        它出现的目的是为了使我们的 URL 可以按照模块化管理:
    例如：
        账户模块：
            /account/add
            /account/update
            /account/delete
            ...
        订单模块：
            /order/add
            /order/update
            /order/delete
    /account,/order部分就是把 RequsetMappding 写在类上，使我们的 URL 更加精细。
    
    方法上：
        请求 URL 的第二级访问目录。
    属性：
        value：用于指定请求的 URL。它和 path 属性的作用是一样的。
        method：用于指定请求的方式。
        params：用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的 key 和 value 必须和配置的一模一样。
    例如：
        params = {"accountName"}，表示请求参数必须有 accountName
        params = {"moeny!100"}，表示请求参数中 money 不能是 100。
        headers：用于指定限制请求消息头的条件。
    注意：
        以上四个属性只要出现 2 个或以上时，他们的关系是与的关系。
```
#### 5.1.2 使用示例
- controller
```java
/**
* RequestMapping 注解出现的位置
*/
@Controller("accountController")
@RequestMapping("/account")
public class AccountController {
    
    @RequestMapping("/findAccount")
    public String findAccount() {
    System.out.println("查询了账户。。。。");
    return "success";
    }
}
```
- jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>requestmapping 的使用</title>
</head>
<body>
    <!-- 第一种访问方式 -->
    <a href="${pageContext.request.contextPath}/account/findAccount">
    查询账户
    </a>
    <br/>
    <!-- 第二种访问方式 -->
    <a href="account/findAccount">查询账户</a>
</body>
</html>
```
注意：在当我们使用此种方式配置时，在 jsp 中第二种写法时，不要在访问 URL  前面加/ ，否则无法找到资源。
#### 5.1.3 method 属性的示例
- controller
```text
/**
* 保存账户
* @return
*/
@RequestMapping(value="/saveAccount",method=RequestMethod.POST)
public String saveAccount() {
    System.out.println("保存了账户");
return "success";
}
```
- jsp
```html
<!-- 请求方式的示例 -->
<a href="account/saveAccount">保存账户，get 请求</a>
<br/>
<form action="account/saveAccount" method="post">
    <input type="submit" value=" 保存账户， post 请求 ">
</form>
```
- 注意：当使用 get 请求时，提示错误信息是 405，信息是方法不支持 get 方式请求

#### 5.1.4 params 属性的示例
- contrller
```java
/**
* 删除账户
* @return
*/
@RequestMapping(value="/removeAccount",params= {"accountName","money>100"})
public String removeAccount() {
    System.out.println("删除了账户");
    return "success";
}
```
- jsp
```html
<a href="account/removeAccount?accountName=aaa&money>100">删除账户，金额 100</a>
<br/>
<a href="account/removeAccount?accountName=aaa&money>150">删除账户，金额 150</a>
```
- 注意：当我们点击第一个超链接时,可以访问成功。当我们点击第二个超链接时，无法访问。

### 5.2 RequestParam
#### 5.2.1 使用说明 
```text
作用：
    把请求中指定名称的参数给控制器中的形参赋值。
属性：
value：请求参数中的名称。
    required：请求参数中是否必须提供此参数。默认值：true。表示必须提供，如果不提供将报错。
```
#### 5.2.2 使用示例
- jsp
```html
<!-- requestParams 注解的使用 -->
<a href="springmvc/useRequestParam?name=test">requestParam 注解</a>
```
- controller
```java
/**
* requestParams 注解的使用
* @param username
* @return
*/
@RequestMapping("/useRequestParam")
public String  useRequestParam(@RequestParam("name")String username,
@RequestParam(value="age",required=false)Integer age){
    System.out.println(username+","+age);
    return "success";
    
    /**
    *  运行结果:test,null
    */
}
```
### 5.3 RequestBody
#### 5.3.1 使用说明 
```text
作用：
    用于获取请求体内容。直接使用得到是 key=value&key=value...结构的数据。
    get 请求方式不适用。
属性：
    required：是否必须有请求体。默认值是:true。当取值为 true 时,get 请求方式会报错。如果取值
    为 false，get 请求得到是 null。
```
#### 5.4.2 使用示例
- jsp
```html
<!--post 请求-->
<!-- request body 注解 -->
<form action="springmvc/useRequestBody" method="post">
    用户名称：<input type="text" name="username" ><br/>
    用户密码：<input type="password" name="password" ><br/>
    用户年龄：<input type="text" name="age" ><br/>
    <input type="submit" value=" 保存 ">
</form>
<!--get 请求-->
<a href="springmvc/useRequestBody?body=test">requestBody 注解 get 请求</a>
```
- controller
```java
/**
* RequestBody 注解
* @param user
* @return
*/
@RequestMapping("/useRequestBody")
public String  useRequestBody(@RequestBody(required=false) String body){
System.out.println(body);
    return "success";
}
/** 运行结果:
*       post: usernamecgx&password123&age23
*       get: null
*/
```
### 5.4 PathVaribale
#### 5.4.1 使用说明 
```text
作用：
    用于绑定 url 中的占位符。例如：请求 url 中 /delete/{id}，这个{id}就是 url 占位符。
    url 支持占位符是 spring3.0 之后加入的。是 springmvc 支持 rest 风格 URL 的一个重要标志。
属性：
    value：用于指定 url 中占位符名称。
    required：是否必须提供占位符
```
#### 5.5.2 使用示例
- jsp
```html
<!-- PathVariable 注解 -->
<a href="springmvc/usePathVariable/100">pathVariable 注解</a>
```
- controller
```java
/**
* PathVariable 注解
* @param user
* @return
*/
@RequestMapping("/usePathVariable/{id}")
public String  usePathVariable(@PathVariable("id") Integer id){
    System.out.println(id);
    return "success";
}
/** 运行结果:
*       100
*/
```
### 5.5 RequestHeader
#### 5.5.1 使用说明 
```text
作用：
    用于获取请求消息头。
属性：
    value：提供消息头名称
    required：是否必须有此消息头
    
注：在实际开发中一般不怎么用
```
#### 5.5.2 使用示例
- jsp
```html
<!-- RequestHeader 注解 -->
<a href="springmvc/useRequestHeader">获取请求消息头</a>
```
- controller
```java
/**
* RequestHeader 注解
* @param user
* @return
*/
@RequestMapping("/useRequestHeader")
public String  useRequestHeader(@RequestHeader(value="Accept-Language",
    required=false)String requestHeader){
    System.out.println(requestHeader);
    return "success";
}
/** 运行结果:
*       zh-CN,zh;q=0.9
*/
```
### 5.6 ModelAttribute
#### 5.6.1 使用说明 
```text
作用：
    该注解是 SpringMVC4.3 版本以后新加入的。它可以用于修饰方法和参数。
    出现在方法上，表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可
    以修饰有具体返回值的方法。
    出现在参数上，获取指定的数据给参数赋值。
属性：
    value：用于获取数据的 key。key 可以是 POJO 的属性名称，也可以是 map 结构的 key。
应用场景：
    当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
例如：
    我们在编辑一个用户时，用户有一个创建信息字段，该字段的值是不允许被修改的。在提交表单数
    据是肯定没有此字段的内容，一旦更新会把该字段内容置为 null，此时就可以使用此注解解决问题。
```
#### 5.6.2 使用示例
##### 5.6.2.1 基于 POJO  属性的基本使用
- jsp
```html
<!-- ModelAttribute 注解的基本使用 -->
<a href="springmvc/testModelAttribute?username=test">测试 modelattribute</a>
```
- controller
```java
/**
* 被 ModelAttribute 修饰的方法
* @param user
*/
@ModelAttribute
public void showModel(User user) {
System.out.println("执行了 showModel 方法"+user.getUsername());
}
/**
* 接收请求的方法
* @param user
* @return
*/
@RequestMapping("/testModelAttribute")
public String testModelAttribute(User user) {
    System.out.println("执行了控制器的方法"+user.getUsername());
    return "success";
}
/** 运行结果: 
*       执行了 showModel 方法test
*       执行了控制器的方法test
*/
```

##### 5.6.2.2 基于 Map 的应用场景示例 1 ：ModelAttribute 
- 需求：修改用户信息，要求用户的密码不能修改
- jsp
```html
<!-- 修改用户信息 -->
<form action="springmvc/updateUser" method="post">
用户名称：<input type="text" name="username" ><br/>
用户年龄：<input type="text" name="age" ><br/>
<input type="submit" value=" 保存 ">
</form>
```
- controller
```java
/**
* 查询数据库中用户信息
* @param user
*/
@ModelAttribute
public User showModel(String username) {
    //模拟去数据库查询
    User abc = findUserByName(username);
    System.out.println("执行了 showModel 方法"+abc);
    return abc;
}
/**
* 模拟修改用户方法
* @param user
* @return
*/
@RequestMapping("/updateUser")
public String testModelAttribute(User user) {
    System.out.println("控制器中处理请求的方法：修改用户："+user);
    return "success";
}
/**
* 模拟去数据库查询
* @param username
* @return
*/
private User findUserByName(String username) {
    User user = new User();
    user.setUsername(username);
    user.setAge(19);
    user.setPassword("123456");
    return user;
}
/** 运行结果:
*       执行了 showModel 方法User [username=cgx, password 123456, age=19]
*/      控制器中处理请求的方法：修改用户：User [username=cgx, password 123456, age=23]
```
##### 5.6.2.3 基于 Map 的应用场景示例 1 ：ModelAttribute  修饰方法 不
- 需求：修改用户信息，要求用户的密码不能修改
- jsp
```html
<!-- 修改用户信息 -->
<form action="springmvc/updateUser" method="post">
    用户名称：<input type="text" name="username" ><br/>
    用户年龄：<input type="text" name="age" ><br/>
    <input type="submit" value=" 保存 ">
</form>
```
- controller
```java
/**
* 查询数据库中用户信息
* @param user
*/
@ModelAttribute
public void showModel(String username,Map<String,User> map) {
    //模拟去数据库查询
    User user = findUserByName(username);
    System.out.println("执行了 showModel 方法"+user);
    map.put("abc",user);
}
/**
* 模拟修改用户方法
* @param user
* @return
*/
@RequestMapping("/updateUser")
public String testModelAttribute(@ModelAttribute("abc")User user) {
    System.out.println("控制器中处理请求的方法：修改用户："+user);
    return "success";
}
/**
* 模拟去数据库查询
* @param username
* @return
*/
private User findUserByName(String username) {
    User user = new User();
    user.setUsername(username);
    user.setAge(19);
    user.setPassword("123456");
    return user;
}
/** 运行结果:
*       执行了 showModel 方法User [username=cgx, password 123456, age=19]
*/ 
```
### 5.7 SessionAttribute
#### 5.7.1 使用说明 
```text
作用：
    用于多次执行控制器方法间的参数共享。
属性：
    value：用于指定存入的属性名称
    type：用于指定存入的数据类型。
```
#### 5.7.2 使用示例
- jsp
```html
<!-- SessionAttribute 注解的使用 -->
<a href="springmvc/testPut">存入 SessionAttribute</a>
<hr/>
<a href="springmvc/testGet">取出 SessionAttribute</a>
<hr/>
<a href="springmvc/testClean">清除 SessionAttribute</a>
```
- controller
```java
/**
* SessionAttribute 注解的使用
*/
@Controller("sessionAttributeController")
@RequestMapping("/springmvc")
@SessionAttributes(value ={"username","password"},types={Integer.class})
public class SessionAttributeController {
/**
* 把数据存入 SessionAttribute
* @param model
* @return
* Model 是 spring 提供的一个接口，该接口有一个实现类 ExtendedModelMap
* 该类继承了 ModelMap，而 ModelMap 就是 LinkedHashMap 子类
*/
@RequestMapping("/testPut")
public String testPut(Model model){
    model.addAttribute("username", "泰斯特");
    model.addAttribute("password","123456");
    model.addAttribute("age", 31);
    //跳转之前将数据保存到 username、password 和 age 中，因为注解@SessionAttribute 中有这几个参数
    return "success";
}
@RequestMapping("/testGet")
public String testGet(ModelMap model){
    System.out.println(model.get("username")+";"+model.get("password")+";"+model.get("a
    ge"));
    return "success";
}
@RequestMapping("/testClean")
public String complete(SessionStatus sessionStatus){
    sessionStatus.setComplete();
    return "success";
    }
}
/** 运行结果:
*       存入了数据
*       获取了数据: 泰斯特: 123456; 31
*       清除了数据
*       获取了数据: null;null;null;
*/      

```
### 5.8 CookieValue
#### 5.8.1 使用说明 
```text
作用：
    用于把指定 cookie 名称的值传入控制器方法参数。
属性：
    value：指定 cookie 的名称。
    required：是否必须有此 cookie。
```
#### 5.8.2 使用示例
- jsp
```html
<!-- CookieValue 注解 -->
<a href="springmvc/useCookieValue">绑定 cookie 的值</a>
```
- controller
```java
/**
* Cookie 注解注解
* @param user
* @return
*/
@RequestMapping("/useCookieValue")
public String  useCookieValue(@CookieValue(value="JSESSIONID",required=false)
String cookieValue){
    System.out.println(cookieValue);
    return "success";
}
/** 运行结果:
*       544S5DFDFSB45SDFEVV45DSFEVSDF
*/
```
## 6 响应数据和结果视图
### 6.1 返回值分类
#### 6.1.1 字符串
controller 方法返回字符串可以指定逻辑视图名，通过视图解析器解析为物理视图地址。
```java
/**
 * 字符串
 * @return
 */
@RequestMapping("/string.do")
public String string(){
    System.out.println("string............");
    return "success";
}
```
#### 6.1.2 void
```java
@RequestMapping("/idvoid.do")
    public void idvoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*User idvoid = new User();
        idvoid.setName("idvoid");
        idvoid.setGender("女");
        idvoid.setAge(18);
        request.setAttribute("idvoid", idvoid);*/
        // 转发请求
        /*request.getRequestDispatcher("/pages/success.jsp").forward(request, response);*/
        // 重定向
        User idvoid = new User();
        idvoid.setName("idvoid");
        idvoid.setGender("女");
        idvoid.setAge(18);
        request.getSession().setAttribute("idvoid", idvoid);
        response.sendRedirect("string.do");
        // 设置中文乱码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // response 直接响应结果
        /*response.getWriter().write(idvoid.toString());*/
    }
```
#### 6.1.3 ModelAndView
ModelAndView 是 SpringMVC 为我们提供的一个对象，该对象也可以用作控制器方法的返回值。
```java
/**
 * modelAndView
 * @return
 */
@RequestMapping("/modelAndView.do")
public ModelAndView modelAndView(){
    ModelAndView mv = new ModelAndView();
    System.out.println("string............");
    User modelAndView = new User();
    modelAndView.setName("modelAndView");
    modelAndView.setAge(20);
    modelAndView.setGender("男");
    mv.addObject("modelAndView", modelAndView);
    mv.setViewName("success");
    return mv;
}
```
### 6.2 转发和重定向
controller 方法在提供了 String 类型的返回值之后，默认就是请求转发。我们也可以写成：
#### 6.2.1 forward转发与Redirect重定向
```java
/**
 * forword or redirect 关键字页面跳转
 */
@RequestMapping("/forwardOrRedirect")
public String forwardOrRedirect(){
    System.out.println("forwardOrRedirect..........");
    // 转发
    return "forward:/pages/success.jsp";
    // 重定向
    //return "redirect:/pages/success.jsp";
}
```

### 6.3 ResponseBody响应json数据
#### 6.3.1 使用说明
```text
作用：
    该注解用于将 Controller 的方法返回的对象，通过 HttpMessageConverter 接口转换为指定格式的
    数据如：json,xml 等，通过 Response 响应给客户端
```
#### 6.3.2 使用示例
- 需求：
使用@ResponseBody 注解实现将 controller 方法返回对象转换为 json 响应给客户端。
-jar：Springmvc 默认用 MappingJacksonHttpMessageConverter 对 json 数据进行转换，需要加入
jackson `2.7.0` 以上 的包。
```text
    jackson-core
    jackson-databind
    jackson-annotations
```
- jsp
```html
<script>
    $(function(){
        $("#bt1").click(function(){
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/requestBody.do",
                contentType: "application/json;charset=utf-8",
                data: '{"name":"aqqje", "gender":"男", "age":20}',
                dataType: "json",
                success: function(data){
                    $("#user").text(data.name+data.age+data.gender);
                }
            });
        });
    });
</script>

<button id="bt1">点我发送 ajax</button>
```
- controller
```java
/**
 * @ResponseBody 与 @RequestBody + jackson 实现 json
 */
@RequestMapping(value = "/requestBody.do", method = RequestMethod.POST)
public @ResponseBody User requestBody(@RequestBody User user){
    user.setAge(100);
    System.out.println("User修改后:"+user);
    return user;
}
```

## 7 SpringMVC 实现文件上传
### 7.1 技术要点
```text
A form 表单的 enctype 取值必须是：multipart/form-data
(默认值是:application/x-www-form-urlencoded)
enctype:是表单请求正文的类型
B method 属性取值必须是 Post
C 提供一个文件选择域<input type=”file” />
```
### 7.2 上传原理
当 form 表单的 enctype 取值不是默认值后，request.getParameter()将失效。
enctype=”application/x-www-form-urlencoded”时，form 表单的正文内容是：
key=value&key=value&key=value
当 form 表单的 enctype 取值为 Mutilpart/form-data 时，请求正文内容就变成：
```text
每一部分都是 MIME 类型描述的正文
-----------------------------7de1a433602ac  分界符
Content-Disposition: form-data; name="userName"  协议头
aaa 协议的正文
-----------------------------7de1a433602ac
Content-Disposition:  form-data;  name="file";
filename="C:\Users\zhy\Desktop\fileupload_demofile\b.txt"
Content-Type: text/plain 协议的类型（MIME  类型）
bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
-----------------------------7de1a433602ac--
```
### 7.2 传统方式实现文件上传
#### 7.2.1 依赖 jar 包
使用 Commons-fileupload 组件实现文件上传，需要导入该组件相应的支撑 jar 包：Commons-fileupload 和
commons-io。commons-io 不属于文件上传组件的开发 jar 文件，但Commons-fileupload 组件从 1.1 版本开始，它
工作时需要 commons-io 包的支持。
- commons-fileupload
- commons-io
#### 7.2.2 示例
- jsp
````html
<!--文件上传传统方式-->
<form action="user/fileUpload1.do" enctype="multipart/form-data" method="post">
    上传文件:<input name="file" type="file"><br>
<input type="submit"  value="提交">
</form>
````
- controller
```java
/**
 * 文件上传传统方式
 * @param request
 */
@RequestMapping(value = "/fileUpload1.do", method = RequestMethod.POST)
public String fileUpload(HttpServletRequest request){
    System.out.println("文件上传传统方式.............");
    // 获取上传路径
    String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
    File file = new File(realPath);
    // 判断路径是否存在
    if(!file.exists()){
        // 不存在路径则新建
        file.mkdirs();
    }
    System.out.println(realPath);
    // 解析 request 对象, 获取上传文件项
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    // 解析 request
    try {
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item: fileItems){
            // 获取上传文件项
            if(!item.isFormField()){
                String fileName = item.getName();
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                fileName = uuid+"_"+fileName;
                // 完成上传
                System.out.println(realPath);
                item.write(new File(realPath, fileName));
                // 删除临时文件
                item.delete();
                return "success";
            }
        }           

    } catch (Exception e) {
        e.printStackTrace();
    }
    return "update";
}
```

### 7.3 springmvc传统方式实现文件上传
传统方式的文件上传，指的是我们上传的文件和访问的应用存在于同一台服务器上。
并且上传完成之后，浏览器可能跳转。
#### 7.3.1 技术要点
- springmvc.xml 配置文件中文件上传解析器的 id 是固定的(multipartResolver)，否则无法实现请求参数(包含文件与)的绑定。
- 表单中 name 属性必须与 conrtoller 中方法的参数名一致
#### 7.3.2 依赖 jar 包
- commons-fileupload
- commons-io
#### 7.3.3 示例
- jsp
```html
<!--spring mvc 文件上传方式:-->
<form action="user/fileUpload2.do" enctype="multipart/form-data" method="post">
    <%--name: 属性名指定必须与 controller 的形参一致--%>
    上传文件:<input name="uploat" type="file"><br>
    <input type="submit"  value="提交">
</form>
```
- controller
```java
/**
 * spring mvc 文件上传方式
 * @param request
 */
@RequestMapping(value = "/fileUpload2.do", method = RequestMethod.POST)
public String fileUpload2(HttpServletRequest request, MultipartFile uploat){
    System.out.println("spring mvc 文件上传方式.............");
    // 获取上传路径
    String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
    File file = new File(realPath);
    // 判断路径是否存在
    if(!file.exists()){
        // 不存在路径则新建
        file.mkdirs();
    }
    // 获取文件名
    String filename = uploat.getOriginalFilename();
    // 上传文件
    try {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename = uuid+"_"+filename;
        uploat.transferTo(new File(realPath, filename));
        return "success";
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "update";
}
```
- springmvc.xml
```xml
<!--文件上传解析器 : multipartResolver id 必须指定为此值-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!--最大上传大小-->
    <property name="maxUploadSize" value="5242880"/>
</bean>
```
### 7.4 springmvc 跨服务器方式的文件上传
#### 7.4.1 分服务器的目的
```text
在实际开发中，我们会有很多处理不同功能的服务器。例如：
    应用服务器：负责部署我们的应用
    数据库服务器：运行我们的数据库
    缓存和消息服务器：负责处理大并发访问的缓存和消息
    文件服务器：负责存储用户上传文件的服务器。
    ( 注意：此处说的不是服务器集群）
分服务器处理的目的是让服务器各司其职，从而提高我们项目 的运行效率
```
#### 7.4.2 依赖
- commons-fileupload
- commons-io
- jersey-core
- jersey-client

#### 7.4.3 示例
- jsp
```html
<!--跨服务器文件上传-->
<form action="user/fileUpload3.do" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="picname"/><br/>
    图片：<input type="file" name="uploadFile"/><br/>
    <input type="submit" value="上传"/>
</form>
```
- controller
```java
// 跨服务器上传服务器文件目录
public static final String FILESERVICEURL = "http://localhost:9090/imgupload/upload/";
/**
 * 跨服务器文件上传
 * @param picname
 * @param uploadFile
 * @return
 */
@RequestMapping(value = "/fileUpload3.do")
public String fileUpload3(String picname, MultipartFile uploadFile) throws IOException {


    String encodeFileName = URLEncoder.encode(picname, "utf-8");
    String fileName = "";
    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
    /*文件原名*/
    String uploadName = URLEncoder.encode(uploadFile.getOriginalFilename(), "utf-8");
    /*文件扩展名*/
    String extendName = uploadName.substring(uploadName.lastIndexOf(".") + 1, uploadName.length());
    /*获取文件名*/
    if(encodeFileName != null && !"".equals(encodeFileName) && !"null".equals(encodeFileName)){
        fileName = uuid + "_" + encodeFileName + "." + extendName;
    }else{
        fileName = uuid + "_" + uploadName;
    }
    System.out.println("文件地址:"+FILESERVICEURL+fileName);
    /*创建 sun 公司提供的 jersey 包中的 Client 对象*/
    Client client = Client.create();
    /*指定上传文件的地址，该地址是 web 路径*/
    WebResource resource = client.resource(FILESERVICEURL + fileName);
    /*实现上传*/
    String result = resource.put(String.class, uploadFile.getBytes());
    System.out.println(result);
    return "success";
}
```
- springmvc.xml 与 springmvc传统上传方式 一致


## 8 SpringMVC 中的异常处理
### 8.1 异常处理的思路
系统中异常包括两类：预期异常和运行时异常 RuntimeException，前者通过捕获异常从而获取异常信息，
后者主要通过规范代码开发、测试通过手段减少运行时异常的发生。
系统的 dao、service、controller 出现都通过 throws Exception 向上抛出，最后由 springmvc 前端
控制器交由异常处理器进行异常处理，如下图：
![](img/springmvc%20异常处理思路.png)
### 8.2 实现
#### 8.2.1 自定义异常
```java
/**
 * 自定义异常
 */
public class CustomException extends Exception {
    private String message;
    public CustomException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
```
#### 8.2.2 自定义异常处理器
```java
/**
 * 自定义异常处理器
 */
public class CustomExceptionResovler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        CustomException customException = null;

        // 如果抛出的是系统自定义异常则直接转换
        if(e instanceof CustomException){
            customException = (CustomException)e;
        }else{
            // 如果抛出的不是系统自定义异常则重新构造一个系统错误异常。
            customException = new CustomException("系统错误，请与系统管理 员联系！");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", customException.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
```
#### 8.2.3 配置异常处理器
```xml
<!--异常处理器-->
<bean id="customExceptionResovler" class="cn.exception.exception.CustomExceptionResovler"/>
```

## 9 SpringMVC 中的拦截器
### 9.1 拦截器的作用
```text
Spring MVC 的处理器拦截器类似于 Servlet 开发中的过滤器 Filter，用于对处理器进行预处理和后处理。
用户可以自己定义一些拦截器来实现特定的功能。
谈到拦截器，还要向大家提一个词——拦截器链（Interceptor Chain）。拦截器链就是将拦截器按一定的顺
序联结成一条链。在访问被拦截的方法或字段时，拦截器链中的拦截器就会按其之前定义的顺序被调用。
说到这里，可能大家脑海中有了一个疑问，这不是我们之前学的过滤器吗？是的它和过滤器是有几分相似，但
是也有区别，接下来我们就来说说他们的区别：
    过滤器是 servlet 规范中的一部分，任何 java web 工程都可以使用。
    拦截器是 SpringMVC 框架自己的，只有使用了 SpringMVC 框架的工程才能用。
    过滤器在 url-pattern 中配置了/*之后，可以对所有要访问的资源拦截。
    拦截器它是只会拦截访问的控制器方法，如果访问的是 jsp，html,css,image 或者 js 是不会进行拦截的。
它也是 AOP 思想的具体应用。
我们要想自定义拦截器， 要求必须实现：HandlerInterceptor  接口。
```
### 9.2 自定义拦截器
#### 9.2.1 自定义拦截器 实现 HandlerInterceptor 接口
```java
/**
* 自定义拦截器
*/
public class HandlerInterceptorDemo1 implements HandlerInterceptor {
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponseresponse, Object handler) 
throws Exception {
    System.out.println("preHandle 拦截器拦截了");
    return true;
}
@Override
public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception {
    System.out.println("postHandle 方法执行了");
}
@Override
public void afterCompletion(HttpServletRequest request, HttpServletResponseresponse, Object handler, Exception ex)
throws Exception {
    System.out.println("afterCompletion 方法执行了");
    }
}
```
### 9.3 配置拦截器
```xml
<!--配置拦截器-->
<mvc:interceptors>
    <mvc:interceptor>
        <!--  用于指定对拦截的 url -->
        <mvc:mapping path="/**"/>
        <!--  用于指定排除的 url-->
        <bean id="customInterceptor1" class="cn.interceptor.interceptor.CustomInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```
### 9.4 拦截器的细节
#### 9.4.1 拦截器的放行
放行的含义是指，如果有下一个拦截器就执行下一个，如果该拦截器处于拦截器链的最后一个，则执行控制器
中的方法。
![](img/拦截器的放行.png)

##### 9.4.2 拦截器中方法的说明
```java
public interface HandlerInterceptor {
    /**
    *  如何调用：
    * 按拦截器定义顺序调用
    *  何时调用：
    * 只要配置了都会调用
    *  有什么用：
    * 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去
    * 进行处理，则返回 true。
    * 如果程序员决定不需要再调用其他的组件去处理请求，则返回 false。
    */
    default boolean preHandle(HttpServletRequest request, HttpServletResponse
    response, Object handler)
    throws Exception {
    return true;
    }
    /**
    *  如何调用：
    * 按拦截器定义逆序调用
    *  何 时调用：
    * 在拦截器链内所有拦截器返成功调用
    *  有什么用：
    * 在业务处理器处理完请求后，但是 DispatcherServlet 向客户端返回响应前被调用，
    * 在该方法中对用户请求 request 进行处理。
    */
    default  void  postHandle(HttpServletRequest  request,  HttpServletResponse
    response, Object handler,
    @Nullable ModelAndView modelAndView) throws Exception {
    }
    /**
    *  如何调用：
    * 按拦截器定义逆序调用
    *  何时调用：
    * 只有 preHandle 返回 true 才调用
    *  有什么用：
    * 在 DispatcherServlet 完全处理完请求后被调用，
    * 可以在该方法中进行一些资源清理的操作。
    */
    default void afterCompletion(HttpServletRequest request, HttpServletResponse
    response, Object handler,
    @Nullable Exception ex) throws Exception {
    }
}
```
##### 9.4.3 拦截器的作用路径
```xml
<!--配置拦截器-->
<mvc:interceptors>
    <mvc:interceptor>
        <!--  用于指定对拦截的 url -->
        <mvc:mapping path="/user/*"/>
        <!--  用于指定排除的 url-->
        <mvc:exclude-mapping path="/user/tologin.do"/>
        <mvc:exclude-mapping path="/user/login.do"/>
        <bean id="customInterceptor1" class="cn.interceptor.interceptor.CustomInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```
##### 9.4.4 多个拦截器的执行
多个拦截器是按照配置的顺序决定的。
![](img/spring%20interceptor%20执行顺序.png)

#### 9.5 拦截器的简单案例（验证用户是否登录）
- jsp
```html
<a href="${pageContext.request.contextPath}/user/tologin.do">登录</a><br>
<hr>

<a href="${pageContext.request.contextPath}/user/main.do">main</a><br>
```
- controller
```java
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 测试拦截用户请求
     * @return
     */
    @RequestMapping("/main.do")
    public String main(){
         return "main";
    }

    /**
     * 跳转至登录页
     * @return
     */
    @RequestMapping("/tologin.do")
    public String tologin(){
        return "login";
    }

    /**
     * 登录请求处理
     * @return
     */
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, String name, String pwd){
        if(name == null || pwd == null){
            return "error";
        }
        User loginUser = findByName(name);
        if(loginUser.getPassword().equals(pwd)){
            request.getSession().setAttribute("loginUser", loginUser);
            return "success";
        }else{
            return "error";
        }
    }
    /**
     * 模拟业务层
     * @param name
     * @return
     */
    public User findByName(String name){
        User user = new User(name, "123");
        return user;
    }
}
```
- interceptor
```java
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("CustomInterceptor");
        // 拦截用户请求
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        // 已登录
        if(loginUser != null){
            return true;
        }
        // 未登录
        response.sendRedirect(request.getContextPath()+"/user/tologin.do");
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
```
- springmvc.xml
```xml
<!--配置拦截器-->
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/user/*"/>
        <mvc:exclude-mapping path="/user/tologin.do"/>
        <mvc:exclude-mapping path="/user/login.do"/>
        <bean id="customInterceptor1" class="cn.interceptor.interceptor.CustomInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```











## 10 SESTFUL 风格
### 10.1 什么是 rest
REST（英文：Representational State Transfer，简称 REST）描述了一个架构样式的网络系统，
比如 web 应用程序。它首次出现在 2000 年 Roy Fielding 的博士论文中，他是 HTTP 规范的主要编写者之
一。在目前主流的三种 Web 服务交互方案中，REST 相比于 SOAP（Simple Object Access protocol，简单
对象访问协议）以及 XML-RPC 更加简单明了，无论是对 URL 的处理还是对 Payload 的编码，REST 都倾向于用更
加简单轻量的方法设计和实现。值得注意的是 REST 并没有一个明确的标准，而更像是一种设计的风格。
它本身并没有什么实用性，其核心价值在于如何设计出符合 REST 风格的网络接口。
### 10.2 restful 的优点
它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用。
### 10.3 restful  的特性：
```text
资源（ Resources）：
    网络上的一个实体，或者说是网络上的一个具体信息。
    它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。可以用一个 URI（统一
    资源定位符）指向它，每种资源对应一个特定的 URI 。要
    获取这个资源，访问它的 URI 就可以，因此 URI 即为每一个资源的独一无二的识别符。

表现层（ Representation）：
    把资源具体呈现出来的形式，叫做它的表现层 （ Representation）。
    比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现，甚至可以采用二
    进制格式。
    
状态转化（ State Transfer）：
    每 发出一个请求，就代表了客户端和服务器的一次交互过程。
    HTTP 协议，是一个无状态协议，即所有的状态都保存在服务器端。因此，如果客户端想要操作服务器，
    必须通过某种手段，让服务器端发生 “状态转化 ”（ State Tran sfer）。而这种转化是建立在表现层之上的，所以
    就是  “表现层状态转化 ”。具体说，就是  HTTP 协议里面，四个表示操作方式的动词： GET、 POST、 PUT、
    DELETE。它们分别对应四种基本操作： GET 用来获取资源， POST 用来新建资源， PUT 用来更新资源， DELETE 用来
    删除资源。
```
### 10.4 restful 的示例：
```text
/account/1 HTTP  GET ：  得到 id = 1 的 account
/account/1 HTTP  DELETE： 删除 id = 1 的 account
/account/1 HTTP  PUT：  更新 id = 1 的 account
/account HTTP  POST：  新增 account
```

### 10.5 基于 HiddentHttpMethodFilter 的示例
#### 10.5.1 作用
由于浏览器 form 表单只支持 GET 与 POST 请求，而 DELETE、PUT 等 method 并不支持，Spring3.0 添
加了一个过滤器，可以将浏览器请求改为指定的请求方式，发送给我们的控制器方法，使得支持 GET、POST、PUT
与 DELETE 请求。
#### 10.5.2 使用方法
- 1 web.xml 中配置该过滤器。
- 2 请求方式必须使用 post 请求。
- 3 按照要求提供_method 请求参数，该参数的取值就是我们需要的请求方式。

#### 10.6 具体示例
- web.xml
```xml
<!--开启 RESTFUL 支持-->
<filter>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <url-pattern>*.do</url-pattern>
</filter-mapping>
```
- jsp
```html
<h1>GET</h1><hr>

<h3>a 标签:<a href="${ctx}/user/user/1.do">restGet</a></h3><br><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/1.do" method="get">
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="restGet">
    </form>
</h3><br><hr>
<h1>POST</h1><hr>
<h3>form 普通表单:
    <form action="${ctx}/user/user.do" method="post">
        <input type="submit" value="restPost">
    </form>
</h3><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user.do" method="post">
        <input type="hidden" name="_method" value="POST">
        <input type="submit" value="restPost">
    </form>
</h3><hr>


<h1>PUT</h1><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/2.do" method="post">
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="restPut">
    </form>
</h3><hr>

<h1>DELTE</h1><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/3.do" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="restDelete">
    </form>
</h3><hr>

</body>
</html>
```
```java
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 模拟新增
     */
    @RequestMapping(value = "/user.do", method = RequestMethod.POST)
    public String restPost(){
        System.out.println("restPost:模拟新增");
        return "redirect:../restful.jsp";
    }
    /**
     * 模拟查询
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.GET)
    public String restGet(@PathVariable("id") int id){
        System.out.println("restGet:模拟查询");
        return "redirect:../../restful.jsp";
    }
    /**
     * 模拟修改
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.PUT)
    public String restPut(@PathVariable("id") int id){
        System.out.println("restPut:模拟修改");
        return "redirect:../../restful.jsp";
    }
    /**
     * 模拟删除
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.DELETE)
    public String restDelete(@PathVariable("id") int id){
        System.out.println("restDelete:模拟删除");
        return "redirect:../../restful.jsp";
    }
}
```






