package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Bullet.Bullet;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Bullet.HeroBullet_2;
import com.ls.Model.Enemy.Enemy;

public class EnemyHeroBullet_2Crash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Enemy && obj2 instanceof HeroBullet_2){
			Enemy enemy = (Enemy) obj1;  
			HeroBullet_2 bullet = (HeroBullet_2) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				enemy.setLife(enemy.getLife()-bullet.getLife());
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
