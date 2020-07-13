package com.ls.Model.Bullet;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class BossBullet_1 extends Bullet{
	
	private int heroX ;
	private int heroY ;

	public BossBullet_1(int x, int y) {
		super(x, y, 213, 258, 5);
		try {
			img = ImageIO.read(new File("bullet/Boss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		d = Direction.N;
		this.life = 1;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(img, x, y, 100, 129, null);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		x += speed;
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub
		if (x<=StaticNum.xborder || y<=StaticNum.yborder-26 || x>=StaticNum.Width-StaticNum.xborder-179 || y>=StaticNum.Height-StaticNum.yborder || dead) {
			this.dead = true;
			MainController.getController().removeObj(this);
		}
	}

}
