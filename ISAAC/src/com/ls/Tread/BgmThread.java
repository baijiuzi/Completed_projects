package com.ls.Tread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BgmThread extends Thread{
	
	Player playBgm;
	boolean isRun = true;
	
	public BgmThread() {
		
	}
	
	@Override
	public void run() {
		while (isRun){
			try {
				playBgm = new Player(new FileInputStream("music/LoginBgm.mp3"));
				playBgm.play();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void interrupt() {
		playBgm.close();
		isRun = false;
	}
}
