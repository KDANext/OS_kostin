package newLab5_OS;

public class main {

	public static void main(String[] args) {
		int ram = 1024;
		int sizeList = 128;
		FizMem fizMem = new FizMem(ram,sizeList);
		Manager manager = new Manager(fizMem);
	}

}
