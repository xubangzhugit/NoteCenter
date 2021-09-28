#  JUnit
 - 什么是单元测试呢？单元测试就是针对最小的功能单元编写测试代码。Java程序最小的功能单元是方法
     ```$xslt
    核心测试方法加上了@Test注解:标注测试类
                   @assertEquals(1, Factorial.fact(1))表示，期望Factorial.fact(1)返回1
                   assertTrue(): 期待结果为true
                   assertFalse(): 期待结果为false
                   assertNotNull(): 期待结果为非null
                   assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
    标记为@BeforeEach和@AfterEach的方法，它们会在运行每个@Test方法前后自动运行
         @BeforeAll和@AfterAll在所有@Test方法运行前后仅运行一次，因此，它们只能初始化静态变量，
         @BeforeAll和@AfterAll也只能标注在静态方法上
                   @Disabled : 不运行该test 方法
                   @EnabledOnOs(OS.WINDOWS) 在windows 上执行
                   不在Windows平台执行的测试，可以加上@DisabledOnOs(OS.WINDOWS)：
                   只能在Java 9或更高版本执行的测试，可以加上@DisabledOnJre(JRE.JAVA_8)
                   只能在64位操作系统上执行的测试@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
                   需要传入环境变量DEBUG=true才能执行的测试，可以用@EnabledIfEnvironmentVariable
                   
    ```
    - 异常测试
      ```$xslt
        @Test
        void testNegative() {
            assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    执行目标方法，抛出异常，，且被IllegalArgumentException捕获，测试成功
                }
            });
        }
        ```
    - 参数化测试
     ```$xslt
         ----------方式一-----------
        JUnit提供了一个@ParameterizedTest注解，用来进行参数化测试
        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 5, 100 })//提供入参
        void testAbs(int x) {
            assertEquals(x, Math.abs(x));
        }
        ---------------方式二---------------
        @ParameterizedTest
        @MethodSource
        void testCapitalize(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }
        //提供入参，方法同名
        static List<Arguments> testCapitalize() {
            return List.of( // arguments:
                    Arguments.arguments("abc", "Abc"), //
                    Arguments.arguments("APPLE", "Apple"), //
                    Arguments.arguments("gooD", "Good"));
        }
        ---------------方式三--------------
        @ParameterizedTest
        @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
        void testCapitalize(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }
        ----------------方式四-----------------
        测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource
        @ParameterizedTest
        @CsvFileSource(resources = { "/test-capitalize.csv" })
        void testCapitalizeUsingCsvFile(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }
     ```
    