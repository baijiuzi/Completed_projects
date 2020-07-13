package com.ls.Controler;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.ls.Bean.Record;
import com.ls.Crash.TotalCrash;
import com.ls.Dao.RecordDao;
import com.ls.Model.Background;
import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Stone;
import com.ls.Model.Boss.MengSiChuo;
import com.ls.Model.Buff.Halo;
import com.ls.Model.Buff.Jiangbei;
import com.ls.Model.Enemy.Clotty;
import com.ls.Model.Enemy.Fly;
import com.ls.Tread.DispatcherThread;
import com.ls.Tread.RepaintThread;
import com.ls.Util.Direction;
import com.ls.Util.StaticNum;
import com.ls.View.EndFrame;
import com.ls.View.MainPanel;

public class MainController {
	private static MainController controller = new MainController();     //单例 唯一控制
	
	List<GameObject> objs = new ArrayList<GameObject>();    //所有物体都添加到列表
	RepaintThread rpth;
	DispatcherThread disth;
	Background bg;
	Hero hero;
/*	Fly fly;
	Clotty clotty;
	Stone stone;
	MengSiChuo mengsichuo;
	Halo halo;
	Jiangbei jiangbei;*/
	
	RecordDao rd = new RecordDao();
	
	private boolean up = false;      //子弹的方向
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	private boolean hw = false;       //英雄的方向
	private boolean hs = false;
	private boolean ha = false;
	private boolean hd = false;
	
	public void init(MainPanel pan){
		bg = new Background();
		hero = new Hero();
		/*fly = new Fly();
		clotty = new Clotty();
		stone = new Stone();
		mengsichuo = new MengSiChuo();
		halo = new Halo();
		jiangbei = new Jiangbei();*/
		rpth = new RepaintThread(pan);
		rpth.start();
		disth = new DispatcherThread();
		disth.start();
	}
	
	public void GameObjectPrint(Graphics2D g){
		//System.out.println("GameObjectPrint方法执行    List长度： "+objs.size());
		for (int i = 0; i < objs.size(); i++) {
			GameObject obj1 = objs.get(i);
			for (int j = 0; j < objs.size(); j++) {
				GameObject obj2 = objs.get(j);
				TotalCrash.crashObject(obj1, obj2);
			}
			obj1.PrintSelf(g);
		}
	}
	
	public void keyPressed(KeyEvent e){
		//System.out.println("调用控制层  按下按键！");
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_W:                //WASD控制角色
			hw = true;
			break;
		case KeyEvent.VK_S:
			hs = true;
			break;
		case KeyEvent.VK_A:
			ha = true;
			break;
		case KeyEvent.VK_D:
			hd = true;
			break;
			
		case KeyEvent.VK_UP:               //小键盘上下左右控制子弹发射
			up = true;
			hero.isFire(up);
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			hero.isFire(down);
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			hero.isFire(left);
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			hero.isFire(right);
			break;
		default:
			break;
		}
		tellHeroDirection();
	}
	
	public void keyRepressed(KeyEvent e){
		//System.out.println("调用控制层  松开按键！");
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_W:                //WASD控制角色
			hw = false;
			break;
		case KeyEvent.VK_S:
			hs = false;
			break;
		case KeyEvent.VK_A:
			ha = false;
			break;
		case KeyEvent.VK_D:
			hd = false;
			break;
		
		
		/*case KeyEvent.VK_I:
			bg.setD(Direction.W);         //背景测试用 直接赋值  
			bg.setYspeed(163);			  //IKJL控制背景房间上下左右
			break;
		case KeyEvent.VK_K:
			bg.setD(Direction.S);
			bg.setYspeed(163);
			break;
		case KeyEvent.VK_J:
			bg.setD(Direction.A);
			bg.setXspeed(128);
			break;
		case KeyEvent.VK_L:
			bg.setD(Direction.D);
			bg.setXspeed(128);
			break;*/
			
		case KeyEvent.VK_UP:               //小键盘上下左右控制子弹发射
			up = false;
			hero.isFire(up);
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			hero.isFire(down);
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			hero.isFire(left);
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			hero.isFire(right);
			break;
		default:
			break;
		}
		tellHeroDirection();
	}
	
	public void tellHeroDirection() {
		if(hw&&!ha&&!hs&&!hd){
			hero.setD(Direction.W);
		}if(!hw&&ha&&!hs&&!hd){
			hero.setD(Direction.A);
		}if(!hw&&!ha&&hs&&!hd){
			hero.setD(Direction.S);
		}if(!hw&&!ha&&!hs&&hd){
			hero.setD(Direction.D);
		}if(hw&&ha&&!hs&&!hd){
			hero.setD(Direction.WA);
		}if(hw&&!ha&&!hs&&hd){
			hero.setD(Direction.WD);
		}if(!hw&&ha&&hs&&!hd){
			hero.setD(Direction.AS);
		}if(!hw&&!ha&&hs&&hd){
			hero.setD(Direction.SD);
		}if(!hw&&!ha&&!hs&&!hd){
			hero.setD(Direction.N);
		}
	}
	
	public void tellHbulletDrection(GameObject obj) {
		if (up) {
			obj.setD(Direction.W);
		}if (down) {
			obj.setD(Direction.S);
		}if (left) {
			obj.setD(Direction.A);
		}if (right) {
			obj.setD(Direction.D);
		}
	}
	
	public void tellBgDrection(Direction di){
		bg.setD(di);
		bg.setYspeed(163);
		bg.setXspeed(256);
		//System.out.println("hero调用告诉背景方向     "+bg.getD());
	}
	

	public void tellHeroTeleport() {
		//System.out.println("告诉hero瞬移");
		if (hero.getX()>=StaticNum.Width/2-40 && hero.getX()<=StaticNum.Width/2-10 && hero.getY()==25) {//上面的中点
			hero.setY(StaticNum.Height-100-90);
			//System.out.println("hero瞬移到下边！！");
		}
		if (hero.getX()>=StaticNum.Width/2-40 && hero.getX()<=StaticNum.Width/2-10 && hero.getY()==StaticNum.Height-100-88) {//下面的中点
			hero.setY(30);
			//System.out.println("hero瞬移到上边！！");
		}
		if (hero.getX()==80 && hero.getY()<=StaticNum.Height/2-78 && hero.getY()>=StaticNum.Height/2-98) {//左面的中点
			hero.setX(StaticNum.Width-80-90);
			//System.out.println("hero瞬移到右边！！");
		}
		if (hero.getX()==StaticNum.Width-80-88 && hero.getY()<=StaticNum.Height/2-78 && hero.getY()>=StaticNum.Height/2-98) {//右面的中点
			hero.setX(85);
			//System.out.println("hero瞬移到左边！！");
		}
	}
	
	public int getHeroX() {
		return hero.getX();
	}
	
	public int getHeroY() {
		return hero.getY();
	}
	
	public void stopThread(){                           //停止线程
		rpth.interrupt();
		disth.interrupt();
	}
	
	public void addList(GameObject obj){
		if (objs.size()>=2) {
			objs.add(1, obj);       //为了让hero置顶显示
		}else {
			objs.add(obj);
		}
	}
	
	public void MengsichuoChangeList(GameObject obj){
		
		if (hero.getY()<=obj.getY()+88) {
			objs.remove(objs.indexOf(obj));
			objs.add(objs.indexOf(hero)+1, obj);
		}else {
			objs.remove(objs.indexOf(obj));
			objs.add(1, obj);
		}
		//System.out.println("萌死戳改变列表位置");
	}
	
	public boolean FlyDied(){
		for (int i = 0; i < objs.size(); i++) {
			if(objs.get(i).toString().startsWith("com.ls.Model.Enemy.Fly")){     //用来判断Fly死没死
				return false; 
			}
		}
		return true;
	}
	
	public void updateRecord(String username,int score) {
		Record r = new Record();
		r.setUsername(username);
		r.setScore(score);
		rd.updateRecord(r);
	}
	
	public void addScore(int life) {
		hero.score += life;
		System.out.println(hero.score);
	}
	
	public void createEndFrame() {                    //创建EndFrame    线程停止
		EndFrame ef = new EndFrame();
		ef.init();
		stopThread();
	}
	
	public void removeObj(GameObject obj){
		objs.remove(obj);
	}
	
	public int getListSize(){
		return objs.size();
	}
	
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	public static MainController getController() {
		return controller;
	}

	public static void setController(MainController controller) {
		MainController.controller = controller;
	}

	


	

	



	
	
	
}
