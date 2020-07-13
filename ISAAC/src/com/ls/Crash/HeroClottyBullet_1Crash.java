package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Bullet.ClottyBullet_1;
import com.ls.Model.Enemy.Enemy;
import com.ls.Tread.HeroHurtTread;

public class HeroClottyBullet_1Crash extends CrashDefine{

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Hero && obj2 instanceof ClottyBullet_1){
			Hero hero = (Hero) obj1;
			ClottyBullet_1 bullet = (ClottyBullet_1) obj2;                            //子类继承后 是不是instanceof Enemy?    可以!
			if (obj1.getRect().intersects(obj2.getRect())) {
				hero.setLife(hero.getLife()-bullet.getLife());
				HeroHurtTread hhth = new HeroHurtTread();
				hhth.start();
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}
}
