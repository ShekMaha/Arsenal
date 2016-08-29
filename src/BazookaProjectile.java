import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class BazookaProjectile extends Projectile{
	public BazookaProjectile(ImageIcon ic, ImageIcon explosion, Player p, Weapon w, double speed,double velY,int direction, double damage, double limit,double detonate) {
		super(ic, explosion,p,w,speed,velY,direction,damage,limit,detonate);
		//x=x-100;
		//y=y-100;
		//damage = 10;
		
	}
	@Override
	public void travel(Player p){
		if (detonate>0){
			travelTime++;
		}
		
		if (travelTime>=detonate){
			destroy(GamePanel.pr);
			travelTime=0;
		}
		if (distance>=cw.range){
			destroy(GamePanel.pr);
			distance=0;
		}
		if (doCollision(GamePanel.en, GamePanel.m)){
			if (ic!=explosion){
				x-=200;
				y-=200;
			}
			ic=explosion;
			width = ic.getIconWidth();
			height = ic.getIconHeight();
			velX=0;
			velY=0;
			System.out.println(x+" "+y);
			
			if (explosionTimer>limit){
				destroy(GamePanel.pr);
				explosionTimer=0;
			}
			return;
			//GamePanel.pr.remove(doCollision(GamePanel.en, GamePanel.m, GamePanel.pr));
			
		}
		//if (p.facingLeft){
		//	x-=velX;
		//}
		//else {
		//	x+=velX;
		//}
		else{
		x+=velX;
		y+=velY;
		}
		distance+=Math.abs(velX);
		
	
	}
	@Override
	public void draw(Graphics g){
		if (ic==explosion){
			g.drawImage(ic.getImage(), (int)x, (int)y, (int)400, (int)400, null);
				
		}
		else
			super.draw(g);
	}
	
	@Override
	public Rectangle getRect(){
		if (ic==explosion)
			return new Rectangle((int)x+200,(int)y+200,400,400);
		return super.getRect();
	}
	
}
