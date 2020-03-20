package Os2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Core {
	private ArrayDeque<Process> processes = new ArrayDeque<Process>();
	private ArrayDeque<Flow> flows = new ArrayDeque<Flow>();
	private int WorkTime = 3;
	public Core() {
		Random rnd = new Random();
		for (int i = 0; i < rnd.nextInt(5)+1; i++) {
			Process process = new Process(i+" ",WorkTime);
			processes.offer(process);
			flows.addAll(process.getFlows());
		}
	}
	public void PrintAllFlow() {
		for (Process process : processes) {
			process.Print();
		}
	}
	public void Work() {
		for (int i = 0; i < 25; i++) {
			if (flows.isEmpty()) break;
			Flow flow = flows.poll();
			if(flow.MaybeWork(WorkTime)) {
				flow.DoIt();
			} else {
				flow.ErrorTime();
			}
		}
	}
}
