package FinalProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Tetris extends JFrame implements ActionListener{
	
	TetrisBlock a = new TetrisBlock();
	public Tetris() {
		
		//addKeyListener(a);
		add(a);
	}
	
	
	public static void main(String[] args) {
		Tetris frame = new Tetris();
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		JMenu jm1 = new JMenu("Game");
		JMenuItem jmi1 = jm1.add("New Game");
		JMenuItem jmi2 = jm1.add("Exit");
		JMenuItem jmi5 = jm1.add("Pause");
		JMenuItem jmi6 = jm1.add("Continue");
		
		JMenu jm2 = new JMenu("Help");
		JMenuItem jmi3 = jm2.add("Instruction");
		JMenuItem jmi4 = jm2.add("Rule");
		JMenuItem jmi7 = jm2.add("Detail");
		menu.add(jm1);
		menu.add(jm2);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(220, 275);
		frame.setTitle("Tetris");
		frame.setVisible(true);
		frame.setResizable(false);
		
		jmi1.addActionListener(frame);
		jmi1.setActionCommand("Restart");
		jmi2.addActionListener(frame);
		jmi2.setActionCommand("Exit");
		jmi5.addActionListener(frame);
		jmi5.setActionCommand("Pause");
		jmi6.addActionListener(frame);
		jmi6.setActionCommand("Continue");
		
		jmi3.addActionListener(frame);
		jmi3.setActionCommand("help");
		jmi4.addActionListener(frame);
		jmi4.setActionCommand("rule");
		jmi7.addActionListener(frame);
		jmi7.setActionCommand("detail");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		UIManager.put("OptoinPane.buttonFont", new FontUIResource(new Font("Times New Roman", Font.ITALIC, 18)));
		UIManager.put("OptoinPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.ITALIC, 18)));
		if ("Exit".equals(command)) {
			Object[] options = { "Confirm", "Cancel" };
			int response = JOptionPane.showOptionDialog(this, "Do you want to exit?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.exit(0);
			}
		} else if ("Restart".equals(command)) {
			a.NewGame();
		} else if ("help".equals(command)) {
			JOptionPane.showMessageDialog(null, "[Down]: down \n[Left]: left \n[Right]: right \n[Up]: rotate", "Hint! ", JOptionPane.INFORMATION_MESSAGE);
		} else if ("rule".equals(command)) {
			JOptionPane.showMessageDialog(null, "Game over if blocks reach the top of the screen. ", "Hint! ", JOptionPane.INFORMATION_MESSAGE);
		} else if ("Pause".equals(command)) {
			a.StopGame();
		} else if ("Continue".equals(command)) {
			a.ContinueGame();
		} else if ("detail".equals(command)) {
			JOptionPane.showMessageDialog(null, "Tetrominoes: \nI type: Light pink \nS type: Light blue \nZ type: Green \nJ type: Purple \nO type: Red \nL type: Yellow \nT type: Canet blue", "Hint! ", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}	

}
