package com.ls.Model.Enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.Bullet.FlyBullet_1;
import com.ls.Tread.EnemyDeadTread;
import com.ls.Tread.FlyTread;
import com.ls.Util.StaticNum;

public class Fly extends Enemy{

	static BufferedImage[] flis = new BufferedImage[2];
	int i = 0;
	private int heroX ;
	private int heroY ;
	private int count = 0;
	FlyTread fth;
	EnemyDeadTread edth;
	
	static{
		try {
			flis[0] =  ImageIO.read(new File("Enemy/Fly/fly0.png"));
			flis[1] =  ImageIO.read(new File("Enemy/Fly/fly1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Fly() {
		super(new Random().nextInt(StaticNum.Width), new Random().nextInt(StaticNum.Height), 55, 55, 8);
		this.img = flis[0];
		this.life = 8;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		
		if (i==1) {
			fth = new FlyTread();
			fth.start();
		}
		
		g.drawImage(flis[i], x, y, 55, 55, null);
		count++;
		if (count%24==0) {
			new FlyBullet_1(x+25, y+25);
		}
		i = i==1?0:1;
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		heroX = MainController.getController().getHeroX();
		heroY = MainController.getController().getHeroY();
		//System.out.println(heroX + "     "+heroY);
		
		switch (new Random().nextInt(256)%4) {
		case 0:
			if (heroX-this.x>0) {  //相对第一象限
				x += speed;
			}
			break;
		case 1:
			if (heroY-this.y>0) { 
				y += speed;
			}
			break;
		case 2:
			if (heroX-this.x<0) { 
				x -= speed;
			}
			break;
		case 3:
			if (heroY-this.y>0) {  
				y -= speed;
			}
			break;

		default:
			break;
		}
		if (this.x>=heroX && this.x<=heroX+88 && this.y>=heroY && this.y<=heroY+88) {  //停
			speed = 0;
		}
		
		
		/*if (heroX-this.x>0 && heroY-this.y>0) {  //相对第一象限
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
		}if (heroX-this.x==0 && heroY-this.y==0) {  //停
			speed = 0;
		}*/
	}

	@Override
	public void CheckBounds() {
		x = x<80?80:x;
		x = x>1024-80-100?1024-80-100:x;      
		y = y<25?25:y;					    
		y = y>652-100-100?652-100-100:y;
		if(dead || life<=0){
			MainController.getController().addScore(8);
			
			MainController.getController().removeObj(this);
			edth = new EnemyDeadTread();
			edth.start();
		}
	}

}
