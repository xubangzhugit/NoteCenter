package javaNote.file;

import javax.sound.sampled.Line;
import javax.swing.plaf.basic.BasicTextAreaUI;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileTest {

   final static Logger logger = Logger.getLogger(FileTest.class.getName());
    public static void main(String[] args){
        try{

            //testFileObject();
            //测试文件输入流
           // testfileInputStream();
            //测试文件输出流
           // testfileoutputStream();
           //读取ZIP包
            //testReadZIP();
            //写入zip包
            //testWriteZip();
            //测试字符流读取数据
            //testReader();
            //测试paths和files工具类（好用）
            testFilesAndPaths();

        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }

    private static void testFilesAndPaths() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("./Notes/javaNote/file/file.md"));
        /*strings.stream().forEach(i->{
            logger.info(i+"");
        });*/
        Path write = Files.write(Paths.get("./Notes/javaNote/file/file1.md"), strings);
        logger.info(write.toString()+"写入完成");
    }

    private static void testReader() throws  Exception{
            // 创建一个FileReader对象:
            Reader reader = new FileReader("./README.md"); // 字符编码是???
            for (;;) {
                int n = reader.read(); // 反复调用read()方法，直到返回-1
                if (n == -1) {
                    break;
                }
                System.out.print(String.valueOf((char) n)); // 打印char
            }
            reader.close(); // 关闭流
    }

    private static void testWriteZip() throws IOException{
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("D:\\ziptest.zip"))) {
            File[] files = new File("D:\\ziptest").listFiles();
            for (File file : files) {
                zip.putNextEntry(new ZipEntry(file.getName()));
                zip.write(getFileDataAsBytes(file));
                zip.closeEntry();
            }
        }
    }

    private static byte[] getFileDataAsBytes(File file) throws IOException{
            FileInputStream fs = new FileInputStream(file.getName());
            byte[] bt = new byte[1024];
            int n;
            StringBuilder sb = new StringBuilder();
            while((n = fs.read(bt))!=-1){
                sb.append(new String(bt));
            }
         return  new String(sb).getBytes();
    }


    private static void testReadZIP() throws IOException{
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream("./Notes/javaNote/file/zipRead.zip"))) {
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                String name = entry.getName();
                if (!entry.isDirectory()) {
                    byte[] bys = new byte[1024];
                    int n;
                    while ((n = zip.read(bys)) != -1) {
                        logger.info(new String(bys,StandardCharsets.UTF_8));
                    }
                }
            }
        }
    }

    private static void testfileoutputStream() throws IOException {
        FileOutputStream outf = new FileOutputStream("./Notes/javaNote/file/file.md");
        outf.write("xubangzhu".getBytes("utf-8"));

        outf.close();
    }

    private static void testfileInputStream() throws Exception{
        FileInputStream fs = new FileInputStream("D:\\NoteCenter\\Notes\\Notes\\javaNote\\java.md");
        fs.available();//返回文件的字节大小
        byte[] by = new byte[1024];//保存临时读取到的字节
        int n ;
        while ((n = fs.read(by))!=-1){
            //以String输出字节
            String s = new String(by, StandardCharsets.UTF_8);
           logger.info(s);
        }
        fs.close();
    }

    private static void testFileObject() throws  Exception{
        File file = new File("D:\\NoteCenter\\Notes");
        logger.info("getPath" +file.getPath());
        logger.info("getabsole"+file.getAbsolutePath());//绝对路径
        logger.info("getCanon"+file.getCanonicalPath());//规范路径
        logger.info("分隔符"+File.separator+""+file.length());
        //./表示当前目录
        File filedir = new File("./Notes/javaNote/file/dir");
        boolean mkdir = filedir.mkdir();
        logger.info(filedir.getAbsolutePath()+""+mkdir);

        /*file.createNewFile();//创建新文件
        File.createTempFile("","");//创建临时文件
        file.deleteOnExit();//jvm 退出时删除文件
        file.canRead();//是否可读
        file.canWrite();//是否可以写
        file.canExecute();//是否可执行
        file.isFile();//文件是否存在
        file.isDirectory();//是否是目录
        file.delete();//删除文件*/
        File[] files = file.listFiles();
        for (File file1 : files) {
            logger.info(file1.getName()+"filelists");
        }



    }
}
