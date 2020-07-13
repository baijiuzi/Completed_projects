package com.ls.Model.Enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.Bullet.ClottyBullet_1;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Tread.EnemyDeadTread;
import com.ls.Tread.ClottyJumpTread;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class Clotty extends Enemy{
	
	ClottyBullet_1[] cb1 = new ClottyBullet_1[4];
	ClottyJumpTread cjth;
	EnemyDeadTread cdth;
	
	static BufferedImage[] bimg = new BufferedImage[20];
	static{
		for (int i = 0; i < bimg.length; i++) {
			try {
				bimg[i] = ImageIO.read(new File("Enemy/Clotty/_a_frm"+i+",0 copy.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	int i = new Random().nextInt(20);
	boolean fire = false;

	public Clotty() {
		super(new Random().nextInt(StaticNum.Width-2*StaticNum.xborder)+StaticNum.xborder, new Random().nextInt(StaticNum.Height-2*StaticNum.yborder)+StaticNum.yborder, 64, 64, 20);
		this.img = bimg[0];
		this.life = 15;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		
		if (i==10) {
			cjth = new ClottyJumpTread();
			cjth.start();
		}
		
		if(i<20){
			g.drawImage(bimg[i], x, y, 100, 100, null);
			if (i==12) {
				fire = true;
				isFire(fire);
			}
			fire =false;
			i++;
		}else {
			g.drawImage(bimg[0], x, y, 100, 100, null);
			i = 0;
		}
		//System.out.println("图   "+i);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		if (i==11) {
			switch (new Random().nextInt(4)) {
			case 0:
				x += speed;
				break;
			case 1:
				x -= speed;
				break;
			case 2:
				y += speed;
				break;
			case 3:
				y -= speed;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void CheckBounds() {
		x = x<80?80:x;
		x = x>1024-80-100?1024-80-100:x;      
		y = y<25?25:y;					    
		y = y>652-100-100?652-100-100:y;
		if(dead || life<=0){
			MainController.getController().addScore(15);
			
			MainController.getController().removeObj(this);
			cdth = new EnemyDeadTread();
			cdth.start();
		}
	}
	
	public void isFire(boolean f){
		if (f) {
			for (int i = 0; i < cb1.length; i++) {
				cb1[i] = new ClottyBullet_1(x+50, y+50);
			}
			cb1[0].setD(Direction.W);
			cb1[1].setD(Direction.S);
			cb1[2].setD(Direction.A);
			cb1[3].setD(Direction.D);
		}
	}

}






