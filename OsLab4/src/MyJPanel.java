import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	private int sizeDisc;
	private int sizeSector;
	private int sizePaintSectors;
	private CellTable[] place;
	private int startSelectedFile;
	
	public MyJPanel(int sizeDisc,int sizeSector) {
		this.sizeDisc = sizeDisc;
		this.sizeSector = sizeSector;
		this.sizePaintSectors = (int) Math.sqrt(Double.parseDouble(sizeDisc/sizeSector+""));
		place = new CellTable[sizeDisc/sizeSector];
		for (int i = 0; i < place.length; i++) {
			place[i] = new CellTable(null, 0);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		int sizeX = this.getWidth()/sizePaintSectors;
		int sizeY = this.getHeight()/sizePaintSectors-4;
		int nextSelectSector=startSelectedFile;
		super.paint(g);
		int x = 0;
		int y = 0;
		for (int i = 0; i < place.length; i++) {
			if (x+sizeX>=getWidth()) {
				x=0;
				y+=sizeY;
			}
			if(i==nextSelectSector) {
				nextSelectSector = place[i].getNextCell();
				g.setColor(Color.red);
			} else if(place[i].getNextCell()==0) {
				g.setColor(Color.gray);
			} else {
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, sizeX, sizeY);
			g.setColor(Color.black);
			g.drawRect(x, y, sizeX, sizeY);
			x+=sizeX;
		}		
	}
	
	public int allocateMemoryForFile(File file) {
		int size = file.getSize();
		int countSectors=size/sizeSector;
		int startNewFile = -1;
		int prevSector = 0;
		if(size%sizeSector>0)
			countSectors++;
		for (int i = 0; i < place.length; i++) {
			if(place[i].getNextCell()==0 && startNewFile == -1) {
				place[i].setNextCell(-1);
				place[i].setFile(file);
				startNewFile = i;
				prevSector = i;	
				countSectors--;
				file.setStartInMem(startNewFile);
			} else if (place[i].getNextCell()==0) {
				place[prevSector].setNextCell(i);
				place[i].setFile(file);
				prevSector = i;
				place[i].setNextCell(-1);
				countSectors--;
			}
			if (countSectors==0)
				break;
		}
		return startNewFile;	
	}
	
	public void clearMemory(File file) {
		int target = file.getStartInMem();
		if(place[target].getNextCell()!=-1) {
			clearMemory(place[place[target].getNextCell()]);
		}
		place[target].setNextCell(0);
		startSelectedFile = -1;
	}

	private void clearMemory(CellTable cellTable) {
		if(cellTable.getNextCell()!=-1) {
			clearMemory(place[cellTable.getNextCell()]);
		}
		cellTable.setNextCell(0);
		cellTable.setFile(null);
	}

	public int getStartSelectedFile() {
		return startSelectedFile;
	}

	public void setStartSelectedFile(int startSelectedFile) {
		this.startSelectedFile = startSelectedFile;
	}
}
