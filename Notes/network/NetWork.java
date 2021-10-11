package network;

import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class NetWork {
    public static void main(String[] args){
        MyServerSocket.testSeerverSocker();

    }

}
//TCP
class MyServerSocket{
    private static final Logger logger = Logger.getLogger(MyServerSocket.class.getName());
    private static final ThreadPoolExecutor threadpool = new ThreadPoolExecutor(4,6,1000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    public static void testSeerverSocker(){
        try{
            ServerSocket sso = new ServerSocket(10000);
            logger.info("service run .....");
            while (true){
                Socket accept = sso.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (OutputStream opt = accept.getOutputStream();
                             InputStream ipt = accept.getInputStream()) {
                            handle(opt, ipt);
                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                accept.close(); //关闭socket
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            } finally {

                            }
                        }
                    }
                }).start();
//                Future<?> submit = threadpool.submit();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

    }
    public static void handle(OutputStream opt,InputStream ipt){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ipt, StandardCharsets.UTF_8));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(opt, StandardCharsets.UTF_8));
        try{
            bufferedWriter.write("hello\n");
            bufferedWriter.flush();
            while(true){
                String s = bufferedReader.readLine();
                if("bye".equals(s)){
                    bufferedWriter.write("bye\n");
                    bufferedWriter.flush();
                }
                bufferedWriter.write("message"+s+"]\n");
                bufferedWriter.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

    }


}
class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost", 10000); // 连接指定服务器和端口
        try (InputStream input = sock.getInputStream()) {
            try (OutputStream output = sock.getOutputStream()) {
                handle(input, output);
            }
        }
        sock.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (; ; ) {
            System.out.print(">>> \n"); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
    }
}
//UDP
class MyDataGramSocketServer{
     public static void main(String[] args){
         //UDP服务端收发数据
         testUDPServer();
     }

    private static void testUDPServer() {
        try{
            DatagramSocket ds = new DatagramSocket(10000);
           while (true) {
               byte[] by = new byte[1024];
               DatagramPacket dp = new DatagramPacket(by,by.length);
               ds.receive(dp);//服务端阻塞等待接收数据
               String str = new String(dp.getData(),dp.getOffset(),dp.getLength(),StandardCharsets.UTF_8);
               System.out.println("服务器接收到的数据--->>>>>"+str);
               //服务器端准备相应数据
               byte[] bytes = "ACK".getBytes(StandardCharsets.UTF_8);
               dp.setData(bytes);
               ds.send(dp);
               System.out.println("发送数据完成---->>>");
           }
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

    }
}
class MyDataGramSockeClient{
     public static void main(String[] args){
         //UDP 客户端收发数据
         testUDPClient();

     }
    private static void testUDPClient() {
       try{

           //发送数据
          while (true){
              Thread.sleep(500);
              //客户端连接服务器
              DatagramSocket localhost = new DatagramSocket();
              localhost.setSoTimeout(1000);
              localhost.connect(InetAddress.getByName("localhost"),10000);
              byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);
              DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
              localhost.send(dp);
              //接收数据
              byte[] by = new byte[1024];
              dp = new DatagramPacket(by,by.length);
              localhost.receive(dp);
              LocalTime localTime = LocalDateTime.now().toLocalTime();
              DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
              String time = dateTimeFormatter.format(localTime);//格式化时间显示
              System.out.println(time+new String(dp.getData(),dp.getOffset(),dp.getLength()));
              //关闭连接
              localhost.disconnect();

          }

       }catch(Exception e){
           e.printStackTrace();
       }finally{

       }
    }
}











