package com.ls.Model.Bullet;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class BossBullet_2 extends Bullet{

	public BossBullet_2(int x, int y) {
		super(x, y, 26, 26, 15);
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
		g.drawImage(img, x, y, 30, 30, null);
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
