package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Stone;
import com.ls.Model.Enemy.Enemy;
import com.ls.Util.Direction;

public class StoneEnemyCrash extends CrashDefine{

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Stone && obj2 instanceof Enemy){
			Stone stone = (Stone) obj1;
			Enemy enemy = (Enemy) obj2;                            //子类继承后 是不是instanceof Enemy?    可以!
			if (obj1.getRect().intersects(obj2.getRect())) {
				enemy.setD(Direction.N);
				//System.out.println("Stone Enemy 碰撞");
			}
			return true;
		}
		return false;
	}
}
