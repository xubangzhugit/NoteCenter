package javaNote.thread;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class MyThread {
   static final Logger logger = Logger.getLogger(MyThread.class.getName());
   public static void main(String[] args){

       logger.info("线程名"+Thread.currentThread().getName()+"BEGIN");
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               while (true) {
                   System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss").format(LocalDateTime.now()));
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       break;
                   }
               }
           }
       };
       Thread t = new Thread(runnable);
       t.start();
       logger.info("线程名"+Thread.currentThread().getName()+"END");

   }
}
