# mybaties note

## 1 概述
iBATIS 一词来源于"internet"和"abatis"的组合,是一个基于Java的持久层框架, iBATIS提供的持久层框架包括SQL Maps和Data Access Objects (DAOs)

## 2 快速入门
### 2.1 加入依赖
```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.5</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.45</version>
</dependency>
```
### 2.2 配置 myMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="mysql">
        <!--配置环境-->
        <environment id="mysql">
            <!--配置事务类型-->
            <transactionManager type="JDBC"></transactionManager>
            <!--以池数据源-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"></property>
                <property name="url" value="jdbc:mysql:///mybaties?useUnicode=true&amp;charaterEncoding=utf-8"></property>
                <property name="username" value="root"></property>
                <property name="password" value="root"></property>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper class="cn.introduction.dao.IUserDao"/>
    </mappers>
</configuration>
```
### 2.3 编写 Dao 接口
```java
public interface IUserDao {

    List<User> findAll();
}
```
### 2.4 编写对应 Dao 接口的映射文件 mapper.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
/*namespace: 命令空间指向对应的 Dao 接口*/
<mapper namespace="cn.introduction.dao.IUserDao">
    <select id="findAll" resultType="cn.introduction.domain.User">
        select * from user
    </select>
</mapper>
```
### 2.5 测试
```java
    private SqlSession sqlSession;
    private Reader reader;
    private IUserDao userDao;
    @Before
    public void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("myMapConfig-introduction.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(reader);
        SqlSession sqlSession = sessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    }
    @Test
    public void findAllTest(){
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
    /**
     * 测试后资源释放
     */
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        if(reader != null){
            reader.close();
        }
    }
```

## 3 自定义 mybaties (查询)
### 3.1 引入解析配置的文件工具, 执行 sql 工具类(略), 创建配置类对象 Configuration, Mapper
### 3.2 创建 SqlSessionFactoryBuilder sqlSession工厂构建者
```java
public SqlSessionFactory build(InputStream inputStream) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(cfg);
    }
```
### 3.3 创建 SqlSessionFactory sqlSessin 工厂接口类以及其实现类 DefaultSqlSessionFactory
- SqlSessionFactory
```java
/**
 * SqlSession 工厂类
 */
public interface SqlSessionFactory {
    /**
     * 打开一个 SqlSession
     * @return
     */
    SqlSession openSession() throws SQLException, ClassNotFoundException;
}
```
- DefaultSqlSessionFactory
```java
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() throws SQLException, ClassNotFoundException {
        return new DefaultSqlSession(cfg);
    }
}
```
### 3.4 创建 SqlSession 接口以及其实现类 DefaultSqlSession

- SqlSession
```java
public interface SqlSession {
    /**
     * 核心交互对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);
    /**
     * 关闭资源
     */
    void close();
}
```
- DefaultSqlSession
```java
public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) throws SQLException, ClassNotFoundException {
        this.cfg = cfg;
        this.conn = DataSourceUtil.getConncetion(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(), new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), conn));

    }

    @Override
    public void close() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```
### 3.5 创建 dao 接口的代理对象MapperProxy
```java
/**
 * dao 的代理对象
 */
public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法名
        String methodName = method.getName();
        // 限定全类名
        String className = method.getDeclaringClass().getName();
        String key = className+"."+methodName;
        //获取 mapper
        Mapper mapper = mappers.get(key);
        if(mapper == null){
            throw new IllegalArgumentException("传参错误!");
        }
        return new Executor().selectList(mapper, conn);
    }
}

```

### 3.6 测试(略)

## 4 mybaties 别名
### 4.1 官方默认注册别名
- 官方文档(19)
- TypeAliasRegistry 别名注册类
### 4.2 自定义别名
- typeAliases 标签
```xml
<typeAliases>
    <!-- 单个别名定义 -->
    <typeAlias alias="user" type="com.itheima.domain.User"/>
    <!-- 批量别名定义，扫描整个包下的类，别名为类名（首字母大写或小写都可以） -->
    <package name="com.itheima.domain"/>
    <package name=" 其它包 "/>
</typeAliases>
```
## 5 Mybatis 输出结果封装
### 5.1 resultType 属性可以指定结果集的类型，它支持基本类型和实体类类型
### 5.2 resultMap 标签可以建立查询的列名和实体类的属性名称不一致时建立对应关系。
- 实例:
```xml
<resultMap id="defaultUserWithAccountMap" type="cn.dynamicsql.domain.User">
        <result property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="birthday" column="birthday"/>
        <result property="sex" column="sex"/>
        <result property="address" column="address"/>
        <collection property="accountList" ofType="cn.dynamicsql.domain.Account">
            <result property="id" column="id"/>
            <result property="uid" column="uid"/>
            <result property="money" column="money"/>
        </collection>
    </resultMap>
```

## 6 dynamic sql 
### 6.1 `<if>`
- 用于属性判断
- 实例
```xml
<select id="findByCondition" parameterType="cn.dynamicsql.domain.User" resultType="cn.dynamicsql.domain.User">
    select * from user where 1=1
    <if test="id != null">
      and id = #{id}
    </if>
    <if test="username != null and username != ''">
        and username = #{username}
    </if>
    <if test="birthday != null and birthday != ''">
        and birthday = #{birthday}
    </if>
    <if test="sex != null and sex != ''">
        and sex = #{sex}
    </if>
    <if test="address != null and address != ''">
        and address = #{address}
    </if>
</select>
```
### 6.2 `<where>`
- 相当于 sql 中的 where 1 = 1 
- 实例
```xml
<select id="findById" resultType="cn.dynamicsql.domain.User" parameterType="INT">
  <include refid="defaultselect" />
    <where>

      <if test="_parameter != null">
          and id = #{id}
      </if>

    </where>
</select>
```
### 6.3 `<foreach>`
- 用于遍历集合
```text
<foreach>`属性：
    collection:代表要遍历的集合元素，注意编写时不要写#{}
    open:代表语句的开始部分
    close:代表结束部分
    传智播客——专注于 Java、.Net 和 Php、网页平面设计工程师的培训
    北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090
    item:代表遍历集合的每个元素，生成的变量名
    sperator:代表分隔符
```
- 实例
```xml
<select id="findBylis" resultType="cn.dynamicsql.domain.User" parameterType="java.util.List">
    <include refid="defaultselect"/>
    <where>
        <if test="array != null">
            <foreach collection="array" open="and id in (" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </if>
    </where>
</select>
```
### 6.4 `<include>` 与 `<sql>`
- 实例 
```xml
<!--定义可复用的 sql-->
<sql id="defaultselect">
    select * from user
</sql>
...
<select id="findByUid" resultMap="defMap" useCache="true">
   <!--引用可复用的 sql-->
   <include refid="defaultselect"/> where uid = #{uid}
</select>
```

### 6.5 其他动态标签
- `choose(when, otherwise)`
- `trim`
- `set`

### 7 懒加载
#### 7.1 开启
``xml
<settings>
    <!--开启懒加载-->
    <setting name="lazyLoadingEnabled" value="true"/>
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>``
#### 7.2 使用
- association
```xml
<resultMap id="defaultbaseMap" type="cn.dynamicsql.domain.Account">
    <result property="id" column="id"/>
    <result property="uid" column="uid"/>
    <result property="money" column="money"/>
    <association property="users" javaType="cn.dynamicsql.domain.User">
        <result property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="birthday" column="birthday"/>
        <result property="sex" column="sex"/>
        <result property="address" column="address"/>
    </association>
</resultMap>
```
- collection
```xml
<resultMap id="defaultUserWithAccountMap" type="cn.dynamicsql.domain.User">
    <result property="id" column="id"/>
    <result property="username" column="username"/>
    <result property="birthday" column="birthday"/>
    <result property="sex" column="sex"/>
    <result property="address" column="address"/>
    <collection property="accountList" ofType="cn.dynamicsql.domain.Account">
        <result property="id" column="id"/>
        <result property="uid" column="uid"/>
        <result property="money" column="money"/>
    </collection>
</resultMap>
```
## 8 缓存
### 8.1 一级缓存(默认开启)
- 一级缓存是 SqlSession 级别的缓存，只要 SqlSession 没有 flush 或 close，它就存在。
### 8.2 二级缓存(默认关闭)
####８.２.１　开启二级缓存
```xml
<!--开启两级缓存-->
<setting name="cacheEnabled" value="true"/>
```
#### 8.2.2 xml 配置二级缓存
##### 8.2.2.1 
```xml
<mapper namespace="cn.dynamicsql.dao.IAccountDao">
    <!--该xml开启二级缓存支持-->
    <cache></cache>
    ...
    <!--userCache: true 具体开启二级缓存-->
    <select id="findByUid" resultMap="defMap" useCache="true">
        ...
    </select>
    ...
</mapper>
```
#### 8.2.3 class 注解二级缓存
##### 8.2.2.2
```java
/*基于注解开启二级缓存*/
@CacheNamespace(blocking = true)
public interface IAccountDao {
    ...
}
```

## 9 注解开发
### 9.1 @Select
```java
@Select("select * from user")
@Results(id = "baseAndWithAccountMap", value = {
        @Result(id = true, column = "id", property = "id"),
        @Result(column = "username", property = "username"),
        @Result(column = "birthday", property = "birthday"),
        @Result(column = "sex", property = "sex"),
        @Result(column = "address", property = "address"),
        @Result(column = "id", property = "accountList", javaType = List.class, many = @Many(select = "cn.annotaion.dao.IAccountDao.findByUid")),
})
List<User> findAllAndWithAccount();
```
### 9.2 @Insert
```java
@SelectKey(keyProperty = "id", keyColumn = "id", before = false, resultType = Integer.class, statement = {"select last_insert_id()"})
@Insert("insert into user (username, birthday, sex, address) values (#{username}, #{birthday}, #{sex}, #{address})")
int save(User user);
```
### 9.3 @Update
```java
@Update("update user set username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address}")
void update(User user);
```
### 9.4 @Delete
```java
@Delete("delete from user where id = #{id}")
void delete(int id);
```
### 9.5 说明
- @Insert:实现新增
- @Update:实现更新
- @Delete:实现删除
- @Select:实现查询
- @Result:实现结果集封装
- @Results:可以与@Result 一起使用，封装多个结果集
- @ResultMap:实现引用@Results 定义的封装
- @One:实现一对一结果集封装
- @Many:实现一对多结果集封装
- @XxxxtProvider: 实现动态 SQL 映射[参考1](https://www.cnblogs.com/he-px/p/7134524.html)[参考SQL公用方法构造方法](https://my.oschina.net/ysma1987/blog/1534909)
- @CacheNamespace:实现注解二级缓存的使用

### 9.6 注解方法结果集类型数据的处理方式
- [参考](https://www.cnblogs.com/zhyonk/p/6692539.html)

## 10 异常
### 报错:There is no getter for property named 'id' in 'class java.lang.Integer'
- 接口:
```java
User findById(Integer id);
```
- 解决:使用 `_parameter` 替代参数
```xml
<if test="_parameter != null">
      and id = #{id}
  </if>
```

### 传参 List 和 Array 小结
- 问题: xml 文件识别不除 list 和 array 的其实定义名
- 接口
```java
List<User> findBylis(List<Integer> lis);
```
- 解决
```java
<select id="findBylis" resultType="cn.dynamicsql.domain.User" parameterType="java.util.List">
    <include refid="defaultselect"/>
    <where>
        <if test="list != null">
            <foreach collection="list" open="and id in (" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </if>
    </where>
</select>
```

### 传参多个时要使用 @Param 进行命名区分
- 接口
User login(@Param("username") String username, @Param("pwd") String pwd)
- 解决
```xml
<select id="findById" resultType="cn.dynamicsql.domain.User">
  <include refid="defaultselect" />
    <where>
      <if test="username != null">
          and username = #{username}, 
      </if>
      <if test="pwd != null">
                and pwd = #{pwd}, 
            </if>
    </where>
</select>
```