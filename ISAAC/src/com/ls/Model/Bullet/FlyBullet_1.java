package com.ls.Model.Bullet;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class FlyBullet_1 extends Bullet{
	
	private int heroX ;
	private int heroY ;

	public FlyBullet_1(int x, int y) {
		super(x, y, 26, 26, 5);
		try {
			img = ImageIO.read(new File("bullet/bullet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d = Direction.N;
		this.life = 1;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, 13, 13, null);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		heroX = MainController.getController().getHeroX()+44;
		heroY = MainController.getController().getHeroY()+44;
		if (heroX-this.x>0 && heroY-this.y>0) {  //相对第一象限
			x += speed;
			y += speed;
		}if (heroX-this.x<0 && heroY-this.y>0) {  //相对第2象限
			x -= speed;
			y += speed;
		}if (heroX-this.x<0 && heroY-this.y<0) {  //相对第3象限
			x -= speed;
			y -= speed;
		}if (heroX-this.x>0 && heroY-this.y<0) {  //相对第4象限
			x += speed;
			y -= speed;
		}
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub
		if (x<=StaticNum.xborder || y<=StaticNum.yborder-26 || x>=StaticNum.Width-StaticNum.xborder || y>=StaticNum.Height-StaticNum.yborder || dead || MainController.getController().getController().FlyDied()) {
			this.dead = true;
			MainController.getController().removeObj(this);
		}
	}

}
