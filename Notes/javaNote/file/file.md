# IO
 -  在计算机系统中，文件是非常重要的存储方式
 ### inputStream 
  ```$xslt
      InputStream就是Java标准库提供的最基本的输入流,inputStream 是所有输入流的超类
      这个抽象类定义的一个最重要的方法就是int read()
      public abstract int read() throws IOException;
   ```
   - FileinputStream :从文件流中读取数据
     使用IO操作时，需要使用try{}..finally{}保证发生异常时，正确的关闭资源
     read()方法是阻塞（Blocking）方法
 ###  OutputStream
   - Java标准库提供的最基本的输出流 : 是所有输出流的超类
   ```$xslt
    这个抽象类定义的一个最重要的方法就是void write(int b)
    public abstract void write(int b) throws IOException;
   ```
   - 和InputStream一样，OutputStream的write()方法也是阻塞的。
 ### Decorator模式
   - 子类继承父类，并持有父类对象的引用。且重写父类对象的方法。。
 ### ZipInputStream
   - ZipInputStream是一种FilterInputStream
   
 ### 序列化
   - 序列化是指把一个java对象变成二进制内容
   ```$xslt
    为什么要把Java对象序列化呢？因为序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。
    
    有序列化，就有反序列化，即把一个二进制内容（也就是byte[]数组）变回Java对象。有了反序列化，保存到文件中的byte[]数组又可以“变回”Java对象，或者从网络上读取byte[]并把它“变回”Java对象。
        
   ```  
  - 一个对象要实现序列化，必须要实现java.io.Serializable序列化接口
  ```$xslt
   序列化
   把一个Java对象变为byte[]数组，需要使用ObjectOutputStreams。它负责把一个Java对象写入一个字节流
   反序列化
   和ObjectOutputStream相反，ObjectInputStream负责从一个字节流读取Java对象：
   缺点：
   Java本身提供的基于对象的序列化和反序列化机制既存在安全性问题，也存在兼容性问题
   更好的序列化方法是通过JSON这样的通用数据结构来实现，只输出基本类型（包括String）的内容，而不存储任何与代码相关的信息。
   ```
 ### reader
   - Reader是Java的IO库提供的另一个输入流接口。和InputStream的区别是，InputStream是一个字节流，即以byte为单位读取，而Reader是一个字符流，即以char为单位读取
   - java.io.Reader是所有字符输入流的超类， 
   FileReader:文件以字符的形式输入
   FileWriter:文件以字符的形式输出
   CharArrayReader和StringReader:特殊的字符输入流
   ```$xslt
    除了特殊的CharArrayReader和StringReader，普通的Reader实际上是基于InputStream构造的，因为Reader需要从InputStream中读入字节流（byte），然后，根据编码设置，再转换为char就可以实现字符流。如果我们查看FileReader的源码，它在内部实际上持有一个FileInputStream。
     既然Reader本质上是一个基于InputStream的byte到char的转换器，那么，如果我们已经有一个InputStream，想把它转换为Reader，是完全可行的。InputStreamReader就是这样一个转换器，它可以把任何InputStream转换为Readers
   ```
  ### writer
  
   
   
   
   
   
   
   
   
   
   
   
   
   