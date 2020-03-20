package Os2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Core {
	ArrayDeque<Process> processes = new ArrayDeque<Process>();
	int WorkTime = 3;
	public Core() {
		Random rnd = new Random();
		for (int i = 0; i < 2; i++) {
			processes.add(new Process(i+" ",WorkTime));
		}
	}
	public void PrintAllFlow() {
		for (Process process : processes) {
			process.Print();
		}
	}
	public void Work() {
		for (int i = 0; i < 25; i++) {
			Process process = processes.poll();
			process.Start();
			if(!process.ProcessDone()) {
				processes.offer(process);
				System.out.println(process.GetName() + " " + "process need more time");
			}
			else {
				System.out.println(process.GetName() + " " + "process done");
			}
			if(processes.isEmpty()) {
				break;
			}
		}
	}
}
