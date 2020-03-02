import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MButton extends JButton implements MouseListener {
	private boolean isMine;
	private boolean canBeFlagged;
	private boolean isNotFlagged;
	private boolean checkBoxOn;
	private boolean uncover; //indicates u uncovered good blank
	private ImageIcon blank, mine, flag, one, two, three, four, five, six, seven, eight;
	private ImageIcon num;
	private int xCom, yCom;
	private MButton[][] mbuttons;//coordinates in the array
	private boolean inGame;
	
    public MButton() { 
    	setIcon(null);
    	checkBoxOn = false;
    	canBeFlagged = false;
    	isMine = false;
    	uncover = false;
    	inGame = true;
    	isNotFlagged = true;
    	blank = new ImageIcon("files/mBlank.png");
    	mine = new ImageIcon("files/mine2.png");
    	flag = new ImageIcon("files/flag.png");
    	one = new ImageIcon("files/one2.png");
    	two = new ImageIcon("files/two2.png");
    	three = new ImageIcon("files/three2.png");
    	four = new ImageIcon("files/four2.png");
    	five = new ImageIcon("files/five2.png");
    	six = new ImageIcon("files/six2.png");
    	seven = new ImageIcon("files/seven2.png");
    	eight = new ImageIcon("files/flag.png");
    	this.addMouseListener(this);
    }
    
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		click();
	}
    
	//easier to test this way
	public void click() {
		if (inGame) {
			if (!uncover && !canBeFlagged && isNotFlagged) {
	
	   		 	uncover = true;
	   		 	if (isMine) {
	   		 		setIcon(mine);
	   		 	}
		   		else {
		   			 flood(xCom, yCom, mbuttons);
		   		}
			}
			else if (canBeFlagged && !uncover && checkBoxOn) {
				if (getIcon() == null) {
					setIcon(flag);
					isNotFlagged = false;
				}
				else if (!isNotFlagged) {
					setIcon(null);
					isNotFlagged = true;
				}
			}
		}
	}
    
    public void reset() {
    	setIcon(null);
    	canBeFlagged = false;
    	isNotFlagged = true;
    	isMine = false;
    	inGame = true;
    	//make sure that there are 10 random isMines
    	uncover = false;
    }
    
    public void setIsMine(boolean isMine) {
    	this.isMine = isMine;
    }
    
    public void setNum(int x) {
    	if (x == 0) num = blank;
    	else if (x == 1) num = one;
    	else if (x == 2) num = two;
    	else if (x == 3) num = three;
    	else if (x == 4) num = four;
    	else if (x == 5) num = five;
    	else if (x == 6) num = six;
    	else if (x == 7) num = seven;
    	else if (x == 8) num = eight;
    	else num = null;
    }
    
    public boolean getIsMine() {
    	return isMine;
    }
    
    public MButton[][] flood(int i, int j, MButton[][] mbuttons) {
    	//checks if the number of neighbors is nonzero, click and stop moving if > 0
    	//error: only going up to the four sides?
    	//int neighbors = checkNeighbors(i , j, mbuttons);
    	ImageIcon temp = mbuttons[i][j].getBlank();
    	ImageIcon temp1 = mbuttons[i][j].getNum();
    	if (mbuttons[i][j].getcanBeFlagged()) {}
    	else if (temp1 != temp) {
    		mbuttons[i][j].setIcon(mbuttons[i][j].getNum());
    		mbuttons[i][j].setUncover(true);
			return mbuttons;
    	}
    	else {
    		mbuttons[i][j].setUncover(true);
    		mbuttons[i][j].setIcon(num);
    		int iMin = Math.max(0, i-1);
    		int iMax = Math.min(8, i+1);
    		int jMin = Math.max(0, j-1);
    		int jMax = Math.min(8, j+1);
    		if (iMin == i-1 && !mbuttons[iMin][j].getUncover()) {
    			flood(iMin, j, mbuttons);
    		}
    		if (iMax == i+1 && !mbuttons[iMax][j].getUncover()) {
    			flood(iMax, j, mbuttons);
    		}
    		if (jMin == j-1 && !mbuttons[i][jMin].getUncover()) {    			
    			flood(i, jMin, mbuttons);
    		}
    		if (jMax == j+1 && !mbuttons[i][jMax].getUncover()) {
    			flood(i, jMax, mbuttons);
    		}
    	}
    	return mbuttons;
    }
    
    
    public boolean allUncover() {
    	if ((!isMine && uncover) || (isMine && !uncover)) {
    		return true;
    	}
    	else  {
    		return false;
    	}
    }
    
    public void setXCom(int x) {
    	xCom = x;
    }
    public void setYCom(int y) {
    	yCom = y;
    }
    public void setUncover(boolean x) {
    	uncover = x;
    	setIcon(num);
    }
    public void setM(MButton[][] m) {
    	mbuttons = m;
    }
    
    public ImageIcon getNum() {
    	return num;
    }
    public ImageIcon getBlank() {
    	return blank;
    }
    public ImageIcon getFlag() {
    	return flag;
    }
    public ImageIcon getOne() {
    	return one;
    }
    public ImageIcon getMine() {
    	return mine;
    }
    public boolean getUncover() {
    	return uncover;
    }
    public void setcanBeFlagged(boolean x) {
    	canBeFlagged = x;
    }
    public boolean getcanBeFlagged() {
    	return canBeFlagged;
    }
    public void setInGame(boolean x) {
    	inGame = x;
    }
    public void setcheckBoxOn(boolean x) {
    	checkBoxOn = x;
    }
    public boolean getcheckBoxOn() {
    	return checkBoxOn;
	}
	public void setisNotFlagged(boolean x) {
		isNotFlagged = x;
	}
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
