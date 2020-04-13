public abstract class Task implements Runnable {
	String name;
	boolean isFinished;

	public Task(String name) {
		this.name = name;
		this.isFinished = false;
	}

	
}
