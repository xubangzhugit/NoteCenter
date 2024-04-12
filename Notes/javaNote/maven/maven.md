### 发布Artifact
  - 准备需要发布的项目
  - 在pom中添加发布的软件包的位置
  ```$xslt
            <distributionManagement>
                <repository>
                    <id>local-repo-release</id>
                    <name>GitHub Release</name>
                    <url>file://${project.basedir}/maven-repo</url>
                </repository>
            </distributionManagement>
        
            <build>
                <plugins>
                    <plugin>
                        <!-- 用来创建源码-->
                        <artifactId>maven-source-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>attach-sources</id>
                                <phase>package</phase>
                                <goals>
                                    <goal>jar-no-fork</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <plugin>
                         <!-- 用来创建javaDoc-->
                        <artifactId>maven-javadoc-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>attach-javadocs</id>
                                <phase>package</phase>
                                <goals>
                                    <goal>jar</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
  ```
  - 在项目根目录下运行Maven命令mvn clean package deploy
  - 这个工程推到GitHub上，并选择Settings-GitHub Pages，选择master branch启用Pages服务
  - 访问对应jar包的地址如下
```$xslt
    注意：不要遗漏 maven-repo
    https://xubangzhugit.github.io/testMavenRepo/maven-repo/zhu/bang/xu/artifact/1.0-SNAPSHOT/artifact-1.0-20211009.052049-1.jar
```
   - 可以在其他项目通过GAV依赖和repository访问该jar
```$xslt
            //项目坐标
            <dependencys>
                <dependency>
                           <groupId>zhu.bang.xu</groupId>
                           <artifactId>artifact</artifactId>
                           <version>1.0-SNAPSHOT</version>
                 </dependency>
            </dependencys>
            //仓库坐标
            <repositories>
                <repository>
                    <id>github-rich-repo</id>
                    <name>The Maven Repository on Github</name>
                    <url>https://xubangzhugit.github.io/testMavenRepo/maven-repo/</url>
                </repository>
            </repositories>
在<repository>中，我们必须声明发布的Maven的repo地址，其中<id>和<name>可以任意填写，<url>填入
GitHub Pages提供的地址+/maven-repo/后缀。现在，即可正常引用这个库
```
```agsl
//maven 打包指定模块
mvn clean package -pl parent
//打包某个具体的子模块
mvn clean package -pl parent/child1
//打包多个模块
mvn clean package -pl parent/child1,parent/child2
//同时打包指定模块的依赖模块(当前模块依赖的其他模块都将被打包)
mvn clean package -pl parent/child1 -am
//同时打包依赖于指定模块的模块(依赖于当前模块的都将被打包)
mvn clean package -pl parent/child1 -amd



```