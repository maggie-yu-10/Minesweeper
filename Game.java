/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(300, 300);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("In progress...");
        status_panel.add(status);
        final JLabel count = new JLabel("Time elapsed: ");
        status_panel.add(count);
        
        final JFrame frame2 = new JFrame("Instructions window: Close when done reading");
        frame2.setLocation(500, 500);
        final JPanel text_panel = new JPanel();
        frame2.add(text_panel, BorderLayout.SOUTH);
        final JLabel instructions2 = new JLabel("Classic minesweeper, 9x9 and 10 mines. Mines are plants, flags, are cats. "
        		+ "Click to uncover a square. Click on Flag checkbox to set to flag-marking mode. Uncover all non-mine squares to win.");
        text_panel.add(instructions2);
        

        final GameCourt court = new GameCourt(status, count);
        frame.add(court, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final JCheckBox check = new JCheckBox("Flag");
        check.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		court.check();
        	}
        });
        control_panel.add(check);
        
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                if (check.isSelected()) {
                	check.doClick();
                	court.reset();
                }
            }
        });
        control_panel.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        frame2.pack();
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setVisible(true);

        court.reset();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}