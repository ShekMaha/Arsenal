import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Menu extends JPanel implements ActionListener, MouseListener{
	private ImageIcon back;
	private JButton b1, b2,b3, b4, b5,b6 , b7;
	private JLabel l1,l2,l3,l4;
	private GamePanel gpc;
	//static Sound menu = new Sound("menu.wav");
	public Menu(GamePanel game){
		//menu.loop();
		gpc=game;
		back= new ImageIcon("menu2.png");
		b5 = new JButton("     ");
		b6 = new JButton("     ");
		b7 = new JButton("   ");
		
		b1 = new JButton("        Play        ");
		b1.setFont(new Font("Impact",Font.PLAIN, 20));
		b2 = new JButton("Instructions");
		b2.setFont(new Font("Impact",Font.PLAIN, 20));
		b3 = new JButton("  Highscore  ");
		b3.setFont(new Font("Impact",Font.PLAIN, 20));
		b4 = new JButton("         Exit         ");
		b4.setFont(new Font("Impact",Font.PLAIN, 20));
		
		setLayout(new FlowLayout(FlowLayout.CENTER,800,25));
		b7.setOpaque(false);
		b7.setBackground(Color.WHITE);
		b7.setBorderPainted(false);
		b6.setOpaque(false);
		b6.setBackground(Color.GRAY);
		b6.setBorderPainted(false);
		b5.setOpaque(false);
		b5.setBackground(Color.GRAY);
		b5.setBorderPainted(false);
		
		b7.setFocusable(false);
		b6.setFocusable(false);
		b5.setFocusable(false);
		add(b7);
		add(b6);
		add(b5);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		b4.setFocusable(false);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		b1.setOpaque(false);
		setFocusable(false);
		//setLayout(new BoxLayout(MyFrame.c, BoxLayout.Y_AXIS));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.setFont(new Font("Impact", Font.BOLD, 24));
		
		g.drawImage(back.getImage(), -8, -10, back.getIconWidth(), back.getIconHeight(),null);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		JButton b = (JButton) (e.getSource());
		if (b.getText().equals("        Play        ")){
			Game.gameFrame.cl.show(Game.gameFrame.c, "Game");
			//menu.stop();
			gpc.newGame();
		
		}
		else if (b.getText().equals("Instructions")){
			Game.gameFrame.cl.show(Game.gameFrame.c, "Instructions");
		}
		
		else if (b.getText().equals("  Highscore  ")){		
			Game.high.read();
			Game.gameFrame.cl.show(Game.gameFrame.c, "Highscore");
			
		}
		else if (b.getText().equals("         Exit         ")){
			System.exit(0);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
