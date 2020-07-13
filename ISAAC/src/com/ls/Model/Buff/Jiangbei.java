package com.ls.Model.Buff;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.GameObject;
import com.ls.Util.StaticNum;

public class Jiangbei extends GameObject{
	
	public boolean move = false;
	private int heroX ;
	private int heroY ;

	public Jiangbei() {
		super(StaticNum.Width/2, StaticNum.Height/2-44, 32*2, 64*2, 0);
		try {
			this.img = ImageIO.read(new File("Buff/jiangbei.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(img, x, y, 32*2, 64*2, null);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		if (move) {
			heroX = MainController.getController().getHeroX();
			heroY = MainController.getController().getHeroY();
			
			x = heroX+12;
			y = heroY-80;
			
			move = false;
		}
	}

	@Override
	public void CheckBounds() {
		if(dead){
			MainController.getController().removeObj(this);
		}
	}

}
