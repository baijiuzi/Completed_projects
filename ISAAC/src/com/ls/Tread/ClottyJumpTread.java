package com.ls.Tread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ClottyJumpTread extends Thread{
	
	Player playBgm;
	
	public ClottyJumpTread() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		try {
			playBgm = new Player(new FileInputStream("music/ClottyJump.mp3"));
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
