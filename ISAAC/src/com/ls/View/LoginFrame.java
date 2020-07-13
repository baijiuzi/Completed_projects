package com.ls.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import com.ls.Bean.Player;
import com.ls.Controler.LoginController;
import com.ls.Tread.ClickTread;
import com.ls.Util.StaticNum;

public class LoginFrame extends Basicframe{
	private static final long serialVersionUID = -7366238963383833241L;
	
	LoginPanel logPan = new LoginPanel();
	private boolean flag;
	public static String username;
	
	
	
	public void init() {
		super.init(StaticNum.Width, StaticNum.Height);
		LoginController.getLogController().init(logPan);
	}
	
	@Override
	public void addPart() {
		logPan.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
		logPan.setFocusable(true);    //设计可作为焦点
		logPan.setBackground(new Color(0,0,250));
		this.add(logPan);
	}

	@Override
	public void addListen() {
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LoginController.getLogController().stopThread();                //关闭窗口 关闭线程
				//lgrpth.interrupt();
			}
		});
		logPan.addKeyListener(new KeyAdapter() {                              //!!给面板加按键监听!!不是给this
			@Override
			public void keyPressed(KeyEvent e) {
				LoginController.getLogController().keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				LoginController.getLogController().keyRepressed(e);
			}
		});
		logPan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("当鼠标点击面板的时候");    //面板上的组件不算
			}
		});
		logPan.getLogIn().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClickTread clth = new ClickTread();
				clth.start();
				
				System.out.println("当鼠标点击login注册登录的时候");    //Login的点击监听
				flag = true;
				
				//1.先检查输入的输入文本是不是英文？  或者在键盘输入监听里？？？？   LoginPanel checkUsText()
				if (logPan.checkUsText() && logPan.checkPwText()) {
					//1.封装us pw --> 创建一个player对象
					//2.控制层 关联 playerdao层  将封装好的player对象 查询 检查是否有 有；登陆成功  没有：注册并登录成功
					Player palyer = LoginController.getLogController().login(logPan.getUsText(),logPan.getPwText());
					
					if (palyer!=null) {  //如果player对象存在（用户名和密码都正确
						JOptionPane.showMessageDialog(logPan, "登录成功 ！");
						
						username = logPan.getUsText();
						//获取logPan.getUsText() 即 登录的用户名
					}else {  //查询用户名是否存在
						Player palyerUsernameExist = LoginController.getLogController().selectUsername(logPan.getUsText());
						
						if (palyerUsernameExist!=null) {  //如果用户名是否存在(用户名正确密码错误
							JOptionPane.showMessageDialog(logPan, "用户名正确密码错误 ！");
							flag = false;
						}else {  //用户名不存在  调用注册（insert）方法插入到数据库  创建新的对象
							LoginController.getLogController().insert(logPan.getUsText(),logPan.getPwText());
							JOptionPane.showMessageDialog(logPan, "注册并登录成功 ！");
							
							username = logPan.getUsText();
							//获取logPan.getUsText() 即 登录的用户名
						}
					}
				}else {
					flag = false;
					JOptionPane.showMessageDialog(logPan, "用户名和密码必须是英文 或 数字！");
				}
			}
		});
		logPan.getStart().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("当鼠标点击start开始游戏的时候");      //Start的点击监听
				
				ClickTread clth = new ClickTread();
				clth.start();
				
				//查看是否登录或注册成功
				if (flag) {  //登录或注册成功
					//把用户名和时间添加到record
					LoginController.getLogController().insertRecord(username,LoginController.getLogController().getSystemTime());
					
					//Logincontroller 方法 创建Mainframe对象 mf.init
					MainFrame mf = new MainFrame();
					mf.init();
					//关闭本（LoginFrame）窗口  --?
				}else {
					JOptionPane.showMessageDialog(logPan, "先登录或注册 ！");
				}
				
			}
		});
		
		
	}

}
