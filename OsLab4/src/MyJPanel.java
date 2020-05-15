import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	private int sizeDisc;
	private int sizeSector;
	private int sizePaintSectors;
	private int[] place;
	private int startSelectedFile;
	private ArrayList<File> files = new ArrayList<File>();
	
	public MyJPanel(int sizeDisc,int sizeSector) {
		this.sizeDisc = sizeDisc;
		this.sizeSector = sizeSector;
		this.sizePaintSectors = (int) Math.sqrt(Double.parseDouble(sizeDisc/sizeSector+""));
		place = new int[sizeDisc/sizeSector];
		
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
				nextSelectSector = place[i];
				g.setColor(Color.red);
			} else if(place[i]==0) {
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
		files.add(file);
		int size = file.getSize();
		int countSectors=size/sizeSector;
		int startNewFile = -1;
		int prevSector = 0;
		if(size%sizeSector>0)
			countSectors++;
		for (int i = 0; i < place.length; i++) {
			if(place[i]==0 && startNewFile == -1) {
				place[i] = -1;
				startNewFile = i;
				prevSector = i;	
				countSectors--;
				file.setStartInMem(startNewFile);
			} else if (place[i]==0) {
				place[prevSector]=i;
				prevSector = i;
				place[i]=-1;
				countSectors--;
			}
			if (countSectors==0)
				break;
		}
		return startNewFile;	
	}
	
	public void clearMemory(File file) {
		files.remove(file);
		int target = file.getStartInMem();
		if(place[target]!=-1) {
			clearMemory(place[target]);
		}
		place[target] = 0;
		startSelectedFile = -1;
	}

	private void clearMemory(int i) {
		if(place[i]!=-1) {
			clearMemory(place[i]);
		}
		place[i] = 0;
	}

	public int getStartSelectedFile() {
		return startSelectedFile;
	}

	public void setStartSelectedFile(int startSelectedFile) {
		this.startSelectedFile = startSelectedFile;
	}
}
