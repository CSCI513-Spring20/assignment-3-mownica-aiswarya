
import java.util.ArrayList;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPooling
{

 public ThreadPooling(int poolSize){
 this.poolS = poolSize;
 q = new LinkedBlockingQueue<Task>();
 workers = new InternalTask[this.poolS];
 for (int i = 0; i < poolSize; i++) {
	 workers[i] = new InternalTask("Thread " + i);
	 workers[i].start();
	 }
 }

 public void shutdown() {
	 this.isShutdown = true;
     for (int i = 0; i < poolS; i++) {
         workers[i] = null;
     }
 }
 
 public void execute(Runnable t) {
	 synchronized (q) {
		 q.add((Task) t);
		 q.notify();
		 }
	 }


 private class InternalTask extends Thread {
	 
	 public InternalTask(String s) {
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
 
 private final int poolS;
 private final InternalTask[] workers;
 private boolean isShutdown = false;
 private final LinkedBlockingQueue<Task> q;
 private final ArrayList<Task> Tasks = new ArrayList<Task>();
}