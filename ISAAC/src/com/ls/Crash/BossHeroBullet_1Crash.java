package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Boss.MengSiChuo;
import com.ls.Model.Bullet.Bullet;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Enemy.Enemy;

public class BossHeroBullet_1Crash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof MengSiChuo && obj2 instanceof HeroBullet_1){
			MengSiChuo enemy = (MengSiChuo) obj1;  
			HeroBullet_1 bullet = (HeroBullet_1) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				enemy.setLife(enemy.getLife()-bullet.getLife());  //一次减3
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
