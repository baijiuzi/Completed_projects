package com.ls.View;
import javax.swing.JFrame;
	/**
	 * 基础的居中的 外部窗口的模板父类!
	 * @author LQWQK
	 * 2020年4月18日 下午4:16:48
	 */
public  abstract class Basicframe extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected int width;
	protected int height;
		
	public void init(int width, int height){
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addPart();
		this.addListen();
		this.pack();
		this.setVisible(true);
	}
	
	public abstract void addPart();
	
	public abstract void addListen();
}

