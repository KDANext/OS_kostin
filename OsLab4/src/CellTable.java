
public class CellTable {
	private File file;
	private int nextCell;
	
	public CellTable(File file, int startInMem) {
		this.file = file;
		this.nextCell = startInMem;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getNextCell() {
		return nextCell;
	}

	public void setNextCell(int startFile) {
		this.nextCell = startFile;
	}
}
