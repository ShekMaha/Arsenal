import javax.swing.ImageIcon;


public class SmallBulletProjectile extends Projectile{
	public SmallBulletProjectile(ImageIcon ic, ImageIcon explosion, Player p, Weapon w, double speed,double velY,int direction,double damage, double limit, double detonate) {
		super(ic, explosion,p,w,speed,velY,direction,damage,limit,detonate);
		damage = 1;
		
	}
}
