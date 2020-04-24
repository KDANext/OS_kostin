package newLab5_OS;

import java.util.ArrayList;
import java.util.Random;

public class Manager {
	FizMem FizMem;
	ArrayList<VirMem> virMems = new ArrayList<VirMem>();
	ArrayList<ListFizMem> history = new ArrayList<ListFizMem>();
	Random rnd = new Random();

	public Manager(FizMem FizMem) {
		this.FizMem = FizMem;		
		ListFizMem temp = FizMem.getFreePage();
		while (temp != null) {
			virMems.add(new VirMem(temp));	
			temp.setFree(false);
			temp = FizMem.getFreePage();
		}
	}
	
	public void work() {
		while (true) {
			for (ListFizMem listFizMem : history) {
				listFizMem.setFree(rnd.nextBoolean());
			}
			for (VirMem virMem : virMems) {
				if(virMem.getInWork()) {
					virMem.setInWork(false);
				} else {
					outLoad(virMem);
				}
			}
		}
	}

	private void outLoad(VirMem virMem) {
		history.add(virMem.getListfizMem());
		virMem.getListfizMem().setFree(true);
		virMems.remove(virMem);
		virMems.add(new VirMem(FizMem.getFreePage()));
	}
}

