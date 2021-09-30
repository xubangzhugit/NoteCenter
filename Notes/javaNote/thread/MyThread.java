package javaNote.thread;

import java.util.logging.Logger;

public class MyThread {
   static final Logger logger = Logger.getLogger(MyThread.class.getName());
   public static void main(String[] args){

       logger.info("线程名"+Thread.currentThread().getName()+"BEGIN");
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               logger.info("runnable执行"+Thread.currentThread().getName()+">>>");
           }
       };
       Thread t = new Thread(runnable);
       t.start();
       logger.info("线程名"+Thread.currentThread().getName()+"END");

   }
}
