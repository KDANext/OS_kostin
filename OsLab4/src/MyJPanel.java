import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	WorkFizMemory wfizMemory;
	
	public MyJPanel(WorkFizMemory wfizMemory) {
		this.wfizMemory = wfizMemory;
	}
	
	@Override
	public void paint(Graphics g) {
		int sizeX = this.getWidth()/wfizMemory.getSizePaintSectors();
		int sizeY = this.getHeight()/wfizMemory.getSizePaintSectors()-4;
		int nextSelectSector=wfizMemory.getStartSelectedFile();
		super.paint(g);
		int x = 0;
		int y = 0;
		for (int i = 0; i < wfizMemory.getPlace().length; i++) {
			if (x+sizeX>=getWidth()) {
				x=0;
				y+=sizeY;
			}
			if(i==nextSelectSector) {
				nextSelectSector = wfizMemory.getCell(i);
				g.setColor(Color.red);
			} else if(wfizMemory.getCell(i)==0) {
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

}
