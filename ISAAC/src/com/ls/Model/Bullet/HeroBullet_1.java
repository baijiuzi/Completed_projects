package com.ls.Model.Bullet;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class HeroBullet_1 extends Bullet{

	public HeroBullet_1(int x, int y) {
		super(x, y, 26, 26, 20);
		try {
			img = ImageIO.read(new File("bullet/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d = Direction.N;
		this.life = 3;
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
		if (d == Direction.W) {
			y-=speed;
		}if (d == Direction.A) {
			x-=speed;
		}if (d == Direction.S) {
			y+=speed;
		}if (d == Direction.D) {
			x+=speed;
		}
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub
		if (x<=StaticNum.xborder || y<=StaticNum.yborder-26 || x>=StaticNum.Width-StaticNum.xborder || y>=StaticNum.Height-StaticNum.yborder ||dead) {
			this.dead = true;
			MainController.getController().removeObj(this);
		}
	}

}
