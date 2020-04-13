
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPooling
{
 //Thread pool size
 private final int poolS;

 //Declare array
 private final InternalTask[] intTasks;

 // FIFO ordering
 private boolean isShutdown = false;

 private final LinkedBlockingQueue<Task> q;

 //Constructor of the class
 public ThreadPooling(int poolSize){
 this.poolS = poolSize;

 q = new LinkedBlockingQueue<Task>();
 intTasks = new InternalTask[this.poolS];
 for (int i = 0; i < poolSize; i++) {
 intTasks[i] = new InternalTask("Thread " + i);
 intTasks[i].start();
 }
 }

 public void execute(Task t) {
 synchronized (q) {
 q.add(t);
 q.notify();
 }
 }

 public void waitForAllTasks() {
 boolean Task1 = true;
 while (Task1) {
 Task1 = false;
 
 try {
 Thread.sleep(100);
 } catch (InterruptedException e) {
 System.out.println("Interruption occured: " + e.getMessage());
 }
 }
 }

 public void shutdown() {
 this.isShutdown = true;
 }

 private class InternalTask extends Thread {
 public InternalTask(String s) {
   super(s);
 }
 @Override
 public void run() {
 Task t;
 while (true) {
 synchronized (q) {
 if (isShutdown && q.isEmpty()) {
 break;
 }
 while (q.isEmpty()) {
 try {
 q.wait();
 } catch (InterruptedException e) {
 System.out.println(" occurred " + e.getMessage());
 }
 }
 t = (Task) q.poll();
 }  
 try {
 t.run();
 //t.setIsFinished();
 } catch (RuntimeException e) {
 System.out.println("Thread pool is stopped " + e.getMessage());
 }
 }
 }
  }

}