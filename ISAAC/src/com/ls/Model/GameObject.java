package com.ls.Model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ls.Controler.MainController;
import com.ls.Util.Direction;

public abstract class GameObject {//所有模型的父类 基础特征行为
	
	protected int x;//1.物体坐标
	protected int y;
	protected int w;//2.物体宽高
	protected int h;
	protected int speed;//3.移动速度
	protected BufferedImage img;//4.背景
	protected int life;//5.血量/buff++/子弹--/分数
	protected Direction d;//方向
	protected boolean dead = false;//是否死亡/消失  默认没消失
	

	//派发每个物体的重绘方法  Graphics2D g：画笔
	public abstract void PrintSelf(Graphics2D g);
	
	//移动 == 变化xy坐标 speed加减
	public abstract void Move();
	
	//检查是否越界
	public abstract void CheckBounds();
	
	//活动物体的有参  子类必添加数据 
	public GameObject(int x, int y, int w, int h, int speed) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.speed = speed;
		MainController.getController().addList(this);//调用添加方法（单例)
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Direction getD() {
		return d;
	}

	public void setD(Direction d) {
		this.d = d;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Rectangle getRect(){
		return new Rectangle(this.x,this.y,this.w,this.h);
	}
}










