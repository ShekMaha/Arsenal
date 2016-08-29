import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	static Map m;
	private Player p;
	static ArrayList <Enemy> en;
	static ArrayList <Projectile> pr;
	static Crate cr;
	private Weapon w;
	boolean newScore=false;
	Sound bgm = new Sound("credits.wav");
	Sound shotgun = new Sound("shotgun.wav");
	//Sound jump = new Sound("jump.wav");
	//Sound pistol = new Sound("pistol.wav");
	//Sound revolver = new Sound("revolver.wav");
	//Sound dualPistol = new Sound("dualPistol.wav");
	//Sound bazooka = new Sound("bazooka.wav");
	//Sound machinegun = new Sound("machinegun.wav");
	//Sound minigun = new Sound("minigun.wav");
	//Sound katana = new Sound ("katana.wav");
	//Sound dead = new Sound("dead.wav");
	//static Sound hit = new Sound("hit.wav");
	ImageIcon pistolProj = new ImageIcon("smallBullet.png");
	ImageIcon pistolExp = new ImageIcon("esmallBullet.png");
	ImageIcon revProj = new ImageIcon("bullet.png");
	ImageIcon revExp = new ImageIcon("ebullet.png");
	ImageIcon bazProj = new ImageIcon("bazBullet.png");
	ImageIcon bazExp = new ImageIcon("exp.gif");
	ImageIcon katanaProj = new ImageIcon("katanaHit.png");
	String info="";
	int infoTimer=0;
	static Timer timer;
	ImageIcon over = new ImageIcon("gameOver.png");
	static int projectileNum = 1;
	ImageIcon im = new ImageIcon("crate.png");
	String score="";
	private long spawnTimer =0;
	private int waveTimer=0;
	 int bCounter=0;
	boolean gameover = false;
	boolean pause = false;
	ImageIcon pauseImg = new ImageIcon("pause.png");
	int probWave =100;
	int back;
	int count=0;
	ImageIcon dot=new ImageIcon("dot.png");
	public GamePanel(){
		
		timer = new Timer(15,this);		
		
		//bgm.loop();
		
		
		//Game.gameFrame.cl.show(Game.gameFrame.c, "Game");
		//setFocusable(true);
		//addKeyListener(this);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		m.draw(g);
		if (pause){
			g.drawImage(pauseImg.getImage(),0,120,pauseImg.getIconWidth(), pauseImg.getIconHeight(),null);
			
		}
		
		if (infoTimer<50){
			g.setColor(Color.blue);
			g.setFont(new Font("Verdana", Font.BOLD, 20));
			g.drawString(info, (int)p.getX(), (int)p.getY()-10);
		}
		g.setColor(Color.black);
		g.setFont(new Font("Impact", Font.BOLD,18));
		g.drawString("Score: "+score, 20, 30);
		//p.travel();
		for (int i=0; i<en.size(); i++){
			en.get(i).travel();	
			en.get(i).draw(g);
		}
		
		cr.draw(g);
		
		if (p.shoot&&bCounter>w.getDelay()&&!w.automatic&&p.life){
			
			p.doRecoil();
			if (w.num==1){
				//pistol.play();
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,1,1,1,0));
				
			}
			else if (w.num==2){
				//revolver.play();
				pr.add(new BulletProjectile(revProj,revExp,p,w,24,0,1,5,1,0));
			}
			else if (w.num==3){
				//dualPistol.play();
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,2,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,3,1,1,0));
			}
			else if (w.num==4){
				//bazooka.play();
				pr.add(new BazookaProjectile(bazProj,bazExp,p,w,8,0,1,10,50,0));
				
			}
			else if (w.num==5){
				//s.shooting(5);
				shotgun.play();
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,-3,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,-2,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,2,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,3,1,1,1,0));
			}			
			
			else if (w.num==8){
				//katana.play();
				w.img=dot;
				pr.add(new KatanaProjectile(katanaProj,katanaProj,p,w,0,0,1,2.5,3,10));
				//w.img=w.katana;
			}
			
			p.shoot=false;
			bCounter=0;
		}
		else if (p.shoot&&bCounter>w.getDelay()&&w.automatic&&p.life){
			p.doRecoil();
			if (w.num==6){
				//machinegun.loop();
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,1,1,1,0));
			}
			else if (w.num==7){
				//minigun.loop();
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,-6,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,-4,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,0,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,6,1,1,1,0));
				pr.add(new SmallBulletProjectile(pistolProj,pistolExp,p,w,24,4,1,1,1,0));
			}
			bCounter=0;
		
		}
		
		//else if (p.shoot&&bCounter>w.getDelay()&&w.automatic&&p.life){
		//	p.doRecoil();
			
			
			
		//}
		for (int i=0; i<pr.size(); i++){
			pr.get(i).draw(g);
		}
		if (gameover){
			g.drawImage(over.getImage(),0,120,over.getIconWidth(), over.getIconHeight(),null);
			g.setColor(Color.black);
			g.setFont(new Font("Impact", Font.BOLD, 55));
			g.drawString("Crates: "+score, 250, 260);
		}
		else{
			p.draw(g);
			w.draw(g);
		}
		
	}
//	@Override
	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
	}
//	@Override
	public void keyPressed(KeyEvent e) {
		
		p.keyPressed(e);
		if (p.shootCounter>30&&!w.automatic){
			p.shoot=false;
		}
		if (gameover){
			shotgun.stop(); 
//			jump.stop(); 
//			pistol.stop();
//			revolver.stop(); 
//			dualPistol.stop();
//			bazooka.stop();
//			machinegun.stop();
//			minigun.stop();
//			katana.stop();
//			hit.stop();
			if (e.getKeyCode()==e.VK_ENTER){
				newGame();
				gameover=false;
			}
			
		}
		if (pause){
			if (e.getKeyCode()==e.VK_ENTER||e.getKeyCode()==e.VK_P){
				timer.start();
				pause=false;
				
			}
		}
		else{
			
			if (e.getKeyCode()==e.VK_P){
				pause = true;
				repaint();
			    timer.stop();
			}
			if (e.getKeyCode()==e.VK_ESCAPE){
				//Menu.menu.play();
				Game.gameFrame.cl.show(Game.gameFrame.c, "Menu");
				
				gameover=false;
				//dead.stop();
				bgm.stop();
				
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
		p.shootCounter=0;
		//sf.stopShake();
		//machinegun.stop();
		//minigun.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==timer){
			//collision();
			//s.music();
			//bgm.loop();
			
			bCounter++;
			score = Crate.score+"";
			//w.update(cr);
			if (gameover&&!newScore){
				Game.high.check(this);
				newScore=true;
				
				
			}
			 if(!gameover){
			    	//dead.stop();
			    	p.travel();
			    }
			w.travel();
		   
			cr.checkCollision();
			for (int i=0; i<pr.size(); i++){
				pr.get(i).travel(p);
			}
			repaint();
			infoTimer++;
			
			
			//if (newScore){
			//	dead.stop();
			//}
			if (probWave>30){
				spawnTimer++;
			}
			
			
			
			//System.out.println(spawnTimer);
			
			
			
				if (spawnTimer%150==0){
					
					if (waveTimer==0){
						probWave = (int)(Math.random()*100)+1;
					}
					//System.out.println(probWave);
					if (probWave<=30){
						waveTimer++;
						
						if (waveTimer%30==0){
							count++;
							Enemy.createEnemy(en,p,true);
						}
						if (count==3){
							waveTimer=0;
							probWave=100;
						}
						//Enemy.createEnemy(en,p,true);
						//Enemy.createEnemy(en,p,true);
						//probWave=100;
					}
					else{
						count=0;
						Enemy.createEnemy(en,p,false);
					}
				}
			
			
			
			//p.travel();			
		}
	}
	
	public void newGame(){
		timer.stop();
		timer.start();
		bgm.stop();
		bgm.loop();
		gameover=false;
		newScore=false;
		m = new Map(Game.gameFrame);
		p = new Player(330,300,0,0,this);
		p.life=true;
		
		w = p.getWeapon();
	
		cr = new Crate(im,p,w);
		en= new ArrayList<Enemy>();
		Enemy.createEnemy(en,p,false);
		pr = new ArrayList<Projectile>();
		Crate.score=0;
		spawnTimer=0;
		
	}

	
	
}

