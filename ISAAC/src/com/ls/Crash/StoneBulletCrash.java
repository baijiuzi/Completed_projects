package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Stone;
import com.ls.Model.Bullet.Bullet;

public class StoneBulletCrash extends CrashDefine {

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Stone && obj2 instanceof Bullet){
			Stone enemy = (Stone) obj1;  
			Bullet bullet = (Bullet) obj2;
			if (obj1.getRect().intersects(obj2.getRect())) {
				bullet.setDead(true);
			}
			return true;
		}
		return false;
	}

}
