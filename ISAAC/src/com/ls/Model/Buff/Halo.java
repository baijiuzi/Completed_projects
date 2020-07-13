package com.ls.Model.Buff;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.GameObject;
import com.ls.Util.StaticNum;

public class Halo extends GameObject{
	
	public boolean move = false;
	private int heroX ;
	private int heroY ;

	public Halo() {
		super(StaticNum.Width/2-200, StaticNum.Height/2-44, 66, 66, 0);
		try {
			this.img = ImageIO.read(new File("Buff/Helo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.life = 20;
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(img, x, y, 66, 66, null);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		if (move) {
			heroX = MainController.getController().getHeroX();
			heroY = MainController.getController().getHeroY();
			
			x = heroX+10;
			y = heroY-44;
		}
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub
		
	}

}
