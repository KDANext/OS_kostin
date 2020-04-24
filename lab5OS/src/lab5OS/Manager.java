package lab5OS;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
	private ArrayList<memPage> listMemory;
	private ArrayList<int[]> outLoadPage = new ArrayList<int[]>();
	private int Rand = 100000;
	private Memory memory;
	Random rnd = new Random();

	public Manager(Memory memory) {
		this.memory = memory;
		memPage temp = memory.getIndexForNewPage();
		listMemory = new ArrayList<memPage>();
		do {		
			for (int j = 0; j < temp.getSize(); j++) {
				memory.setInformation(temp.getIndexStart(), j, rnd.nextInt(100000));
			}
			listMemory.add(temp);
			temp = memory.getIndexForNewPage();
		} while (temp!=null);
		ramdomPages();
	}
	public void outLoad(int i) {
		int[] tempMemory = new int[listMemory.get(i).getSize()];
		int startPage = listMemory.get(i).getIndexStart();
		for (int j = 0; j < tempMemory.length; j++) {
			tempMemory[j] = memory.getInformation(startPage,j);
			memory.setInformation(startPage, j, rnd.nextInt(100000));
		}
		outLoadPage.add(tempMemory);
	}

	private void ramdomPages() {
		for (memPage is : listMemory) {
			int indexStart = is.getIndexStart();
			for (int i = 0; i < is.getSize(); i++) {
				memory.setInformation(indexStart, i, rnd.nextInt(100000));
			}
		}		
	}
	public void work() {
		
		while (true) {
			for (int i = 0; i < rnd.nextInt(5)+1; i++) {
				listMemory.get(rnd.nextInt(listMemory.size())).setNeed(true);;;
			}
			for (int i = 0; i < listMemory.size(); i++) {
				if(listMemory.get(i).isNeed()) {
					listMemory.get(i).setNeed(false);
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
	private void printPages(ArrayList<memPage> list) {
		for (memPage memPage : list) {
			for (int i = 0; i < memPage.getSize(); i++) {
				System.out.printf("%7d",memory.getInformation(memPage.getIndexStart(), i));
			}
			System.out.println();
		}
	}
}

