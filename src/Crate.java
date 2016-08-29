import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;



public class Crate {
	private double x;
	private double y;
	private double width;
	private double height;
	private int possibleY [] = {445,215,110,325};
	private int possibleY2 [] = {445,215,110};
	private int choiceY=0;
	static int score=0;
	//private int choiceX=0;
	private Player p;
	private int num=1;
	static int weapon=1;
	ImageIcon img;
	int temp=1;
	Weapon w;
	public  Crate(ImageIcon ic, Player p, Weapon w){
		this.p=p;
		img =ic;
		this.w=w;
		
		positioning();
		score =0;
		//x=choiceX;
		//y=choiceY;
		width = ic.getIconWidth();
		height=ic.getIconHeight();
	}
	
	public void draw(Graphics g){
		
		g.drawImage(img.getImage(),(int)x,(int)y, (int)width,(int) height,null);
	}
	
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,(int) width,(int) height);
	}
	
	public void checkCollision(){
		//System.out.println(x);
		if (p.crateCollision(GamePanel.cr)){
			w.update(weapon);
			System.out.println("weapon acquired");
			score++;			
			positioning();	
			
		}
	}
	public void positioning(){
		
		
		int c2 = (int)(Math.random()*2);
//		if (num==1){
//			weapon=1;
//			choiceY = (int)(Math.random()*possibleY2.length);
//			y=possibleY2[choiceY];
//		}
//		else{
			//weapon = (int)(Math.random()*4)+1;
			while (temp==weapon){
				weapon = (int)(Math.random()*8)+1;
			}
			temp=weapon;
			//weapon=7;
			choiceY = (int)(Math.random()*possibleY.length);
			y=possibleY[choiceY];
	//	}
		if (choiceY==0){			
			if (c2==1)
				x=(int)(Math.random()*270)+30;
			else 
				x=(int)(Math.random()*270)+415;
		}
		else if (choiceY==1){
			if (c2==1)
				x=(int)(Math.random()*170)+30;
			else
				x=(int)(Math.random()*170)+545;
		}
		else if (choiceY==2){
			x=(int)(Math.random()*270)+235;
		}
		else{
			x=(int)(Math.random()*270)+235;
		}
		//weapon = 7;
		
	}
	
}
