import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Map {
	private Rectangle rects[]= new Rectangle[10];
	private ImageIcon flame;

	public Map(Game g){
		
		rects[0]=new Rectangle(0,0,(int)(g.getWidth()/2.36),(int)(g.getHeight()/13.5));
		rects[1]=new Rectangle((int)(g.getWidth()/2.36)+(int)(g.getWidth()/6.58),0,(int)(g.getWidth()/2.36),(int)(g.getHeight()/13.5));
		rects[2] = new Rectangle(0,0,30,800);
		rects[3] = new Rectangle(750,0,40,800);
		rects[4]=new Rectangle(0,475,335,40);
		rects[5]=new Rectangle(415,475,335,40);
		rects[6]=new Rectangle(0,245,210,30);
		rects[7]=new Rectangle(570,245,210,30);
		rects[8]=new Rectangle(235,140,300,35);
		rects[9] = new Rectangle(225,355,330,35);
		flame=new ImageIcon("fire.gif");
		
	}
	public void draw(Graphics g){
		g.setColor(Color.gray);
		for (int i=0; i<rects.length; i++){
			g.fillRect(rects[i].x, rects[i].y, rects[i].width, rects[i].height);
		}
		
		g.drawImage(flame.getImage(), 345,460,70,60,null);
		
	}
	public Rectangle[] getPlat(){
		return rects;
	}
	public Rectangle getRect(){
		return new Rectangle(275,425,flame.getIconWidth(),flame.getIconHeight());
	}
	
}
