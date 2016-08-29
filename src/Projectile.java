import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public abstract class Projectile {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double velX;
	protected double velY;
	protected double speed;
	double gravity;
	protected double damage;
	protected double limit;
	protected int distance=0;
	ImageIcon ic;
	ImageIcon explosion;
	protected long explosionTimer=0;
	protected Player cp;
	protected Weapon cw;
	boolean left;
	boolean right;
	boolean neutral;
	double detonate;
	double travelTime=-1;
	public Projectile(ImageIcon ic,ImageIcon explosion, Player p, Weapon w,double speed,double velY,int direction, double damage, double limit,double detonate){
		this.ic =ic;
		this.explosion =explosion;
		this.speed=speed;
		this.damage=damage;
		this.limit=limit;
		this.velY = velY;
		this.detonate=detonate;
		width=ic.getIconWidth();
		height = ic.getIconHeight();
		cp=p;
		cw=w;
		neutral=false;
		left=false;
		right=false;
		
		if (direction==1){
			neutral=true;
		}
		else if (direction==2){
			left=true;
			neutral=false;
		    velX=-speed;
		    x=w.x1;
		    width=-width;
		}		
		else if (direction==3){
			right=true;
			neutral=false;
		velX=speed;
		x=w.x2+w.width;
		
		}
		
		if (neutral){
			if(p.facingRight){
				velX=speed;
				x=w.x;
				
			}
			else if (p.facingLeft) {//if (p.facingLeft){
				velX=-speed;
				//x=p.getX();
				x=w.x;
				width=-width;
			}
		}
		y=w.y-3;
		//y = p.getY()+p.getHeight()/4;
		
		//System.out.println(x);
		//System.out.println(y);
	}
	public void draw(Graphics g){	
		if (!neutral){
			//if (right)
				g.drawImage(ic.getImage(), (int)x, (int)y, (int)width, (int)height, null);
			//else
				//g.drawImage(ic.getImage(),(int)x /*(int)(x+width)*/, (int)y, (int) width/*(int)-width*/, (int)height, null);
			
		}
		else if (neutral){
		//if (cp.facingRight)
			g.drawImage(ic.getImage(), (int)x, (int)y, (int)width, (int)height, null);
		//else 			
			//g.drawImage(ic.getImage(), (int)(x+width), (int)y, (int)-width, (int)height, null);		
		}
	}
	public boolean doCollision(ArrayList<Enemy> e, Map m){
		for (int i=0; i<e.size(); i++){			
			if (getRect().intersects(e.get(i).getRect())){
				
				//GamePanel.hit.play();
				explosionTimer++;
				
				if (explosionTimer>1){
				     e.get(i).attacked(damage);
				}
				return true;				
			}
		 }
		
		for ( int i=0; i<m.getPlat().length; i++){			
			if (getRect().intersects(m.getPlat()[i])){
				
				if (detonate==0){
					explosionTimer++;
					return true;	
				}
				else{
					return false;
				}
			}
		
		}
		return false;
	}
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
			ic=explosion;
			width = ic.getIconWidth();
			height = ic.getIconHeight();
			
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
	
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,ic.getIconWidth(),ic.getIconHeight());
	}
	
	public void destroy(ArrayList<Projectile> pr){
		pr.remove(this);		
	}
	
	
//	public void createProjectile(ArrayList<Projectile> pr, Player p){
//		if (p.left){
//			x=p.getX();			
//		}
//		else if(p.right){
//			x=p.getX()+p.getWidth();
//		}
//		y = (p.getX()+p.getWidth())/2;
//		pr.add(new Projectile((x,y,width,height));
//	}
}
