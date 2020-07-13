package com.ls.Controler;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ls.Bean.Player;
import com.ls.Bean.Record;
import com.ls.Dao.PlayerDao;
import com.ls.Dao.RecordDao;
import com.ls.Tread.BgmThread;
import com.ls.Tread.LogInRepaintThread;
import com.ls.View.LoginPanel;

public class LoginController {
	
	private static LoginController logController = new LoginController();  //单例
	
	PlayerDao pd = new PlayerDao();
	RecordDao rd = new RecordDao();
	
	LogInRepaintThread lgrpth;
	BgmThread bgmth;
	private boolean flag = true;
	
	public void init(LoginPanel logPan){
		lgrpth = new LogInRepaintThread(logPan);
		lgrpth.start();
		bgmth = new BgmThread();
		bgmth.start();
	}
	
	public void keyPressed(KeyEvent e) {      //按下
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_SPACE:
			System.out.println("按下空格！！！！  换一个界面  但是下一次按空格的话又会调用一遍这个方法 设置一个flag让方法只能调用一次 x 不用方法了");
			//就直接换一个logPan的背景 给logPan添加组件
			if (flag) {
				LoginPanel.changeImg  = true;      //这里让LoginPanel的方法只调用了一次
				flag = false;
			}
			
			break;

		default:
			break;
		}
		
	}
	
	public void keyRepressed(KeyEvent e) {    //松开
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_1:
			
			break;

		default:
			break;
		}
		
	}
	
	public Player login(String usText, String pwText) {
		//System.out.println("封装了几次？？？？？");
		Player p = new Player();
		p.setUsername(usText);
		p.setPassword(pwText);
		return pd.login(p);
	}
	
	public void insert(String usText, String pwText) {
		Player p = new Player();
		p.setUsername(usText);
		p.setPassword(pwText);
		pd.insert(p);
	}

	public Player selectUsername(String usText) {
		Player p = new Player();
		p.setUsername(usText);
		return pd.selectUsername(p);
	}
	
	public void insertRecord(String username,Date date) {
		Record r = new Record();
		r.setUsername(username);
		r.setDate(date);
		rd.insertRecord(r);
	}
	
	public void stopThread() {
		lgrpth.interrupt();
		bgmth.interrupt();
	}
	
	public Date getSystemTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		try {
			return simpleDateFormat.parse(simpleDateFormat.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static LoginController getLogController() {
		return logController;
	}

	public static void setLogController(LoginController logController) {
		LoginController.logController = logController;
	}

	

	

	
	











	












	

}
