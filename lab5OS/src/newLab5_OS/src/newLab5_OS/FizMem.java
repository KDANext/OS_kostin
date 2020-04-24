package newLab5_OS;

import java.util.ArrayList;

public class FizMem {
	private ArrayList<ListFizMem> arrayList = new ArrayList<ListFizMem>();
	
	public FizMem(int ram,int sizePage) {
		for (int i = 0; i < ram/sizePage; i++) {
			arrayList.add(new ListFizMem(sizePage));
		}
	}
	
	public ListFizMem getFizPage(int index) {
		return arrayList.get(index);
	}

	public ArrayList<ListFizMem> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<ListFizMem> arrayList) {
		this.arrayList = arrayList;
	}
	
	public ListFizMem getFreePage() {
		for (ListFizMem listFizMem : arrayList) {
			if(listFizMem.getFree()) {
				return listFizMem;
			}
		}
		return null;
	}
}
