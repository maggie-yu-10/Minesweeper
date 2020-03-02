/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
    private MButton[][] mbuttons; 
    public boolean playing = false; // whether the game is running 
    private JLabel status;
    private JLabel count;// Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    public static long start = System.currentTimeMillis();

    public GameCourt(JLabel status, JLabel count) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        this.status = status;
        this.count = count;
        mbuttons = new MButton[9][9];
        
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick(start);
            }
        });
        timer.start();

        setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		mbuttons[i][j] = new MButton();
        		mbuttons[i][j].setXCom(i);
        		mbuttons[i][j].setYCom(j);
        		mbuttons[i][j].setM(mbuttons);
        		add(mbuttons[i][j]);
        	}
        }
        
        reset();
    }

    public void reset() {
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			mbuttons[i][j].reset();
    		}
    	}
    	start = System.currentTimeMillis();
    	setMines(mbuttons);
    	setNum(mbuttons);
        playing = true;
        status.setText("In progress...");
        repaint();

        requestFocusInWindow();
    }
    
    void tick(long start) {
        if (playing) {
        	boolean gameEnd = true;
        	boolean uncoveredMine = false;
        	for (int i = 0; i < 9; i++) {
        		for (int j = 0; j < 9; j++) {
        			if (!mbuttons[i][j].allUncover()) {
        				gameEnd = false;
        			}
        			if (mbuttons[i][j].getIsMine() && mbuttons[i][j].getUncover()) {
        				uncoveredMine = true;
        			}
        		}
        	}
        	if (gameEnd) {
        		for (int i = 0; i < 9; i++) {
        			for (int j = 0; j < 9; j++) {
        				mbuttons[i][j].setInGame(false);
        			}
        		}
        		playing = false;
        		status.setText("You win!");
        	}
        	if (uncoveredMine) {
        		for (int i = 0; i < 9; i++) {
        			for (int j = 0; j < 9; j++) {
        				mbuttons[i][j].setInGame(false);
        			}
        		}
        		playing = false;
        		status.setText("You lose!");
        	}
        	long temp = System.currentTimeMillis();
        	long time = (temp-start) / 1000;
        	long min = time / 60;
        	long sec = time % 60;
        	
        	if (sec < 10) count.setText("Time elapsed: " + min + ":0" + sec);
        	else count.setText("Time elapsed: " + min + ":" + sec);
        	

        	repaint();
        }
    }
    
    public void check() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (mbuttons[i][j].getIcon() == null) {
					mbuttons[i][j].setcanBeFlagged(!mbuttons[i][j].getcanBeFlagged());
				}
				mbuttons[i][j].setcheckBoxOn(!mbuttons[i][j].getcheckBoxOn());
			}
		}
    }
    
    public void setMines(MButton[][] mbuttons) {
    	//MAGGIE: randomly generates 10 coordinates to be mines and sets isMines as true for these coordinates
    	TreeSet<Coordinates> mineSet = new TreeSet<Coordinates>();
    	int mineCount = 10;
    	while (mineCount > 0) {
	    	int x = (int) (Math.random() * 9);
	    	int y = (int) (Math.random() * 9);
	    	int prevSize = mineSet.size();
	    	mineSet.add(new Coordinates(x, y));
	    	if (prevSize != mineSet.size()) {
	    		mineCount--;
	    	}
    	}   	
    	while (mineSet.size() > 0) {
	    	Coordinates first = mineSet.first();
	    	mbuttons[first.getX()][first.getY()].setIsMine(true);
	    	mineSet.remove(mineSet.first());
    	}
    	//MAGGIE: java collections: set makes sure there are no duplicate randomly generated mine coordinates
    }
    
    //finds how many neighbors are mines
    public void setNum(MButton[][] mbuttons) {
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			int val = 0;
    			int iMin = Math.max(0, i-1);
    			int iMax = Math.min(8, i+1);
    			int jMin = Math.max(0, j-1);
    			int jMax = Math.min(8, j+1);
    			for (int a = iMin; a <= iMax; a++) {
    				for (int b = jMin; b <= jMax; b++) {
    					if (mbuttons[a][b].getIsMine()) {
    						val++;
    					}
    				}
    			}
    			if (mbuttons[i][j].getIsMine()) val--;
    			mbuttons[i][j].setNum(val);
    		}
    	}
	}
	
	public MButton[][] getmbuttons() {
		return mbuttons;
	}
	public JLabel getCount() {
		return count;
	}
	public JLabel getStatus() {
		return status;
	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}