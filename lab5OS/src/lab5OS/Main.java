package lab5OS;

public class Main {

	public static void main(String[] args) {
		int ram = 16;
		int sizePage = 4;
		Memory memory = new Memory(ram, sizePage);
		Manager manager = new Manager(memory);
		manager.work();
	}

}
