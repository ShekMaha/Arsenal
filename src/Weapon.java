import java.awt.Graphics;

import javax.swing.ImageIcon;


public class Weapon {
	double x,x1,x2;
	double y;
	double width;
	double height;
	ImageIcon img;
	ImageIcon revolver;
	ImageIcon pistol;
	ImageIcon bazooka;
	ImageIcon shotgun;
	ImageIcon machinegun;
	ImageIcon katana;
	ImageIcon minigun;
	private int delay;
	boolean automatic=false;
	double range=0;
	double recoil=0;
	boolean dual=false;
	int num=0;
	int nTimer=0;
	GamePanel gp;
	
	//String name="";
	Player p;
	public Weapon(Player p,GamePanel gp){
		this.gp=gp;
		this.p=p;
		
		revolver = new ImageIcon("gun.png");
		pistol = new ImageIcon("pistol.png");
		bazooka = new ImageIcon("bazooka.png");
		shotgun = new ImageIcon("shotgun.png");
		machinegun = new ImageIcon("machinegun.png");
		katana = new ImageIcon("katana.png");
		minigun = new ImageIcon("minigun.png");
		update(1);
		if(p.facingRight){			
			x=p.getX()+p.getWidth();
		}
		else if (p.facingLeft){			
			x=p.getX();
		}
		
		y = p.getY()+p.getHeight()/4;
	}
	
	public void update(int num){
		dual=false;
		automatic=false;
		recoil=0;
		this.num=num;
		gp.infoTimer=0;
		if (num == 1){
			gp.info="Pistol";
			dual=false;
			img = pistol;
			delay=10;
			
			range=800;
		}
		else if (num==2){
			gp.info="Revolver";
			dual=false;
			img=revolver;
			delay = 10;
			recoil=0;
			range=800;
			
		}
		else if (num==3){
			dual=true;
			gp.info="Dual Pistol";
			img=pistol;
			range=800;
			delay=10;
			recoil=0;
		}
		else if (num==4){
			gp.info="Bazooka";
			range=800;
			img=bazooka;
			delay=120;
			recoil=30;
		}
		else if (num==5){
			gp.info="Shotgun";
			range=200;
			delay=30;
			recoil=30;
			img=shotgun;
		}
		else if (num==6){
			gp.info="Machine Gun";
			range = 800;
			delay=2;
			automatic=true;
			gp.bCounter=2;
			img=machinegun;
			recoil=2;
		}
		else if (num==7){
			gp.info="Minigun";
			range=800;
			delay=2;
			gp.bCounter=2;
			automatic=true;
			img=minigun;
			recoil=5;
		}
		else if (num==8){
			gp.info="Katana";
			range=5;
			delay=10;
			img=katana;
		}
		
	}
	public void travel(){
		
		if (img==gp.dot){
			nTimer++;
			
		}
		if (nTimer>=20){
			img=katana;
			nTimer=0;
		}
		
		if (dual){
			x1=p.getX()+p.getWidth();
			x2=p.getX()-width;
		}
		else{
			if(p.facingRight){			
				x=p.getX()+p.getWidth()-5;
			}
			else {//if (p.facingLeft){			
				x=p.getX()-width+5;
			}
		}
		y = p.getY()+p.getHeight()/4;
	}
	public void draw(Graphics g){
		if (dual){
			g.drawImage(img.getImage(), (int)x1, (int)y, img.getIconWidth(), img.getIconHeight(), null);
			g.drawImage(img.getImage(), (int)(x2+width), (int)y, -img.getIconWidth(), img.getIconHeight(), null);
		}
		else{
		if (p.facingRight)
			g.drawImage(img.getImage(), (int)x, (int)y, img.getIconWidth(), img.getIconHeight(), null);
		else 
			g.drawImage(img.getImage(), (int)(x+width), (int)y, -img.getIconWidth(), img.getIconHeight(), null);
		}
	}
	public int getDelay(){
		return delay;
	}
	
}
