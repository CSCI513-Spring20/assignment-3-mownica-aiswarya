import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GeneratingEvenNumbers extends Task {
	

	public static void main(String[] args) {
		ArrayList<GeneratingEvenNumbers> taskslist = new ArrayList<GeneratingEvenNumbers>();
		int lowerLimit = 0;
		Scanner scanner = new Scanner(System.in);
	    int maxsize = 20;
	    int Numofthreads = 10;
	    int SizeofthreadPool = 10;
	    ThreadPooling threadPooltask = new ThreadPooling(SizeofthreadPool);
	    int taskSizePerThread = (int) Math.ceil(maxsize / Numofthreads);
	    int upperLimit = lowerLimit + taskSizePerThread;
	    
	    while (upperLimit <= maxsize) {
	      GeneratingEvenNumbers task = new GeneratingEvenNumbers(lowerLimit, upperLimit - 1);
	      threadPooltask.execute(task);
	      taskslist.add(task);
	      lowerLimit = upperLimit;
	      upperLimit = lowerLimit + taskSizePerThread;
	    }
	
	    threadPooltask.waitForAllTasks();
	    threadPooltask.shutdown();
	    scanner.close();
	  }
	

	public GeneratingEvenNumbers(int start, int end) {
		super("GeneratingEvenNumbers(" + start + ":" + end+")");
		
	}

	@Override
	public void run() {
		System.out.println(" started running with " + Thread.currentThread().getName());
	}
	
}

