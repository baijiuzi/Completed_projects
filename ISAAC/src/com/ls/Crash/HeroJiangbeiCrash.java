package com.ls.Crash;

import com.ls.Controler.MainController;
import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Buff.Jiangbei;
import com.ls.Tread.GetBuffTread;
import com.ls.View.LoginFrame;

public class HeroJiangbeiCrash extends CrashDefine{
	
	static int getBuffCount = 0;
	GetBuffTread gbth;
	
	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Hero && obj2 instanceof Jiangbei){
			Hero hero = (Hero) obj1;
			Jiangbei jiangbei = (Jiangbei) obj2;                           
			if (obj1.getRect().intersects(obj2.getRect())) {
				getBuffCount++;
				jiangbei.move = true;
				if (getBuffCount<40) {
					hero.isChange = true;
					gbth = new GetBuffTread();
					gbth.start();
				}else{
					jiangbei.setDead(true);
					MainController.getController().addScore(jiangbei.getLife());
					TotalCrash.Crashs.remove(10);
					System.out.println("奖杯死 移除检查 游戏结束！！！！！  " + hero.score);
					MainController.getController().updateRecord(LoginFrame.username,hero.score);
					MainController.getController().createEndFrame();
				}
			}
			return true;
		}
		return false;
	}
}
