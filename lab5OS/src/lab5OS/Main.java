package lab5OS;

public class Main {

	public static void main(String[] args) {
		int ram = 16;
		int sizePage = 4;
		Manager manager = new Manager(ram, sizePage);
		manager.work();
	}

}
