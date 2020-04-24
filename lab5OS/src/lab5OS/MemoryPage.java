package lab5OS;

import java.util.Random;

public class MemoryPage {
	private int[] memory;
	private Boolean signOfAppeal=true;
	
	public MemoryPage(int sizePage) {
		memory = new int[sizePage];
		randomPage(100000);
	}
	
	public int getInformation(int i) {
		return memory[i];
	}
	
	public boolean setInformation(int i,int information) {
		if(memory[i]==-1) {
			memory[i]=information;
			return true;
		} 
		return false;
	}
	
	private void newPage() {
		for (int i = 0; i < memory.length; i++) {
			memory[i]=-1;
		}
	}
	
	private int emptyIndex() {
		for (int i = 0; i < memory.length; i++) {
			if(memory[i]==-1) {
				return i;
			}
		}
		return -1;
	}
	
	public void randomPage(int r) {
		Random rnd = new Random();
		for (int i = 0; i < memory.length; i++) {
			memory[i] = rnd.nextInt(r);
		}
	}

	public Boolean getSignOfAppeal() {
		return signOfAppeal;
	}

	public void setSignOfAppeal(Boolean signOfAppeal) {
		this.signOfAppeal = signOfAppeal;
	}
	
	public int[] getMemory() {
		return memory;
	}
}
