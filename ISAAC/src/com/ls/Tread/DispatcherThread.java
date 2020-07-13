package com.ls.Tread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.ls.Model.Background;

public class DispatcherThread extends Thread{
	
	ArrayList<String> config = new ArrayList<>();
	String line = "";
	private boolean isRun = true;

	
	public DispatcherThread() {
		//System.out.println("调用分发线程！！！");
		try(InputStream in = DispatcherThread.class.getClassLoader().getResourceAsStream("1.levelEnemy");  //输入字节流
			BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
			
			while((line = br.readLine()) != null){
				//System.out.println("读入的一行   "+line);
				config.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(isRun){
			
			try {
				Thread.sleep(41);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println("Background.moveCount！！！: "+Background.moveCount);
			
			if(Background.move){
				//System.out.println("当背景移动时！！！");
				for (int i = 0; i < config.size(); i++) {              //循环每行 是否生成    生物
					String[] split = config.get(i).split("=");
					//System.out.println("行数   "+ i);
					if(Background.moveCount == Integer.parseInt(split[0])){
						//System.out.println( "地图运动    "+ Background.moveCount +"   当地图移动   "+split[0]+"   时");
						for (int j = 1; j <= Integer.parseInt(split[2]); j++) {
							try {
								Class.forName(split[1]).newInstance();
								//System.out.println("循环！！！生成！！！");
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
						Background.move = false;
					}
				}
			}
		}
	}
	
	@Override
	public void interrupt() {
		isRun = false;
	}
}
