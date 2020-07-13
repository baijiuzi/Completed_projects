package com.ls.Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Bullet.HeroBullet_2;
import com.ls.Tread.FireTread;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;
import com.ls.View.LoginFrame;

public class Hero extends GameObject{
	
	HeroBullet_1 hb1;
	HeroBullet_2 hb2;
	static boolean fire = false;
	//static  public boolean stone = false;
	public boolean isChange = false;
	public boolean getBuff = false;
	
	public int score = 6;
	
	static BufferedImage[] hpImg = new BufferedImage[17];  //血量
	static BufferedImage[] WImg = new BufferedImage[3];
	static BufferedImage[] SImg = new BufferedImage[3];
	static BufferedImage[] AImg = new BufferedImage[3];
	static BufferedImage[] DImg = new BufferedImage[3];
	static BufferedImage[] NImg = new BufferedImage[3];
	static BufferedImage getBuffImg ;
	static BufferedImage[][] DirectionImg = {WImg,SImg,AImg,DImg,NImg};    //四个方向的三个动态的图
	static{
		try {
			for (int i = 0; i < hpImg.length; i++) {
				hpImg[i] = ImageIO.read(new File("heroHp/"+i+".png"));
			}
			for (int i = 0; i < WImg.length; i++) {
				WImg[i] = ImageIO.read(new File("hero/hero W_"+i+".png"));
			}
			for (int i = 0; i < SImg.length; i++) {
				SImg[i] = ImageIO.read(new File("hero/hero S_"+i+".png"));
			}
			for (int i = 0; i < AImg.length; i++) {
				AImg[i] = ImageIO.read(new File("hero/hero A_"+i+".png"));
			}
			for (int i = 0; i < DImg.length; i++) {
				DImg[i] = ImageIO.read(new File("hero/hero D_"+i+".png"));
			}
			for (int i = 0; i < DImg.length; i++) {
				NImg[i] = ImageIO.read(new File("hero/hero S_0.png"));
			}
			
			getBuffImg = ImageIO.read(new File("hero/1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static int dir = 4;//默认DirectionImg[4][] 也就是Nimg
	
	public Hero() {
		super(StaticNum.Width/2, StaticNum.Height/2, 88, 88, 8);  //不居中是因为hero的左上角为顶点
		this.img = NImg[0]; //默认图片 正对面
		this.d = Direction.N;
		this.life = 6;
		
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		if (this.getLife()>=0) {
			g.drawImage(hpImg[this.getLife()], 0, 0, 128*2, 15*2, null);   //画血
		}
								//分为开火时的Hero图片的不开火时的图片 使用下面的isFire方法设置fire
								//！！但是开火时松开时候开火  松开又按照控制hero的方向了 让人火大 就这样吧
		//System.out.println("DirTansNum(d)返回的dir是   "+dir+"   默认d  "+d);
		if (fire) {
			//System.out.println("   hb1的方向："+hb1.getD());
			if (getBuff) {   //获得Halo更换子弹 头随子弹方向
				g.drawImage(DirectionImg[DirTansNum(hb2.getD())][new Random().nextInt(3)], x, y, 88, 88 , null);
			}else {
				g.drawImage(DirectionImg[DirTansNum(hb1.getD())][new Random().nextInt(3)], x, y, 88, 88 , null);
			}
			
		}else {
			g.drawImage(DirectionImg[DirTansNum(d)][new Random().nextInt(3)], x, y, 88, 88 , null);
		} 
		if (isChange) {    //该图片用 用完没用
			g.drawImage(getBuffImg, x, y, 88, 88, null);
			isChange = false;
		}
		if (getBuff) {
			speed = 12;
			/*if (fire) {     改变子弹在下面的isfire  MainCon..里调用
				System.out.println("如果获得buff之后开火  改变子弹");
				hb1.setW(20);
				hb1.setH(20);  // x  不行 改成策略模式改子弹吧  x 子弹类型太多了  相对应的策略也太多了
				
			}*/
		}
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
		}if (d == Direction.WA) {
			x-=speed;
			y-=speed;
		}if (d == Direction.WD) {
			x+=speed;
			y-=speed;
		}if (d == Direction.AS) {
			x-=speed;
			y+=speed;
		}if (d == Direction.SD) {
			x+=speed;
			y+=speed;
		}
		//System.out.println("heroD    "+d);
		//System.out.println(this.life);
	}

	@Override
	public void CheckBounds() {  //想获得Bg的x y坐标 不是新建一个bg对象（!）  控制层新建连接的放法 那没事了
		//System.out.println(/*"背景左上角坐标  "+bgx+","+bgy + */"       hero左上角坐标  "+x+","+y);
		x = x<80?80:x;
		x = x>1024-80-88?1024-80-88:x;       //现在问题：更换地图的时候英雄检查边框不对了！！  
		y = y<25?25:y;					     //没事了 和图片左上角坐标没关系 从0.0算就成
		y = y>652-100-88?652-100-88:y;
		
		if (MainController.getController().getListSize() == 2 || getBuff == true && MainController.getController().getListSize() == 3) {  //只有英雄和背景的时候才能换房间/或者获得buff且只有背景英雄buff的时候
			//检查是否走到了换地图的地方
			if (x>=StaticNum.Width/2-40 && x<=StaticNum.Width/2-10 && y==25) {//上面的中点
				MainController.getController().tellBgDrection(Direction.W);
				//System.out.println("hero走到上面！！");
			}
			if (x>=StaticNum.Width/2-40 && x<=StaticNum.Width/2-10 && y==StaticNum.Height-100-88) {//下面的中点
				MainController.getController().tellBgDrection(Direction.S);
				//System.out.println("hero走到下面！！");
			}
			if (x==80 && y<=StaticNum.Height/2-78 && y>=StaticNum.Height/2-98) {//左面的中点
				MainController.getController().tellBgDrection(Direction.A);
				//System.out.println("hero走到左面！！");
			}
			if (x==StaticNum.Width-80-88 && y<=StaticNum.Height/2-78 && y>=StaticNum.Height/2-98) {//右面的中点
				MainController.getController().tellBgDrection(Direction.D);
				//System.out.println("hero走到右面！！");
			}
		}
		
		if(dead || life<=0){
			MainController.getController().removeObj(this);
			System.out.println("【Hero DEAD】 游戏结束  " + score);
			MainController.getController().updateRecord(LoginFrame.username,score);
			MainController.getController().createEndFrame();
		}
	}
	
	public void isFire(boolean f){
		if(f){    //按下开火
			
			FireTread fth = new FireTread();
			fth.start();
			
			if (getBuff) {
				hb2 = new HeroBullet_2(x+30, y+28);
				MainController.getController().tellHbulletDrection(hb2);
				//System.out.println("获得Buff  换个子弹");
			}else {
				hb1 = new HeroBullet_1(x+38, y+30);
				MainController.getController().tellHbulletDrection(hb1);
			}
			fire = true;
		}else {
			fire = false;
		}
	}
	
	public int DirTansNum(Direction di){        //下面的PrintSelf在用
		switch (di) {                           //问题！：如何选择对应的数组循环画图片   没事了ok了
		case W:									//方向转为 数组下标
			dir = 0;
			break;
		case S:
			dir = 1;
			break;
		case A:
			dir = 2;
			break;
		case D:
			dir = 3;
			break;
		case N:
			dir = 4;
			break;
		case WA:
			dir = 0;
			break;                          //子弹的方向就是hero脸的朝向 但是发射完子弹之后子弹的方向不是N
		case WD:                            //如果发射玩改成N的话 已经发出去的子弹就停在半空中了
			dir = 0;                        //该怎么让 发射完不管有没有子弹 至少让他是正面不动的效果 也就是Dir是4
			break;							//没事了↓
		case AS:
			dir = 1;						
			break;
		case SD:
			dir = 1;
			break;
		default:
			break;
		}
		return dir;
	}
	
}
