package newLab5_OS;

import java.util.Random;

public class ListFizMem {
	private String data;
	private Boolean free = true;
	private int size;
	
	public ListFizMem(int sizePage) {
		data ="";
		size = sizePage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}
}
