package lab5OS;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
	private ArrayList<MemoryPage> listMemory;
	private ArrayList<int[]> outLoadPage = new ArrayList<int[]>();
	private int Rand = 100000;
	private int sizePage;

	public Manager(Memory memory) {
		int[] temp = memory.getIndexForNewPage();
		listMemory = new ArrayList<MemoryPage>();
		do {		
			listMemory.add(new MemoryPage(memory,temp[0],temp[1]));
			temp = memory.getIndexForNewPage();
		} while (temp!=null);
		ramdomPages();
	}
	public void outLoad(int i) {
		outLoadPage.add(listMemory.get(i).getInformationOnPage());
		listMemory.get(i).randomPage(Rand);;
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
		printPagesOut(outLoadPage);
	}
	
	private void printPagesOut(ArrayList<int[]> list) {
		for (int[] is : list) {
			for (int i : is) {
				System.out.printf("%7d",i);
			}
			System.out.println();
		}		
	}
	private void printPages(ArrayList<MemoryPage> list) {
		for (MemoryPage memoryPage : list) {
			for (int i : memoryPage.getInformationOnPage()) {
				System.out.printf("%7d",i);
			}
			System.out.println();
		}
	}
}

