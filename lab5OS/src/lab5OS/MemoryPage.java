package lab5OS;

import java.util.ArrayList;
import java.util.Random;

public class MemoryPage {
	private Boolean signOfAppeal=true;
	private int startListX;
	private int startListY;
	private int size;
	private Memory memorys;
	
	public MemoryPage(Memory memorys, int indexX, int indexY) {
		startListX = indexX;
		startListY = indexY;
		size = memorys.getSizePage();
		this.memorys = memorys;
		clear();
	}	
	
	public void randomPage(int r) {
		Random rnd = new Random();
		for (int i = 0; i < size; i++) {
			memorys.setInformation(startListX, startListY+i, rnd.nextInt(r-1)+1);
		}
	}

	public Boolean getSignOfAppeal() {
		return signOfAppeal;
	}

	public void setSignOfAppeal(Boolean signOfAppeal) {
		this.signOfAppeal = signOfAppeal;
	}
	
	public int[] getInformationOnPage() {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = memorys.getInformation(startListX, startListY+i);
		}
		return array;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			memorys.setInformation(startListX, startListY+i,-1);
		}
		
	}
}
