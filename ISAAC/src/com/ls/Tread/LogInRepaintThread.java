package com.ls.Tread;

import com.ls.View.LoginPanel;

public class LogInRepaintThread extends Thread{//设置帧数 1s24次刷新重绘
	
	private LoginPanel logPan;
	boolean isRun = true;
	
	public LogInRepaintThread(LoginPanel logPan) {
		this.logPan = logPan;
	}
	
	@Override
	public void run() {
		while(isRun){
			logPan.repaint();
			//System.out.println("登陆面板重绘线程:面板重绘!");
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
