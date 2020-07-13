package com.ls.Model;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Util.StaticNum;

public class Stone extends GameObject{

	public Stone() {
		super(new Random().nextInt(StaticNum.Width-2*(StaticNum.xborder+88+66))+StaticNum.xborder+88, new Random().nextInt(StaticNum.Height-2*(StaticNum.yborder+88+66))+StaticNum.yborder+88, 66, 66, 0);
		try {
			this.img = ImageIO.read(new File("stone/stone.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void PrintSelf(Graphics2D g) {
		g.drawImage(img, x, y, 66, 66, null);
		Move();
		CheckBounds();
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CheckBounds() {
		// TODO Auto-generated method stub
		if (dead) {
			MainController.getController().removeObj(this);
		}
	}

}
