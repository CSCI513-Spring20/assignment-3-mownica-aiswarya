import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GeneratingEvenNumbers extends Task {
	private int sindex;
	private int eindex;
	private ArrayList<Integer> evenNumbers = new ArrayList<Integer>();;

	public static void main(String[] args) {
		ArrayList<GeneratingEvenNumbers> taskslist = new ArrayList<GeneratingEvenNumbers>();
		int min = 0;                                      //initialized the min and max limits
		int max = 0;
		 
		Scanner scanner = new Scanner(System.in);
		                                                 //Took 10 threads to complete the task
		   ThreadPooling threadtask = new ThreadPooling(10);
		   int taskThread = 2;
		 
		                                                // Asks user for the maximum number of even numbers to be generated
		   System.out.print("Enter maximum size to generate even numbers: ");
		   int maxsize = scanner.nextInt();
		   
		   while (max <= maxsize + 2) {
		     GeneratingEvenNumbers task = new GeneratingEvenNumbers(min, max - 1 );
		     min = max;
		     max = min + taskThread;
		     threadtask.add(task);                           //Adds the tasks to the thread
		     taskslist.add(task);
		   }
		   threadtask.shutdown();                           //shutdown the thread
		   scanner.close();
		 }
	
	
	@Override
	public void run() {
		for (int i = sindex; i <= eindex; i++) {
			if (Even(i)) {
			evenNumbers.add(i);
			}                                              //Adds all the even numbers to the arraylist
		}
		System.out.println(" starts with " + Thread.currentThread().getName());
		System.out.println(" obtained the even numbers " + Arrays.toString(evenNumbers.toArray()) );
		System.out.println(" finishes with " + evenNumbers.size() + " even numbers obtained by " + Thread.currentThread().getName());
	}
	public static boolean Even(int number) {
		if (number % 2 == 0) {
		return true;                                //Checks if the number is even or not
		}
		return false;
		}
	public GeneratingEvenNumbers(int sindex, int eindex) {
		super("ObtainingEvenNumbers[" + sindex + ":" + eindex+"]");    //This method updates the start index and end index                              
		this.sindex = sindex;
		this.eindex = eindex;
	}
}