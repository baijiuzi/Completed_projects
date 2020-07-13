package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Boss.MengSiChuo;
import com.ls.Model.Enemy.Enemy;

public class HeroBossCrash extends CrashDefine{

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Hero && obj2 instanceof MengSiChuo){
			Hero hero = (Hero) obj1;
			MengSiChuo enemy = (MengSiChuo) obj2;                            //子类继承后 是不是instanceof Enemy?    可以!
			if (obj1.getRect().intersects(obj2.getRect())) {
				//hero.setLife(hero.getLife()-1);   碰撞不扣血
				
			}
			return true;
		}
		return false;
	}
}
