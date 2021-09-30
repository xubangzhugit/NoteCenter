##### GMT(Greenwish mean time)格林威治时间
- 日期:(具体指某一天)
- 时间:(  
        1.   不带日期的时间(12.30.59);
        2.    带日期的时间(2021-12-20 12:30:59),带日期的时间才能确定某一个时刻;
      )
#### OldDateTime
    - Epoch Time :时间戳
    - 标准库API
  ```$xslt
        我们再来看一下Java标准库提供的API。Java标准库有两套处理日期和时间的API：
            一套定义在java.util这个包里面，主要包括Date、Calendar和TimeZone这几个类；
            一套新的API是在Java 8引入的，定义在java.time这个包里面，主要包括LocalDateTime、ZonedDateTime、ZoneId等
  ```
  #### Date 
     Date对象有几个严重的问题：它不能转换时区,此外，也很难对Date 日期进行加减，计算日期差等操作
  #### calendar(相比于Date,多了对日期时间的简单计算)
     Calendar可以用于获取并设置年、月、日、时、分、秒，它和Date比，主要多了一个可以做简单的日期和时间运算的功能。
  #### TimeZone(时区的唯一标识，是以字符串标识的ID)
     和Calendar和Date相比，TimeZone提供了时区转换的功能。时区用TimeZone对象表示   
### NewDateTime
 ```$xslt
    从Java 8开始，java.time包提供了新的日期和时间API，主要涉及的类型有：
         本地日期和时间：LocalDateTime，LocalDate，LocalTime；
         带时区的日期和时间：ZonedDateTime；
         时刻：Instant；
         时区：ZoneId，ZoneOffset；
         时间间隔：Duration。
         以及一套新的用于取代SimpleDateFormat的格式化类型DateTimeFormatter
      新API修正了旧API不合理的常量设计：
         Month的范围用1~12表示1月到12月；
         Week的范围用1~7表示周一到周日
  ```    
  #### LocalDatetTime
  ```$xslt
        ISO 8601规定的日期和时间分隔符是T
        标准格式如下：
        日期：yyyy-MM-dd
        时间：HH:mm:ss
        带毫秒的时间：HH:mm:ss.SSS
        日期和时间：yyyy-MM-dd'T'HH:mm:ss
        带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
        缺点：
        无法与时间戳进行转换，因为localDateTime没有时区
   ```
  #### DateTimeFormatter
    ```$xslt
            DateTimeFormatter不但是不变对象，它还是线程安全的
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println(dtf.format(LocalDateTime.now()));
    ```   
  #### ZonedDateTime
      - 相当于localDateTime+ ZonedId时区
      -   
  #### Duration和Period
     - Duration表示两个时刻之间的时间间隔。另一个类似的Period表示两个日期之间的天数  
  #### instant(新版时间戳)
  ```$xslt
    以Instant类型表示，我们用Instant.now()获取当前时间戳，效果和System.currentTimeMillis()类似
    - instant加上zonedID 生成zonedDateTime
   ```   
     
     
     
     
     
     
     
     
     
     
     
     