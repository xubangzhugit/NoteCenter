package javaNote.junitAndRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JunitRegexTest {


    public static void main(String[] args){
        //测试精确匹配
        testExactRegex();
        //分组匹配
        testGroupRegex();
        //测试非贪婪匹配
        testlayzRegex();
    }
    static Pattern pattern = Pattern.compile("(\\d+?)(0*)");
    private static void testlayzRegex() {

        Matcher matcher = pattern.matcher("1230000");
        if (matcher.matches()) {
            System.out.println("group1=" + matcher.group(1)); // "123"
            System.out.println("group2=" + matcher.group(2)); // "0000"
        }
    }

    static Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
    private static void testGroupRegex() {
        Matcher m = p.matcher("010-12345678");
        if (m.matches()) {//判断是否匹配成功
            String g1 = m.group(1);
            String g2 = m.group(2);
            System.out.println(g1);
            System.out.println(g2);
            System.out.println(m.group(0));
        } else {
            System.out.println("匹配失败!");
        }
    }

    private static void testExactRegex() {
        String re1 = "abc";
        System.out.println("abc".matches(re1));
        System.out.println("Abc".matches(re1));
        System.out.println("abcd".matches(re1));

        String re2 = "a\\&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));
    }
}
