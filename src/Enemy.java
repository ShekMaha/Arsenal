import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Enemy {
	private int choice=0;
	private int type=0;
	private ImageIcon img ;
	private ImageIcon angry;
	private ImageIcon enemy1 = new ImageIcon("enemy1.png");
	private ImageIcon angry1 = new ImageIcon("angryEnemy1.png");
	private ImageIcon enemy2 = new ImageIcon("enemy2.png");
	private ImageIcon angry2 = new ImageIcon ("angryEnemy2.png");
	private ImageIcon enemy3 = new ImageIcon("enemy3.png");
	private ImageIcon angry3 = new ImageIcon("angryEnemy3.png");
	private double x;
	private double y;
	private double width, height;
	private double velX;
	private double velY;
	private double playerToFloor=0, playerToWall=0, playerToTop=0;
	private static double maxVel=10, grav1 = 0.4, grav2 = 2; 
	private boolean inAir=false;
	private int counter=0;
	private int speedCounter=0;
	private int num=-1;
	private int direction=0;
	private double speed=0;
	private boolean normal=true;
	private double health;
	private boolean hit=false;
	private long hitTimer=0;
	private Player cp;
	private long animateTimer=0;
	private boolean animate=false;
	int prob=0;
	
	public Enemy(double x, double y, double velX, double velY, Player p, boolean wave){
		//super(x,y,velX,velY);
		
		cp=p;
		if (!wave){
			prob = (int)(Math.random()*100)+1;
			if (prob<=50){
				type=1;
			}
			else if (prob<=70){
				type=2;
			}
			else{
				type=3;
			}
		}
		else {
			type=2;
		}
		//type=3;
		//type = (int)(Math.random()*2)+1;
		if (type==1){
			img=enemy1;
			angry=angry1;
			health=10;
		}
		else if (type==2){
			img=enemy2;
			angry=angry2;
			health=2;
		}
		
		this.x = x;
		this.y = y;
		this.velX=velX;
		this.velY=velY;
		choice=(int)(Math.random()*2)+1;
		if (choice==1){
			direction=-1;
		}
		else {
			direction=1;
		}		
		
		
		speed=3;
		if (type==3){
			img=enemy3;
			angry=angry3;
			health=1;
		}
		width=img.getIconWidth();
		height =img.getIconHeight();
	}
	
	public void travel(){
		if (type==3){
			if (health<=0){			
				animate();
				destroy(GamePanel.en);
				return;
			}
			
			if (y<cp.getY()-10){
				velY=1.5;
			}
			else if (y>cp.getY()+10){
				velY=-1.5;
			}
			
			if (topCollision(GamePanel.m)){
				velY=-1*playerToTop;
			}
			if (bottomCollision(GamePanel.m)){
				counter++;
				inAir=false;
				//System.out.println(bottomCollision(GamePanel.m));
				velY=playerToFloor;
				//System.out.println(x);
				if (cp.getY()<y){
					velY=-2;
				}
				if (x>199&&x<236&&y<160){
					x-=1;
				}
				else if (x>199&&x<236&&y<275){
					x+=1;
				}
				else if (x>199&&x<236&&y<358){
					x-=1;
				}
				else if (x>533&&x<546&&y>160){
					x-=1;
				}
				else if (x>533&&x<533+width){
					x+=1;
				}
				//else if (x)
				calculateXDistance();
				//v elY=0;
				//inAir = false;
			}
			else{
				if (cp.getX()>=x){
					velX=2;
				}
				else if (cp.getX()<x){
					velX=-2;
				}
				
			}
			if (leftCollision(GamePanel.m)){
				velX=-1*playerToWall;
				num=1;
			}
			if (rightCollision(GamePanel.m)){
				velX=playerToWall;
				num=-1;
			}
			playerToFloor=0;
			y+=velY;
			x+=velX;
			//calculateXDistance();
		}
		else{
			if (health<=0){
				
				animate();
				destroy(GamePanel.en);
				return;
			}
			if (y>550){
				normal=false;
				x=274;
				speedCounter++;
				if (speedCounter<2){
					speed*=2;
				}
				y=0;
				
			}
			
			if (velY!=0){
				inAir=true;
			}
			
			if (choice==1||choice==2){				
				velX=speed*direction;
				
				if (inAir&&counter<30){				
					velX=0;
				}			
			}
			
			if (velY<maxVel){
				if (type==1){
					velY+=grav2;
				}
				else if (type==2){
					velY+=grav1;
				}
			}
			if (bottomCollision(GamePanel.m)){
				counter++;
				inAir=false;
				velY=playerToFloor;
				
				//velY=0;
				//inAir = false;
			}
			if (leftCollision(GamePanel.m)){
				velX=-1*playerToWall;
				direction=1;
			}
			if (rightCollision(GamePanel.m)){
				velX=playerToWall;
				direction=-1;
			}
			playerToFloor=0;
			x+=velX;
			
			//velY+=ay;
			y+=velY;
		
		
		
		}
		
		
		
		
		
		
		
		//else {
		//	jump=false;
		//}
		
		//if (topCollision(GamePanel.m)){
		//	velY=-1*playerToTop+1;
		//}
		
		

		
	
		//super.travel();
	}
	public void draw(Graphics g){
		
		if (hit){
			g.drawImage(angry.getImage(), (int) x, (int) y,(int) width, (int) height,null);
			hitTimer++;
			
			if (hitTimer>1){
				hit=false;
				
			}
		}
		else{
			
			if (!normal){
				g.drawImage(angry.getImage(), (int) x, (int) y,(int) width, (int) height,null);			
			}
			else
				g.drawImage(img.getImage(), (int) x, (int) y,(int) width, (int) height,null);
		}
		
	}
	public int getChoice(){
		return choice;
	}
	
	public boolean topCollision(Map m){
		if (velY>0){
			return false;
		}
		else {
			for (int i=0; i<m.getPlat().length;i++){
				if (m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x),(int)Math.round(y+velY)))||
				(m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+width),(int)Math.round(y+velY))))){
					playerToTop=y-(m.getPlat()[i].y+m.getPlat()[i].height);
					return true;
				}
			}
				
		}
		return false;
	}
	
	public boolean bottomCollision(Map m){
		if (velY<0){
			return false;
		}
		else {
			for (int i=0; i<m.getPlat().length;i++){
				if (m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x),(int)Math.round(y+height+velY)))||
				(m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+width),(int)Math.round(y+height+velY))))){
					playerToFloor=m.getPlat()[i].y-(y+height);
					return true;
				}
			}
				
		}
		return false;
	}
	public boolean leftCollision(Map m){
		if (velX>0){
			return false;
		}
		else {
			for (int i=0; i<m.getPlat().length;i++){
				if (m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+velX),(int)Math.round(y)))||
					(m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+velX),(int)Math.round(y+height-1))))){
					playerToWall=x-(m.getPlat()[i].x+m.getPlat()[i].width);
					return true;
				}
			}
				
		}
		return false;
	}
	public boolean rightCollision(Map m){
		if (velX<0){
			return false;
		}
		else {
			for (int i=0; i<m.getPlat().length;i++){
				if (m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+width+velX),(int)Math.round(y)))||
					(m.getPlat()[i].getBounds().contains(new Point((int)Math.round(x+width+velX),(int)Math.round(y+height-1))))){
					playerToWall=m.getPlat()[i].x-(x+width)-1;
					return true;
				}
			}
				
		}
		return false;
	}
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,(int) width,(int) height);
	}
	public static void createEnemy(ArrayList<Enemy> e, Player p,boolean wave){
		
			e.add(new Enemy(340,0,0,0,p,wave));		
		
	}
	public void destroy(ArrayList<Enemy> e){
		
		if (animateTimer>5){//hitTimer>1){
			
			e.remove(this);	
			animateTimer=0;
			//hit=false;
			//hitTimer=0;
		}
	}
	public void attacked(double damage){
		hit=true;		
		health-=damage;		
	}
	
	public void calculateXDistance(){
		//if (y<355)
		//if (y<475){
		if (y>175&&y<245){
			if (x<355){
				velX=2;
			}
			else{
				velX=-2;
			}
		}
		else if (y>390&&y<475){
			if (x<355){
				velX=2;
			}
			else{
				velX=-2;
			}
		}
		else{
			if (x<395){
				velX=-2;
			}
			else{
				velX=2;
			}
		}
		//}
		//double a =800-x;
		//double b = Math.abs(0-cp.getX());
		
	}
	
	public void animate(){
		animateTimer++;
		
		//hit=false;
		if (x<cp.getX()){
			velX=-20;
			velY=-10;
		}
		else if (x>cp.getX()){
			velX=20;
			velY=-10;
		}
//		if (animateTimer>50){
//			animateTimer=0;
//			return;
//		
//		}
		
		x+=velX;
		y+=velY;		
	}
//	public static void wave(){
//		time
//		e.add(new Enemy(340,0,0,0,p,true));
//		e.add(new Enemy(340,0,0,0,p,true));
//		e.add(new Enemy(340,0,0,0,p,true));
//		
//	}
}
