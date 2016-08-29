import java.awt.CardLayout;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;


public class Game extends JFrame {
	
	static Container c;
	public static Game gameFrame;
	public static Menu menu;
	static int width,height;
	public static Instructions ins;
	public static Highscore high;
	public static GamePanel gp;
	CardLayout cl;
	
	static GraphicsDevice vc;
	
	public Game(String title){
		super(title);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
		gp = new GamePanel();
		menu = new Menu(gp);
		ins = new Instructions();
		high = new Highscore();
		c=getContentPane();
		cl= new CardLayout();
		c.setLayout(cl);
		c.add("Game", gp);
		c.add("Menu", menu);
		c.add("Instructions", ins);
		c.add("Highscore",high);
		addKeyListener(gp);
		width=790;
		height=540;
	}
	public static void setFullScreen(DisplayMode dm, JFrame window){
		//window.setUndecorated(false);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if (dm!=null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception e){}				
			
		}
	}
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	public void restoreScreen(){
		Window w=vc.getFullScreenWindow();
		if (w != null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	public static void main(String[]args) {
		
		DisplayMode dm = new DisplayMode(790,540,16,DisplayMode.REFRESH_RATE_UNKNOWN);
		
		gameFrame = new Game("ARSENAL");
		
		//gameFrame.run(dm);
		gameFrame.setVisible(true);
		gameFrame.setSize(width,height);
		//gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		//gameFrame.pack();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//gameFrame.setResizable(true);
		gameFrame.cl.next(gameFrame.c);
		gameFrame.cl.show(gameFrame.c, "Menu");
		
//		try{
//			Game.setFullScreen(dm, gameFrame);
//			try{
//				Thread.sleep(100000);
//			}catch(Exception e){}
//		}finally{
//			gameFrame.restoreScreen();
//		}
	}
	
	public void run(DisplayMode dm){
		
	}
	
}
