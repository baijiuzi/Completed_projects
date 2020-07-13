package com.ls.Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;

public class Background extends GameObject{
	public int xspeed = 0;
	public int yspeed = 0;
	static public int moveCount = 0;  //初始房间 没有移动的房间是0
	static public boolean move = true;
	
	public Background() {     //测试换一个地图英雄边框碰撞
		super(0, 0, StaticNum.Width*5, StaticNum.Height*4, 0);    //背景的位置xy不变  速度换房间的时候变  换房间随机图片
		try {
			this.img = ImageIO.read(new File("backgrand/BG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(img, x, y ,StaticNum.Width*5, StaticNum.Height*4,null);
		//System.out.println("画背景！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		CheckIsBg();
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub             //移动 进哪个门 背景向哪里移动
		//System.out.println(d+"  "+xspeed+"  "+yspeed+"   ("+x+","+y+")");
		if(d==Direction.W){         
			y+=yspeed;
			MainController.getController().tellHeroTeleport();      //设计：每次移动房间的时候计数器+1 按照计数器的数值分配Enemy
			moveCount++;
			move = true;
		}if(d==Direction.S){         
			y-=yspeed;
			MainController.getController().tellHeroTeleport();
			moveCount++;
			move = true;
		}if(d==Direction.A){         
			x+=xspeed;
			MainController.getController().tellHeroTeleport();
			moveCount++;
			move = true;
		}if(d==Direction.D){         //去右侧的房间
			x-=xspeed;
			MainController.getController().tellHeroTeleport();
			moveCount++;
			move = true;
		}
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub 
													  //角色检查边界的时候    可移动的范围 0，0 -- 320*2，218*2
		//System.out.println(d+"  "+xspeed+"  "+yspeed+"   ("+x+","+y+")");
		//地图检查边界 x 速度设为0，方向设为N
		//1.不能超出整张地图
		x = x<-StaticNum.Width*4?x = -StaticNum.Width*4:x;
		y = y<-StaticNum.Height*3?y = -StaticNum.Height*3:y;
		x = x<=0?x:0;
		y = y<=0?y:0;
		if(x%1024==0 && y%652==0){   //！！！！！！！！！不停：找xy加速度的最大公约数 我人傻了--  没事了 我分别设置了x的速度和y的速度
			xspeed = 0;
			yspeed = 0;
			d = Direction.N;
		}
	}
	
	public void CheckIsBg(){
		//2.地图内
		String[] Nbg = {"0-652","0-1304","0-1956","-20480","-40960","-1024-1956","-2048-1956","-2048-1304","-4096-1304","-4096-1956","-30720"};
		List<String> NoneBg = Arrays.asList(Nbg);
		String moved = "";
		if(d==Direction.W){         
			moved = x+""+(y+StaticNum.Height);
		}if(d==Direction.S){         
			moved = x+""+(y-StaticNum.Height);
			//System.out.println(moved+"       进到方向为S的检查边界！！！！！！！！！！！！！！！！！！！！！！！");
		}if(d==Direction.A){         
			moved = (x+StaticNum.Width)+""+y;
		}if(d==Direction.D){         
			moved = (x-StaticNum.Width)+""+y;
			//System.out.println(moved+"       进到方向为D的检查边界！！！！！！！！！！！！！！！！！！！！！！！");
		}
		if(d!=Direction.N && NoneBg.indexOf(moved)!=-1){
			moved = "";
			xspeed = 0;
			yspeed = 0;
			d = Direction.N;
			//System.out.println("停 不让背景Move()");
		}
	}
		
	
	
	public int getXspeed() {
		return xspeed;
	}

	public void setXspeed(int xspeed) {
		this.xspeed = xspeed;
	}

	public int getYspeed() {
		return yspeed;
	}

	public void setYspeed(int yspeed) {
		this.yspeed = yspeed;
	}
}
