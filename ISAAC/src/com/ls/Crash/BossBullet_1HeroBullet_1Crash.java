package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Boss.MengSiChuo;
import com.ls.Model.Bullet.BossBullet_1;
import com.ls.Model.Bullet.Bullet;
import com.ls.Model.Bullet.HeroBullet_1;
import com.ls.Model.Enemy.Enemy;

public class BossBullet_1HeroBullet_1Crash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof BossBullet_1 && obj2 instanceof HeroBullet_1){
			BossBullet_1 Bbullet = (BossBullet_1) obj1;  
			HeroBullet_1 Hbullet = (HeroBullet_1) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				Bbullet.setDead(true);
				Hbullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
