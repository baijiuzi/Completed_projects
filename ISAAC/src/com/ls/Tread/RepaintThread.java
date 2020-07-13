package com.ls.Tread;

import com.ls.View.MainPanel;

public class RepaintThread extends Thread{//设置帧数 1s24次刷新重绘
	
	private MainPanel pan;
	boolean isRun = true;
	
	public RepaintThread(MainPanel pan) {
		this.pan = pan;
	}
	
	@Override
	public void run() {
		while(isRun){
			pan.repaint();
			//System.out.println("重绘线程:面板重绘!");
			try {
				Thread.sleep(41);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void interrupt() {
		isRun = false;
	}
}
