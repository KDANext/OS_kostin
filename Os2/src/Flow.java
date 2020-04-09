import java.util.Random;

public class Flow {
	private String name;
	private int curTime;
	private int finishTime;
	
	public Flow(String name) {
		this.name = name;
		generateRandomFlow();
		printFlow();
	}

	public void printFlow() {
		System.out.println("flow:"+name+" time: "+curTime+" endTime: "+finishTime);
	}

	private void generateRandomFlow() {
		Random rnd = new Random();
		finishTime = rnd.nextInt(10)+1;
	}
	/**
	 * 
	 * @param timeForWork
	 * @return work is done return true else false
	 */
	public Boolean startWork(int timeForWork) {
		if(curTime+timeForWork >= finishTime) {
			curTime = finishTime;
			return true;
		} else {
			curTime+=timeForWork;
			return false;
		}
		
	}

	public String getName() {
		return name;
	}
}
