package com.ls.Crash;

import java.util.ArrayList;

import com.ls.Model.GameObject;

public class TotalCrash{

	static ArrayList<CrashDefine> Crashs = new ArrayList<>();
	static{
		Crashs.add(new BossBullet_1HeroBullet_1Crash());
		Crashs.add(new BossHeroBullet_1Crash());
		Crashs.add(new EnemyHeroBullet_1Crash());
		Crashs.add(new HeroBossBullet_1Crash());
		Crashs.add(new HeroBossBullet_2Crash());
		Crashs.add(new HeroBossCrash());
		Crashs.add(new HeroHaloCrash());
		Crashs.add(new HeroClottyBullet_1Crash());
		Crashs.add(new HeroEnemyCrash());
		Crashs.add(new HeroFlyBullet_1Crash());
		Crashs.add(new HeroJiangbeiCrash());
		Crashs.add(new StoneBulletCrash());
		Crashs.add(new StoneEnemyCrash());
		//Crashs.add(new StoneHeroCrash());
		//Crashs.add(new StoneStoneCrash());
		Crashs.add(new BossBullet_1HeroBullet_2Crash());
		Crashs.add(new BossHeroBullet_2Crash());
		Crashs.add(new EnemyHeroBullet_2Crash());              // 问题： 加上碰撞之后子弹会设出不来     
		
	}
	
	public static boolean crashObject(GameObject obj1, GameObject obj2) {
		for (int i = 0; i < Crashs.size(); i++) {
			if (Crashs.get(i).crashObject(obj1, obj2)) {
				return true;
			}
		}
		return false;
	}

}
