
import java.util.ArrayList;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPooling
{

 public ThreadPooling(int poolSize)   //Threads are created
 {
 this.poolS = poolSize;
 q = new LinkedBlockingQueue<Task>();
 workers = new WorkerTask[this.poolS];
 for (int i = 0; i < poolSize; i++) {
	 workers[i] = new WorkerTask("Thread " + i);
	 workers[i].start();
	 }
 }

 public void shutdown()    		      //closing all threads
 {
	 this.isShutdown = true;
     for (int i = 0; i < poolS; i++) {
         workers[i] = null;
     }
 }
 
 public void add(Runnable t) 		  // tasks are added to thread
 {
	 synchronized (q) {
		 q.add((Task) t);
		 q.notify();
		 }
	 }


 private class WorkerTask extends Thread   //All tasks are executed
 {
	 
	 public WorkerTask(String s) {
		 super(s);
	 }
	 
	 public void run() {
	 Task t;
	 while (true) {
		 synchronized (q) {
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
			 } catch (RuntimeException e) {
				 System.out.println("Thread pool is stopped " + e.getMessage());
				 }
		 }
	 }
	 }
 
 private final int poolS;                //Thread pool size
 private final WorkerTask[] workers;     //Internal pool is an Array
 private boolean isShutdown = false;     
 private final LinkedBlockingQueue<Task> q;  //FIFO Ordering
 private final ArrayList<Task> Tasks = new ArrayList<Task>();
}