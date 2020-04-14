public abstract class Task implements Runnable {
	String name;
	boolean complete;

	public Task(String name) {
		this.name = name;
		this.complete = false;
	}

	public String getName() {
		return name;
	}
 	
}
