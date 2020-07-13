package com.ls.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ls.Controler.MainController;
import com.ls.Tread.LogInRepaintThread;
import com.ls.Util.StaticNum;

public class MainFrame extends Basicframe{
	private static final long serialVersionUID = -1967681333688701529L;
	
	MainPanel pan = new MainPanel();
	
	public void init() {
		super.init(StaticNum.Width, StaticNum.Height);
		MainController.getController().init(pan);
	}
	
	@Override
	public void addPart() {
		//-----加入面板-----
		pan.setPreferredSize(new Dimension(StaticNum.Width-10, StaticNum.Height-10));//莫名其妙的问题：JFrame生成Jframe的时候大小不对
		pan.setFocusable(true);    //设计可作为焦点
		pan.setBackground(new Color(0,250,250));                                     //面板背景颜色 ！提示用
		this.add(pan);                                      
		
	}

	@Override
	public void addListen() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainController.getController().stopThread();                //关闭窗口 关闭线程
				//lgrpth.interrupt();
			}
		});
		pan.addKeyListener(new KeyAdapter() {                              //!!给面板加按键监听!!不是给this
			@Override
			public void keyPressed(KeyEvent e) {
				MainController.getController().keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				MainController.getController().keyRepressed(e);
			}
		});
	}

}
