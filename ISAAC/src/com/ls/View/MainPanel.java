package com.ls.View;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.ls.Controler.MainController;

public class MainPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {  //画笔重绘
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		MainController.getController().GameObjectPrint(gg);
		
		//System.out.println("调用了吗？？？？？？？？");    调用了
	}
	
	public MainPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
	}
}
