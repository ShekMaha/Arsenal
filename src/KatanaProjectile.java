import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class KatanaProjectile extends Projectile{
	public KatanaProjectile(ImageIcon ic, ImageIcon explosion, Player p, Weapon w, double speed,double velY,int direction, double damage, double limit,double detonate) {
		super(ic, explosion,p,w,speed,velY,direction,damage,limit,detonate);
		
		
	}
	@Override
	public void travel(Player p){
		
		if (p.facingLeft){
			x=p.getX()+p.getWidth()-20;
			
		}
		else if (p.facingRight){
			x=p.getX()+p.getWidth();
		}
		y=p.getY()+p.getHeight()/4;
		
		super.travel(p);
	}
	@Override
	public Rectangle getRect(){
		
		if (cp.facingLeft){
			return new Rectangle((int)x-explosion.getIconWidth(),(int)y,explosion.getIconWidth(),explosion.getIconHeight());
		}
		
		return new Rectangle((int)x,(int)y,explosion.getIconWidth(),explosion.getIconHeight());
		
		
	}
	
}
