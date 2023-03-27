package FinalProject;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class TetrisBlock extends JPanel implements KeyListener{

	// blockType 代表方块类型
	// turnState 代表方块状态
	private int blockType;
	private int score = 0;
	private int turnState;
	private int x;
	private int y;
	private int i = 0;
	int j = 0;
	int flag = 0;
	private static JFrame mainFrame=null;
	Timer timer = new Timer(1000, new TimeListener());
	public TetrisBlock() {
		setSize(220, 275);
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
	
		newblock();
	    newmap();
	    drawwall();
	    
	    timer.start();
	}
	
	
	// 定义已经放下的方块x=0-11,y=0-21;
	int[][] map = new int[13][23];

	// 方块的形状 第一组代表方块类型有S、Z、L、J、I、O、T 7种 第二组 代表旋转几次 第三四组为 方块矩阵
	private final int shapes[][][] = new int[][][] {
	// i
	        { { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
	                { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
	        // s
	        { { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
	        // z
	        { { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
	        // j
	        { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
	                { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	        // o
	        { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	        // l
	        { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	                { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	        // t
	        { { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	                { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	                { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } 
	};

	public void newblock() {
		
		blockType = (int) (Math.random() * 1000) % 7;
	    turnState = (int) (Math.random() * 1000) % 4;
	    x = 4;
	    y = 0;
	    if (gameover(x, y) == 1) {

	        newmap();
	        drawwall();
	        score = 0;
	        JOptionPane.showMessageDialog(null, "GAME OVER");
	    }
	}

	public void drawwall() {
		for (i = 0; i < 12; i++) {
	        map[i][21] = 2;
	    }
	    for (j = 0; j < 22; j++) {
	        map[11][j] = 2;
	        map[0][j] = 2;
	    }
	}
	
	public void newmap() {
		for (i = 0; i < 12; i++) {
	        for (j = 0; j < 22; j++) {
	            map[i][j] = 0;
	        }
	    }
	}
	
	// 旋转的方法
	public void turn() {
	    int tempturnState = turnState;
	    turnState = (turnState + 1) % 4;
	    if (blow(x, y, blockType, turnState) == 1) {
	    }
	    if (blow(x, y, blockType, turnState) == 0) {
	        turnState = tempturnState;
	    }
	    repaint();
	}
	
	// 左移的方法
	public void left() {
	    if (blow(x - 1, y, blockType, turnState) == 1) {
	        x = x - 1;
	    }
	    ;
	    repaint();
	}

	// 右移的方法
	public void right() {
	    if (blow(x + 1, y, blockType, turnState) == 1) {
	        x = x + 1;
	    }
	    ;
	    repaint();
	}

	// 下落的方法
	public void down() {
	    if (blow(x, y + 1, blockType, turnState) == 1) {
	        y = y + 1;
	        delline();
	    }
	    ;
	    if (blow(x, y + 1, blockType, turnState) == 0) {
	        add(x, y, blockType, turnState);
	        newblock();
	        delline();
	    }
	    ;
	    repaint();
	}
	
	
	
	// 是否合法的方法
	public int blow(int x, int y, int blockType, int turnState) {
	    for (int a = 0; a < 4; a++) {
	        for (int b = 0; b < 4; b++) {
	            if (((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
	                    + b + 1][y + a] == 1))
	                    || ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
	                            + b + 1][y + a] == 2))) {

	                return 0;
	            }
	        }
	    }
	    return 1;
	}
	
	// 消行的方法
	public void delline() {
	    int c = 0;
	    for (int b = 0; b < 22; b++) {
	        for (int a = 0; a < 12; a++) {
	            if (map[a][b] == 1) {

	                c = c + 1;
	                if (c == 10) {
	                    score += 10;
	                    for (int d = b; d > 0; d--) {
	                        for (int e = 0; e < 11; e++) {
	                            map[e][d] = map[e][d - 1];

	                        }
	                    }
	                }
	            }
	        }
	        c = 0;
	    }
	}
	
	
	
	public int gameover(int x, int y) {
		if (blow(x, y, blockType, turnState) == 0) {
			return 1;
		}
		    return 0;
	}
	
	// 把当前添加map
	public void add(int x, int y, int blockType, int turnState) {
	    int j = 0;
	    for (int a = 0; a < 4; a++) {
	        for (int b = 0; b < 4; b++) {
	            if (map[x + b + 1][y + a] == 0) {
	                map[x + b + 1][y + a] = shapes[blockType][turnState][j];
	            }
	            ;
	            j++;
	        }
	    }
	}
	

	// 画方块的的方法
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Color c1 = new Color((int)(Math.random() * 0x1000000));
	    
	    for (i = 0; i < 12; i++) {
	    	for (j = 0; j < 22; j++) {
	    		g.setColor(Color.white);
	    		g.drawRect(i * 10, j * 10, 10, 10);  
	    	}  
	    }
	    Color color;
	    switch (blockType) {
        case 0: //I
            color = new Color(236, 161, 166); //#eca1a6
            break;
        case 1: //S
        	color = new Color(146, 168, 209); //#92a8d1
        	break;
        case 2: //Z
        	color = new Color(107,142,35); //墨綠
        	break;
        case 3: //J
        	color = new Color(176, 170, 192); //#b0aac0
        	break;
        case 4://O
        	color = new Color(205,92,92); //indianred
        	break;
        case 5: //L
        	color = new Color(218,165,32); //goldenrod
        	break;
        default: //T
        	color = new Color(95,158,160); //cadetblue
        	break;
       }
	    
	    // 画当前方块
	    for (j = 0; j < 16; j++) {
	        if (shapes[blockType][turnState][j] == 1) {
	    		g.setColor(color);
	        	g.fillRect((j % 4 + x + 1) * 10, (j / 4 + y) * 10, 10, 10);
	        	g.setColor(Color.white);
	    		g.drawRect((j % 4 + x + 1) * 10, (j / 4 + y) * 10, 10, 10); 	
	        }
	    }
	   
	    
	    
	    // 画已经固定的方块
	    for (j = 0; j < 22; j++) {
	        for (i = 0; i < 12; i++) {
	            if (map[i][j] == 1) {
		    		g.setColor(Color.black);
	            	g.fillRect(i * 10, j * 10, 10, 10);
	            	g.setColor(Color.white);
		    		g.drawRect(i * 10, j * 10, 10, 10); 

	            }
	            if (map[i][j] == 2) {
	            	g.setColor(Color.GRAY);
	            	g.fillRect(i * 10, j * 10, 10, 10);

	            }
	        }
	    }
	    g.setColor(Color.black);
	    g.setFont(new Font("SansSerif", Font.PLAIN, 11));
	    g.drawString("Score: ", 125, 20);
	    g.setFont(new Font("SansSerif", Font.BOLD, 20));
	    g.drawString(""+score, 150, 50);
	    
	    
	    g.setFont(new Font("SansSerif", Font.BOLD, 11));
	    g.drawString("Instructions: ", 125, 100);
	    g.setFont(new Font("SansSerif", Font.PLAIN, 10));
	    g.drawString("[Up]: rotate", 125, 112);
	    g.drawString("[Down]: move down", 125, 124);
	    g.drawString("[Left]: move left", 125, 136);
	    g.drawString("[Right]: move right", 125, 148);
	    
	    g.drawString("Click 'Game' to exit,", 125, 178);
	    g.drawString("pause, continue,", 125, 190);
	    g.drawString("or start new game. ", 125, 202);
	    
	}

	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	    case KeyEvent.VK_DOWN:
	        down();
	        break;
	    case KeyEvent.VK_UP:
	        turn();
	        break;
	    case KeyEvent.VK_RIGHT:
	        right();
	        break;
	    case KeyEvent.VK_LEFT:
	        left();
	        break;
	    }	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class TimeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			repaint();
			if (blow(x, y + 1, blockType, turnState) == 1) {
				y = y + 1;
				delline();
			}
			;
			if (blow(x, y + 1, blockType, turnState) == 0) {

				if (flag == 1) {
					add(x, y, blockType, turnState);
					delline();
					newblock();
					flag = 0;
				}
				flag = 1;
			}
			;

		}

	}

	public void NewGame() {
		score = 0;
		newblock();
	    newmap();
	    drawwall();
	    repaint();
		
	}
	
	public void StopGame() {
		timer.stop();
	}
	
	public void ContinueGame() {
		timer.start();
	}
	
}
