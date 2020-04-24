package lab5OS;

public class Memory {
	int [][] memory;
	int sizeX;
	int sizeY;	
	
	public Memory(int rAM,int sizeList) {
		sizeX = rAM/sizeList;
		sizeY = sizeList;
		memory = new int[sizeX][sizeY];
	}
	
	public memPage getIndexForNewPage() {
		for (int i = 0; i < sizeX; i++) {
			if(memory[i][0]==0) {
				return new memPage(i, sizeY);
			}
		}
		return null;
	}
	
	public void setInformation(int i,int j,int information) {
		memory[i][j]=information;
	}

	public int getSizePage() {
		return sizeY;
	}

	public int getInformation(int i, int j) {
		return memory[i][j];		
	}
}
