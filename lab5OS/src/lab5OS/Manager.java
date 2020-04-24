package lab5OS;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
	
	private ArrayList<MemoryPage> listMemory;
	private ArrayList<MemoryPage> outLoadPage = new ArrayList<MemoryPage>();
	private int Rand = 100000;
	private int sizePage;
	
	public Manager(int sizeMemory,int sizePage) {
		listMemory= new ArrayList<MemoryPage>();
		this.sizePage = sizePage;
		int tempCountPage = sizeMemory/sizePage;
		System.out.println(tempCountPage);
		for (int i = 0; i < tempCountPage; i++) {
			listMemory.add(new MemoryPage(sizePage));
		}
		ramdomPages();
	}
	public void outLoad(int i) {
		outLoadPage.add(listMemory.get(i));
		listMemory.remove(i);
		listMemory.add(i, new MemoryPage(sizePage));
	}

	private void ramdomPages() {
		for (MemoryPage memoryPage : listMemory) {
			memoryPage.randomPage(Rand);
		}		
	}
	public void work() {
		Random rnd = new Random();
		while (true) {
			for (int i = 0; i < rnd.nextInt(5)+1; i++) {
				listMemory.get(rnd.nextInt(listMemory.size())).setSignOfAppeal(true);;
			}
			for (int i = 0; i < listMemory.size(); i++) {
				if(listMemory.get(i).getSignOfAppeal()) {
					listMemory.get(i).setSignOfAppeal(false);
				} else {
					outLoad(i);
				}
			}
			printAll();
			System.out.println("Press Any Key To Continue...");
	        new java.util.Scanner(System.in).nextLine();
		}
	}
	private void printAll() {
		System.out.println("mainMemory:");
		printPages(listMemory);
		System.out.println("outLoadMemory:");
		printPages(outLoadPage);
	}
	
	private void printPages(ArrayList<MemoryPage> list) {
		for (MemoryPage memoryPage : list) {
			for (int i : memoryPage.getMemory()) {
				System.out.printf("%7d",i);
			}
			System.out.println();
		}
	}
}

