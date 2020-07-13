package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Bullet.Bullet;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Enemy.Enemy;

public class EnemyHeroBullet_1Crash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Enemy && obj2 instanceof HeroBullet_1){
			Enemy enemy = (Enemy) obj1;  
			HeroBullet_1 bullet = (HeroBullet_1) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				enemy.setLife(enemy.getLife()-bullet.getLife());
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
