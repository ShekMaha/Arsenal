import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Player implements KeyListener{
	private double x;
	private double y;
	private double width;
	private double height;
	private double velX;
	private double velY;
	private ImageIcon img, imgLeft, imgRight;
	private boolean jump=false;
	boolean shoot=false;
	private ImageIcon weapon;
	static int shootCounter=0;
	private Weapon w;
	boolean shot;
	boolean life = true;
	//private boolean gravity=true;
	private double playerToFloor=0, playerToWall=0, playerToTop=0;
	boolean right=false, left=false, up=false;
	boolean facingLeft = false, facingRight =true;
	boolean inAir = false;
	private static double maxVel=20, gravity = 1, jumpSpeed=-17;
	private GamePanel gpc;
	
	public Player(double x, double y,double velX, double velY,GamePanel game){
		this.x=x;
		this.y=y;
		this.velX=velX;
		this.velY=velY;	
		this.w=new Weapon(this,game);
		gpc=game;
		//img = new ImageIcon("player.png");
		imgLeft = new ImageIcon("playerleft.png");
		imgRight = new ImageIcon("playerRight.png");
		img = imgRight;
		//ay=0;
		width=img.getIconWidth();
		height = img.getIconHeight();
		//velX=0;
		//velY=0;
	}
	public void draw(Graphics g){
		//collision();
		g.drawImage(img.getImage(), (int) x, (int) y,(int) width, (int) height,null);
		//g.drawRect((int)x, (int)y, img.getIconWidth(), img.getIconHeight());
	}
	public void travel(){
		//if (crateCollision(GamePanel.cr)){
		shootCounter++;	
		//}
		if (y>535){
			//gpc.h.write(gpc.score);
			//gpc.dead.play();
			gpc.gameover=true;
			life=false;
			
			
		}
		if (enemyCollision(GamePanel.en)){
			//gpc.dead.play();
			gpc.gameover=true;
			life=false;
			
			
		}
		if (right){
			img = imgRight;
			velX=5;
			if (rightCollision(GamePanel.m)){
				velX=playerToWall;
			}
		}
		else if (left){
			img=imgLeft;
			velX=-5;
			if (leftCollision(GamePanel.m)){
				velX=-1*playerToWall;
			}
		}
		playerToWall=0;
		
		if (velY<maxVel){
			velY+=gravity;
		}
		
		if (bottomCollision(GamePanel.m)){
			jump=true;
			velY=playerToFloor;
			//velY=0;
			inAir = false;
		}
		else{
			inAir=true;
		}
		//jump=false;
		if (inAir){
			jump=false;
		}
		else{
			jump=true;
		}
		//else {
		//	jump=false;
		//}
		
		if (topCollision(GamePanel.m)){
			velY=-1*playerToTop;
		}
		playerToFloor=0;
		x+=velX;
		
		//velY+=ay;
		y+=velY;
		

		
	}
	public void setVelX(double vel){
		velX=vel;
	}
	public void setVelY(double vel){
		velY=vel;
		
	}
	
	public double getVelY(){
		return velY;
	}
	public double getVelX(){
		return velX;
	}

	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,img.getIconWidth(),img.getIconHeight());
	}
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y=y;
	}
	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x=x;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==e.VK_RIGHT||e.getKeyCode()==e.VK_D){
			right=true;
			facingLeft=false;
			facingRight=true;
//			rightKey = true;
//			leftKey=false;
		}

		else if(e.getKeyCode()==e.VK_LEFT||e.getKeyCode()==e.VK_A){
			left=true;
			facingLeft=true;
			facingRight=false;
			//leftKey= true;
			//rightKey=false;
			//p.setVelX(-5);
		}
		
		if ((e.getKeyCode()==e.VK_UP||e.getKeyCode()==e.VK_W)&&jump){
			up=true;
			velY=jumpSpeed;
			//gpc.jump.play();
		}
		
		if (e.getKeyCode()==e.VK_SPACE&&(!shoot||w.automatic)){
			shootCounter++;
			shot =true;
			shoot=true;
			
			
		}
		
	}
	public void doRecoil() {
		if (facingLeft){
			x+=w.recoil;
			if (rightCollision(GamePanel.m)){
				
				x-=w.recoil;
				System.out.println(x);
			}
			playerToWall = 0;
		}
		else {
			x-=w.recoil;
			if (leftCollision(GamePanel.m)){
				x=-playerToWall+30;
				
			}
			playerToWall = 0;
			
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==e.VK_RIGHT||e.getKeyCode()==e.VK_D){
			right = false;		
			velX=0;
			
		}
		
		else if(e.getKeyCode()==e.VK_LEFT||e.getKeyCode()==e.VK_A){			
			left= false;
			velX=0;
		
		}
		
		if(e.getKeyCode()==e.VK_UP||e.getKeyCode()==e.VK_W){			
			up = false;
		}
		
		if (e.getKeyCode()==e.VK_SPACE) {
			shootCounter=0;
			shoot=false;
			shot=false;
		}
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
	public boolean enemyCollision(ArrayList<Enemy> e){
		 for (int i=0; i<e.size(); i++){
			 if (getRect().intersects(e.get(i).getRect())){
				 return true;
			 }
		 }
		return false;
	}
	
	public boolean crateCollision(Crate cr){
		if (getRect().intersects(cr.getRect())){
			return true;
		}
		return false;
	}
	
	public double getWidth(){
		return width;
	}
	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	public Weapon getWeapon(){
		return w;
	}
	//public void setWeapon(ImageIcon img){
		//weapon=img;
	//}
}
