package com.ls.Crash;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ls.Controler.MainController;
import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Buff.Halo;
import com.ls.Tread.GetBuffTread;

public class HeroHaloCrash extends CrashDefine{

	static int getBuffCount = 0;
	GetBuffTread gbth;
	
	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Hero && obj2 instanceof Halo){
			Hero hero = (Hero) obj1;
			Halo halo = (Halo) obj2;                            //子类继承后 是不是instanceof Enemy?    可以!
			if (obj1.getRect().intersects(obj2.getRect())) {
				getBuffCount++;
				//System.out.println(getBuffCount);
				halo.move = true;
				hero.getBuff = true;
				if (getBuffCount<40) {
					hero.isChange = true;
					//要是加声音的话就夹在这
					gbth = new GetBuffTread();
					gbth.start();
				}else {
					//移除检测
					hero.setLife(hero.getLife()+8);
					MainController.getController().addScore(halo.getLife());
					TotalCrash.Crashs.remove(6);
				}
			}
			return true;
		}
		return false;
	}
}
