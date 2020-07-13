package com.ls.View;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ls.Util.StaticNum;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 4206783824984424030L;
	
	JButton Isaac = new JButton();
	JLabel username = new JLabel("Username:");
	JLabel password = new JLabel("Password:");
	JTextField us = new JTextField();
	JTextField pw = new JTextField();                     //封装为player对象
	JButton LogIn = new JButton();
	JButton Start = new JButton();
	
	static public boolean changeImg = false;
	static BufferedImage img;
	static BufferedImage LoginImg;
	static BufferedImage logoImg;
	static BufferedImage startImg;
	static {
		try {
			img = ImageIO.read(new File("Login/firstBG.png"));  //默认开始界面背景
			logoImg = ImageIO.read(new File("Login/logo.png"));  //登录logo
			LoginImg = ImageIO.read(new File("Login/LogIn.png"));  //登录/注册按钮  -->查询 更新
			startImg = ImageIO.read(new File("Login/Start.png"));  //开始按钮
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);                
		Graphics2D gg = (Graphics2D) g;
		if (changeImg) {                                  
			try {
				img = ImageIO.read(new File("Login/stratBG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    //如果控制层 按下空格 改变图片变成真 换图（换界面
			addPart();
			changeImg = false;
		}
		gg.drawImage(img, 0, 0, StaticNum.Width, StaticNum.Height, null);
		
		//System.out.println("自动调用？？？？？？？？");    //没被调用原因： paintComponent 和 paintComponents
	}
	
	public LoginPanel() {
		this.setLayout(null);
		//System.out.println("LoginPanel加入到MainFrame里");
	}
	
	public void addPart(){
		
		this.add(Isaac);
		Isaac.setBounds(151, 32, 722 , 157);
		Isaac.setBorderPainted(false);
		Isaac.setIcon(new ImageIcon(logoImg));
		Isaac.setContentAreaFilled(false);
		
		this.add(username);
		username.setFont(new Font("Papyrus", Font.BOLD, 30));
		username.setBounds(150, 220, 205, 69);
		
		this.add(us);
		us.setFont(new Font("幼圆", Font.BOLD, 25));
		us.setBounds(340, 220, 520, 69);
		us.setOpaque(false);
		
		this.add(password);
		password.setFont(new Font("Papyrus", Font.BOLD, 30));
		password.setBounds(150, 300, 205, 69);
		
		this.add(pw);
		pw.setFont(new Font("幼圆", Font.BOLD, 25));
		pw.setBounds(340, 300, 520, 69);
		pw.setOpaque(false);
		
		this.add(LogIn);
		LogIn.setBounds(200, 420, 189, 169);
		LogIn.setBorderPainted(false);
		LogIn.setIcon(new ImageIcon(LoginImg));
		LogIn.setContentAreaFilled(false);
		
		this.add(Start);
		Start.setBounds(600, 380, 216, 226);
		Start.setBorderPainted(false);
		Start.setIcon(new ImageIcon(startImg));
		Start.setContentAreaFilled(false);
	}

	public boolean checkUsText() {     //检查是否是英文
		String text = us.getText();
		for (int i = 0; i < text.length(); i++) {
			if ((text.charAt(i)>='a' && text.charAt(i)<='z') || (text.charAt(i)>='A' && text.charAt(i)<='Z') || (text.charAt(i)>='0' && text.charAt(i)<='9')) {
				
			}else {
				return false;
			}
		}
		return true;
	}

	public boolean checkPwText() {
		String text = pw.getText();
		for (int i = 0; i < text.length(); i++) {
			if ((text.charAt(i)>='a' && text.charAt(i)<='z') || (text.charAt(i)>='A' && text.charAt(i)<='Z') || (text.charAt(i)>='0' && text.charAt(i)<='9')) {
				
			}else {
				return false;
			}
		}
		return true;
	}
	
	public JButton getLogIn() {
		return LogIn;
	}

	public void setLogIn(JButton logIn) {
		LogIn = logIn;
	}

	public JButton getStart() {
		return Start;
	}

	public void setStart(JButton start) {
		Start = start;
	}

	public String getUsText() {
		return us.getText();
	}

	public String getPwText() {
		return pw.getText();
	}
	
	

	

}












