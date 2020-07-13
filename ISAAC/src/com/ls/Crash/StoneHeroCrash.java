package com.ls.Crash;

import com.ls.Model.GameObject;
import com.ls.Model.Hero;
import com.ls.Model.Stone;
import com.ls.Model.Enemy.Enemy;
import com.ls.Util.Direction;

public class StoneHeroCrash extends CrashDefine{

	@Override
	public boolean crashObject(GameObject obj1, GameObject obj2) {
		if(obj1 instanceof Stone && obj2 instanceof Hero){
			Stone stone = (Stone) obj1;
			Hero hero = (Hero) obj2;                           
			if (obj1.getRect().intersects(obj2.getRect())) {  //如果有相交的话
				if (hero.getY()<stone.getY() && hero.getX()/2>=stone.getX() && hero.getX()/2<=stone.getX()+66) {//shang
					
				}
				
				
			}
			return true;
		}
		return false;
	}
}

//左     y+88>sy+6   y+88<sy+80-6    x+88<sx+80
//右     y+88>sy+6   y+88<sy+80-6    x>sx
//下     x+88>sx+6   x+88<sx+80-6    y+88>sy
//上     x+88>sx+6   x+88<sx+80-6    y+88<sy+80

//判断相对位置  四个象限
				/*if (hero.getY()+88>stone.getY()+6 && hero.getY()+88<stone.getY()+66-6 && hero.getX()>stone.getX() && (hero.getD()==Direction.A || hero.getD()==Direction.AS || hero.getD()==Direction.WA) ) {   //右边 判断jio
					hero.setD(Direction.N);
					hero.setX(hero.getX()+1);
					//hero.setX(stone.getX()+50);
					System.out.println("右");
				}
				if (hero.getY()+88>stone.getY()+6 && hero.getY()+88<stone.getY()+66-6 && hero.getX()+88<stone.getX()+66 && (hero.getD()==Direction.D || hero.getD()==Direction.WD || hero.getD()==Direction.SD)) {   //左边
					hero.setD(Direction.N);
					hero.setX(hero.getX()-1);
					//hero.setX(stone.getX()-70);
					System.out.println("左");
				}
				if (hero.getX()+88>stone.getX()+6 && hero.getX()+88<stone.getX()+66-6 && hero.getY()+88<stone.getY()+66 && (hero.getD()==Direction.S || hero.getD()==Direction.AS || hero.getD()==Direction.SD)) {
					hero.setD(Direction.N);
					hero.setY(hero.getY()-1);
					//hero.setY(stone.getY()-90);
					System.out.println("上");
				}
				if (hero.getX()+88>stone.getX()+6 && hero.getX()+88<stone.getX()+66-6 && hero.getY()+88>stone.getY() && (hero.getD()==Direction.W || hero.getD()==Direction.WA || hero.getD()==Direction.WD)) {
					hero.setD(Direction.N);
					hero.setY(hero.getY()+1);
					//hero.setY(stone.getY()-8);
					System.out.println("下");
				}*/