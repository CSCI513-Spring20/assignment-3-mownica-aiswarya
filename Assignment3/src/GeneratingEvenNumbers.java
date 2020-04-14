import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GeneratingEvenNumbers extends Task {
	private int sindex;
	private int eindex;
	private ArrayList<Integer> evenNumbers;

	public static void main(String[] args) {
		ArrayList<GeneratingEvenNumbers> taskslist = new ArrayList<GeneratingEvenNumbers>();
		int min = 0;
		int max = 2;
		 
		Scanner scanner = new Scanner(System.in);
		   ThreadPooling threadtask = new ThreadPooling(10);
		   int taskThread = 2;
		 
		   System.out.print("Enter maximum size to generate even numbers: ");
		   int maxsize = scanner.nextInt();
		   
		   while (max <= maxsize + 2) {
		     GeneratingEvenNumbers task = new GeneratingEvenNumbers(min, max - 1 );
		     min = max;
		     max = min + taskThread;
		     threadtask.add(task);
		     taskslist.add(task);
		   }
		   threadtask.shutdown();
		   scanner.close();
		 }
	
	
	@Override
	public void run() {
		System.out.println(" starts with " + Thread.currentThread().getName());
		for (int i = sindex; i <= eindex; i++) {
			if (Even(i)) {
			evenNumbers.add(i);
			}
			}
		System.out.println(" obtained the even numbers " + Arrays.toString(evenNumbers.toArray()) );
		System.out.println(" finishes with " + evenNumbers.size() + " even numbers obtained by " + Thread.currentThread().getName());
	}
	public static boolean Even(int number) {
		if (number % 2 == 0) {
		return true;
		}
		return false;
		}
	public GeneratingEvenNumbers(int sindex, int eindex) {
		super("ObtainingEvenNumbers[" + sindex + ":" + eindex+"]");
		evenNumbers = new ArrayList<Integer>();
		this.sindex = sindex;
		this.eindex = eindex;
	}
}