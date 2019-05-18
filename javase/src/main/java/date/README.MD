# 1 Date类

## 1.1 概述

java.util.Date类 表示特定的瞬间，精确到毫秒。

```text
继续查阅Date类的描述，发现Date拥有多个构造函数，只是部分已经过时，但是其中有未过时的构造函数可以把毫秒值转成日期对象。

- public Date()：分配Date对象并初始化此对象，以表示分配它的时间（精确到毫秒）。
- public Date(long date)：分配Date对象并初始化此对象，以表示自从标准基准时间（称为“历元（epoch）”，即1970年1月1日00:00:00 GMT）以来的指定毫秒数。

tips: 由于我们处于东八区，所以我们的基准时间为1970年1月1日8时0分0秒。
```
简单来说：使用无参构造，可以自动设置当前系统时间的毫秒时刻；指定long类型的构造参数，可以自定义毫秒时刻。例如：
```text
import java.util.Date;
public class Test1 {
    public static void main(String[] args) {
        // 创建日期对象，把当前的时间
        System.out.println(new Date());
        // 创建日期对象，把当前的毫秒值转成日期对象
        System.out.println(new Date(0L));
    }
}
```

tips:在使用println方法时，会自动调用Date类中的toString方法。Date类对Object类中的toString方法进行了覆盖重写，所以结果为指定格式的字符串。

## 1.2 常用方法

Date类中的多数方法已经过时，常用的方法有：

- public long getTime() 把日期对象转换成对应的时间毫秒值。

# 2 DateFormat类

java.text.DateFormat 是日期/时间格式化子类的抽象类，我们通过这个类可以帮我们完成日期和文本之间的转换,也就是可以在Date对象与String对象之间进行来回转换。

- 格式化：按照指定的格式，从Date对象转换为String对象。
- 解析：按照指定的格式，从String对象转换为Date对象。

构造方法

由于DateFormat为抽象类，不能直接使用，所以需要常用的子类java.text.SimpleDateFormat。这个类需要一个模式（格式）来指定格式化或解析的标准。构造方法为：

- public SimpleDateFormat(String pattern)：用给定的模式和默认语言环境的日期格式符号构造SimpleDateFormat。

参数pattern是一个字符串，代表日期时间的自定义格式。

格式规则

常用的格式规则为：

|标识字母（区分大小写）| 含义 |
| --- | ---- |
| y | 年    |
| M | 月    |
| d | 日    |
| H | 时    |
| m | 分    |
| s | 秒    |

备注：更详细的格式规则，可以参考SimpleDateFormat类的API文档0。

常用方法

DateFormat类的常用方法有：

- public String format(Date date)：将Date对象格式化为字符串。
- public Date parse(String source)：将字符串解析为Date对象。

format方法

使用format方法的代码为：
```java
/** 时间转字符串
     *
     * @param formart 指定格式
     * @param date 需要转化时间
     * @return
     */
    public static String date2Str(String formart, Date date){
        if((formart == null && formart == "") && date == null) return null;
        sdf = new SimpleDateFormat(formart);
        return sdf.format(date);
    }
```
parse方法

使用parse方法的代码为：
```java
/** 字符串转时间
     *
     * @param formart 指定格式
     * @param dateStr 时间字符串
     * @return
     * @throws ParseException
     */
    public static Date str2Date(String formart, String dateStr) throws ParseException {
        if((formart == null && formart == "") && dateStr == null) return null;
        sdf = new SimpleDateFormat(formart);
        return sdf.parse(dateStr);
    }
```
## 3 Calendar类

概念

日历我们都见过

### 3.1 获取方式

Calendar为抽象类，由于语言敏感性，Calendar类在创建对象时并非直接创建，而是通过静态方法创建，返回子类对象，如下：

Calendar静态方法

- public static Calendar getInstance()：使用默认时区和语言环境获得一个日历

例如：

    import java.util.Calendar;
    
    public class Demo06CalendarInit {
        public static void main(String[] args) {
            Calendar cal = Calendar.getInstance();
        }    
    }

### 3.2 常用方法

根据Calendar类的API文档，常用方法有：

- public int get(int field)：返回给定日历字段的值。
- public void set(int field, int value)：将给定的日历字段设置为给定值。
- public abstract void add(int field, int amount)：根据日历的规则，为给定的日历字段添加或减去指定的时间量。
- public Date getTime()：返回一个表示此Calendar时间值（从历元到现在的毫秒偏移量）的Date对象。

Calendar类中提供很多成员常量，代表给定的日历字段：



|字段值 |	含义  |  
| --- | ---- |              
|YEAR        	|年                      | 
|MONTH       	|月（从0开始，可以+1使用）   |     
|DAY_OF_MONTH	|月中的天（几号）          | 
|HOUR        	|时（12小时制）        |
|HOUR_OF_DAY 	|时（24小时制）        |    
|MINUTE      	|分                  | 
|SECOND      	|秒                  | 
|DAY_OF_WEEK 	|周中的天（周几，周日为1，可以-1使用）|

### 3.3 get/set方法

get方法用来获取指定字段的值，set方法用来设置指定字段的值，代码使用演示：

```java
public class Test2 {
    public static void main(String[] args) {
        // 获取一个日历对象
        Calendar calendar = Calendar.getInstance();
        // 年
        int year = calendar.get(Calendar.YEAR);
        // 月
        int month = calendar.get(Calendar.MONTH)+1;
        // 日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year+"-"+month+"-"+day);


        // 设置指定数据
        calendar.set(Calendar.YEAR, 2020);
        year = calendar.get(Calendar.YEAR);
        System.out.println(year+"-"+month+"-"+day);
    }
}
```

### 3.4 add方法

add方法可以对指定日历字段的值进行加减操作，如果第二个参数为正数则加上偏移量，如果为负数则减去偏移量。代码如：

```java
     // 当前年 -3 年
    calendar.add(Calendar.YEAR, -3);
    //当前月份 +2 月
    calendar.add(Calendar.MONTH, 2);
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
    System.out.println(year+"-"+month+"-"+day);
```

### 3.5 getTime方法

Calendar中的getTime方法并不是获取毫秒时刻，而是拿到对应的Date对象。

```java
    // 获取当前 calendar 对象的 Date 对象
    Date date = calendar.getTime();
    System.out.println(date);
```

### 3.6 注意

- 西方星期的开始为周日，中国为周一。
- 在Calendar类中，月份的表示是以0-11代表1-12月。
- 日期是有大小关系的，时间靠后，时间越大。
