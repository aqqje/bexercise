# mybaties note

## 异常
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