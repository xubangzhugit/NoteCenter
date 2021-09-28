package javaNote.timeAndDate;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.logging.Logger;

public class LocalDateTest {
    static final Logger logger = Logger.getLogger(LocalDateTest.class.getName());
    public static void main(String[] args){
        //旧的日期时间格式
        //测试Date 基本时间
        //testDate();
        //测试Calendar 修改时间
        //testCalendar();
        //测试TimeZone 时区转换
        //testTimeZone();
        //新的日期时间格式
        //测试localDateTime
        //testLocalDateTime();
        //测试DateTimeFormatter
        //testDateTimeFormatter();
        //测试duration
       // testDuration();
        //测试zonedDateTime
          //testzonedDateTime();
        //测试instant(时间戳)
        //testInstant();
        //旧api 新api 转换
        //testNewOldChange();



    }

    private static void testNewOldChange() {
        // Date -> Instant:旧转新
        Instant ins1 = new Date().toInstant();

        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendaro = Calendar.getInstance();
        Instant ins2 = calendaro.toInstant();
        ZonedDateTime zdt = ins2.atZone(calendaro.getTimeZone().toZoneId());
        // ZonedDateTime -> long: 新转旧
        ZonedDateTime zdto = ZonedDateTime.now();
        long ts = zdto.toEpochSecond() * 1000;

        // long -> Date:
        Date date = new Date(ts);

        // long -> Calendar:
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);
    }

    private static void testInstant() {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒

        // 以指定时间戳创建Instant:
        Instant ins = Instant.ofEpochSecond(1568568760);
        //时间戳加上zoned ，生成zoneDateTime
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
    }

    private static void testzonedDateTime() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zbj);
        System.out.println(zny);
        //通过localDateTime添加一个zonedID,返回一个ZonedDateTime
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbja = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime znya = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zbja);
        System.out.println(znya);
        //时区转换
        // 以中国时区获取当前时间:
        ZonedDateTime zbjc = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime znyc = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbjc);
        System.out.println(znyc);
    }

    private static void testDateTimeFormatter() {
        // 自定义格式化:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        // 用自定义格式解析:
        LocalDateTime dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);
        //----------------
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 加5天减3小时:
        LocalDateTime dt2p = dt.plusDays(5).minusHours(3);
        System.out.println(dt2p); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt2.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59
        //-------------
        LocalDateTime wdt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 日期变为31日:
        LocalDateTime wdt2 = dt.withDayOfMonth(31);
        System.out.println(dt2); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime wdt3 = dt2.withMonth(9);
        System.out.println(dt3); // 2019-09-30T20:30:59
        //---------------
        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第1个周一:
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);
        //要判断两个LocalDateTime的先后，可以使用isBefore()、isAfter()方法，对于LocalDate和LocalTime类似
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
    }

    private static void testLocalDateTime() {
        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime now2 = LocalDateTime.now();
        logger.info("localDate"+now+"localTime"+now1+"localDateTime"+now2);
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        LocalDate d = dt.toLocalDate(); // 转换到当前日期
        LocalTime t = dt.toLocalTime(); // 转换到当前时间
        // 指定日期和时间:
        LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);//通过localDate和localtime创建localDateTime
        //通过字符串创建local日期类
        LocalDateTime dtp = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate dp = LocalDate.parse("2019-11-19");
        LocalTime tp = LocalTime.parse("15:16:17");

    }

    private static void testDuration(){
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d = Duration.between(start, end);
        System.out.println(d); // PT1235H10M30S

        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D
    }
    private static void testTimeZone() {
        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        String[] availableIDs = TimeZone.getAvailableIDs(); //获取系统支持的所有时区
        Arrays.stream(availableIDs).forEach(i->{
            if(i.equals("Asia/Shanghai")){
                System.out.println(i+"时区");

            }
        });
        System.out.println(tzDefault.getID()); // Asia/Shanghai
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York
    }

    private static void testCalendar() {
        Calendar ca = Calendar.getInstance();
        int y = ca.get(Calendar.YEAR);//年
        int m = ca.get(Calendar.MONTH);//月(0-11)
        int d = ca.get(Calendar.DAY_OF_MONTH);//日
        int w = ca.get(Calendar.DAY_OF_WEEK);//周 （日...六）(1...7)
        int h = ca.get(Calendar.HOUR_OF_DAY);//时
        int mt = ca.get(Calendar.MINUTE);//分
        int s = ca.get(Calendar.SECOND);//秒
        int ms = ca.get(Calendar.MILLISECOND);//毫秒

        System.out.println(y+"年"+m+"月"+d+"日"+w+"周"+h+"时"+mt+"分"+s+"秒"+ms);
        System.out.println(ca.getTime());//利用Calendar.getTime()可以将一个Calendar对象转换成Date对象
        //-----------
        // 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置2019年:
        c.set(Calendar.YEAR, 2019);
        // 设置9月:注意8表示9月:
        c.set(Calendar.MONTH, 8);
        // 设置2日:
        c.set(Calendar.DATE, 2);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 23);
        //设置时区
        c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date time = c.getTime();//将calendar对象转Date对象
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
    }

    private static void testDate() {
        int ti  = 123000;
        logger.info(ti+"");
        logger.info(Integer.toHexString(ti));//打印16进制内容
        logger.info(NumberFormat.getCurrencyInstance(Locale.US).format(ti));//以美元方式显示价格
        int time = 1574208900;
        //TODO: displayDatetime
        // 获取当前时间:
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());//默认Date格式
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
        //时间格式化
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

    }
}
