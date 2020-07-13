package com.ls.Model.Boss;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.GameObject;
import com.ls.Model.Bullet.BossBullet_1;
import com.ls.Model.Bullet.BossBullet_2;
import com.ls.Tread.EnemyDeadTread;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class MengSiChuo extends GameObject{
	
	static BufferedImage[] bimg = new BufferedImage[24];
	static BufferedImage bossHp;
	static BufferedImage bossHpRed;
	static{
		try {
			for (int i = 0; i < bimg.length; i++) {
				bimg[i] = ImageIO.read(new File("Mengsichuo/"+i+".png"));
			}
			bossHp = ImageIO.read(new File("bossHp/bossHp.png"));
			bossHpRed = ImageIO.read(new File("bossHp/bossHpRed.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int i = 0;
	private int count = 0;
	BossBullet_2[] cb1 = new BossBullet_2[4];
	EnemyDeadTread edth;

	public MengSiChuo() {
		super(100, 200, 78*2, 88*2, 0);
		this.img = bimg[0];
		this.life = 250;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(bossHpRed, 430, 20, (131*2/250)*this.life, 20*2, null);   //血量
		g.drawImage(bossHp, 420, 20, 131*2, 20*2, null);
		
		if(i<24){
			g.drawImage(bimg[i], x, y, 78*3, 88*3, null);
			i++;
		}else {
			g.drawImage(bimg[0], x, y, 78*3, 88*3, null);
			i = 0;
		}
		//System.out.println("图   "+i);
		count++;
		if (i==20) {
			new BossBullet_1(x+78, y+88);           //子弹1
		}
		if (i==11 && count%2==0) {
			for (int i = 0; i < 4; i++) {
				cb1[i] = new BossBullet_2(x+78+78/2, y+88*2);    //子弹2
			}
			cb1[0].setD(Direction.W);
			cb1[1].setD(Direction.S);
			cb1[2].setD(Direction.A);
			cb1[3].setD(Direction.D);
		}
		Move();
		CheckBounds();
		//System.out.println(this.life);
	}

	@Override
	public void Move() {  //跳
		if (i==8 && count%3==0) {
			x = MainController.getController().getHeroX()-73 + (new Random().nextInt(2)-1) * (new Random().nextInt(500));
			y = MainController.getController().getHeroY()-176 + (new Random().nextInt(2)-1) * (new Random().nextInt(500));
		}
	}

	@Override
	public void CheckBounds() {
		x = x<80?80:x;
		x = x>1024-80-100?1024-80-100:x;      
		y = y<25?25:y;					    
		y = y>652-100-100?652-100-100:y;
		MainController.getController().MengsichuoChangeList(this);  //判断谁在上一图层
		
		if(dead || life<=0){
			MainController.getController().removeObj(this);
			edth = new EnemyDeadTread();
			edth.start();
		}
	}

}
