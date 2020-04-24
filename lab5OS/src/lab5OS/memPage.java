package lab5OS;

public class memPage {
	private int indexStart;
	private int size;
	private boolean need;
	
	public memPage(int indexStart,int size) {
		this.indexStart = indexStart;
		this.size = size;
		need=true;
	}

	public int getIndexStart() {
		return indexStart;
	}

	public void setIndexStart(int indexStart) {
		this.indexStart = indexStart;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isNeed() {
		return need;
	}

	public void setNeed(boolean need) {
		this.need = need;
	}
}
