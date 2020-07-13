package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Boss.MengSiChuo;
import com.ls.Model.Bullet.Bullet;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Bullet.HeroBullet_2;
import com.ls.Model.Enemy.Enemy;

public class BossHeroBullet_2Crash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof MengSiChuo && obj2 instanceof HeroBullet_2){
			MengSiChuo enemy = (MengSiChuo) obj1;  
			HeroBullet_2 bullet = (HeroBullet_2) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				enemy.setLife(enemy.getLife()-bullet.getLife());   //一次减5
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
