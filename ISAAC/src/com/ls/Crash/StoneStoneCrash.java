package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Stone;
import com.ls.Model.Enemy.Enemy;
import com.ls.Util.Direction;

public class StoneStoneCrash extends CrashDefine{

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Stone && obj2 instanceof Stone){
			Stone stone1 = (Stone) obj1;
			Stone stone2 = (Stone) obj2;                            //子类继承后 是不是instanceof Enemy?    可以!
			if (obj1.getRect().intersects(obj2.getRect())) {
				stone1.setDead(true);                        //石头之间的碰撞先等会
			}
			return true;
		}
		return false;
	}
}
