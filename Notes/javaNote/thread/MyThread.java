package javaNote.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

public class MyThread {
   static final Logger logger = Logger.getLogger(MyThread.class.getName());
   public static void main(String[] args){
       //测试runnable
       //testRunnable();
      // testThreadPool();


   }

    private static void testThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(4,6,2000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        Future<String> submit = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return null;
            }
        });
        try{
            String s = submit.get();//线程阻塞，直到线程完成
            submit.isDone();//判断任务是否完成
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }

    private static void testRunnable() {
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
class Reen {
    private final Lock lock  = new ReentrantLock();
    private int count;

    public Integer addCount(int num){
        if(lock.tryLock()){
            try{
                count+=num;
                return count;
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
        return null;
    }
}
class TaskQueue{
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String task){
        lock.lock();
        try{
            queue.add(task);
            condition.signalAll();//唤醒所有被wait线程
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          lock.unlock();
        }
    }
    public String gettask(){
        lock.lock();
        try{
            while (queue.isEmpty()){
                condition.wait();//队列中为空，释放当前线程锁
            }
            return queue.remove();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return null;
    }
}
class MyReadWriteLock{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readlock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private int[] array = new int[10];
    public void set(int a){
         writeLock.lock();
         try{
             array[a]+=1;
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             writeLock.unlock();
         }

    }
    public int[] get(){
        readlock.lock();
        try{
            return Arrays.copyOf(array,array.length);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readlock.unlock();
        }
        return new int[0];
    }

}
class MystampedLock{
    private final StampedLock stampedlock = new StampedLock();

    private double X;
    private double Y;
    public void writeData(double x,double y){
        //写入加锁(互斥)
        long l = stampedlock.writeLock();
        try{
            X+=x;
            Y+=y;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
             stampedlock.unlockWrite(l);
        }
    }
    public double readData(){
        long l = stampedlock.tryOptimisticRead();//获取乐观读锁
        double tempX = X;
        double tempY = Y;
        try{
            if(!stampedlock.validate(l)){ //验证从调用tryOptimisticRead开始到现在这段时间内有无写锁占用过锁资源，有写锁获得过锁资源则返回false
                long l1 = stampedlock.readLock();
                try{
                   tempX = X;
                   tempY = Y;
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    stampedlock.unlockRead(l1);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
        }
        return (tempX+tempY)*2;
    }

}