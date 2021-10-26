### springboot 
  - springboot 的自动装配原理：
     启动类上的SpringbootApplication 注解
                   包含springbootConfiguration和
                      enableAutoConfiguration注解.
                          enableAutoConfiguration注解包含一个@import注解
                              @import注解导入autoconfigurationImportSelector.class
                                  该类的SelectImports方法SpringFactoriesLoader.loadFactoryNames()
                                      扫描所有具有META-INF/spring.factories的jar包
                                      通过spring.factories文件将所有的Javaconfig自动配置类加载到spring容器中
                                  每个XXXautoconfiguretion自动配置类都是在某些条件下才会生效。
                                  常见的条件注解：
                                      @ConditionalOnBean：当容器里有指定的bean的条件下。
                                      @ConditionalOnMissingBean：当容器里不存在指定bean的条件下。
                                      @ConditionalOnClass：当类路径下有指定类的条件下。
                                      @ConditionalOnMissingClass：当类路径下不存在指定类的条件下。
                                      @ConditionalOnProperty：指定的属性是否有指定的值，比如@ConditionalOnProperties(prefix=”xxx.xxx”, value=”enable”, matchIfMissing=true)，代表当xxx.xxx为enable时条件的布尔值为true，如果没有设置的情况下也为true。
     @ConfigurationProperties，它的作用就是从配置文件中绑定属性到对应的bean上                                    
                                      
  - springboot 热加载
    - 只要classpath下的源码或配置文件修改，springboot会自动重启
     ```$xslt
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-devtools</artifactId>
       </dependency>
     ```                             
  - springboot瘦身打包
    ```$xslt
      <build>
              <finalName>awesome-app</finalName>
              <plugins>
                  <plugin>
                      <groupId>org.springframework.boot</groupId>
                      <artifactId>spring-boot-maven-plugin</artifactId>
                      在maven 插件处添加该pom,不会打包项目所依赖的jar包(除了第一次打包)
                      <dependencies>
                          <dependency>
                              <groupId>org.springframework.boot.experimental</groupId>
                              <artifactId>spring-boot-thin-layout</artifactId>
                              <version>1.0.27.RELEASE</version>
                          </dependency>
                      </dependencies>
                  </plugin>
              </plugins>
          </build>
    ``` 
    - 监控Actuator
     ```$aidl
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
     ```
     Actuator 会暴露它所收集的所有接口给JMX 供外部调用
    
    
                     