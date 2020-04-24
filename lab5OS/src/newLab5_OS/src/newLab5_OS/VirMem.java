package newLab5_OS;

public class VirMem {
	ListFizMem listfizMem;
	Boolean inWork = true;
	
	public VirMem(ListFizMem ListFizMem) {
		this.listfizMem = listfizMem;
	}

	public ListFizMem getListfizMem() {
		return listfizMem;
	}

	public void setListfizMem(ListFizMem listfizMem) {
		this.listfizMem = listfizMem;
	}

	public Boolean getInWork() {
		return inWork;
	}

	public void setInWork(Boolean inWork) {
		this.inWork = inWork;
	}
}
