package com.ls.View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ls.Bean.Record;
import com.ls.Dao.RecordDao;
import com.ls.Util.StaticNum;

public class EndFrame extends Basicframe{
	private static final long serialVersionUID = 3283071122160505012L;
	
	JPanel epan = new JPanel();
	JScrollPane jspan;
	JTable jt;
	
	public void init() {
		// TODO Auto-generated method stub
		super.init(StaticNum.Width, StaticNum.Height);
	}

	@Override
	public void addPart() {
		epan.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
		epan.setFocusable(true);    //设计可作为焦点
		//epan.setBackground(new Color(0,0,250));
		this.add(epan);
		
		RecordDao rd = new RecordDao();
		List<Record> records = rd.selectAll();               //把每一行加入到records里面了
		//要把 Record 对象转成 Vector<String>
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		
		Vector<String> columnNames = new Vector<>();
		columnNames.add("rid");
		columnNames.add("username");
		columnNames.add("date");
		columnNames.add("score");
		
		for (int i = 0; i < records.size(); i++) {          //把每一行转移到rowData里面了
			rowData.add(new Vector<String>());
			rowData.get(i).add(records.get(i).getRid()+"");
			rowData.get(i).add(records.get(i).getUsername()+"");
			rowData.get(i).add(records.get(i).getDate()+"");
			rowData.get(i).add(records.get(i).getScore()+"");
			//System.out.println(rowData.toString());
		}
		jt = new JTable(rowData, columnNames);
		jt.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
		
		
		jspan = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspan.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
		epan.add(jspan);
		
	}

	@Override
	public void addListen() {
		// TODO Auto-generated method stub
		
	}
	
	/*class EndPanel extends JPanel{
		public EndPanel() {
			addJtable();
		}
		
		public void addJtable(){
			RecordDao rd = new RecordDao();
			
			List<Record> records = rd.selectAll();               //把每一行加入到records里面了
			//要把 Record 对象转成 Vector<String>
			Vector<Vector<String>> rowData = new Vector<Vector<String>>();
			
			Vector<String> columnNames = new Vector<>();
			columnNames.add("rid");
			columnNames.add("username");
			columnNames.add("date");
			columnNames.add("score");
			
			for (int i = 0; i < records.size(); i++) {          //把每一行转移到rowData里面了
				rowData.add(new Vector<String>());
				rowData.get(i).add(records.get(i).getRid()+"");
				rowData.get(i).add(records.get(i).getUsername()+"");
				rowData.get(i).add(records.get(i).getDate()+"");
				rowData.get(i).add(records.get(i).getScore()+"");
				//System.out.println(rowData.toString());
			}
			
			jspan.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
			epan.add(jspan);
			
			JTable jt = new JTable(rowData, columnNames);
			jt.setPreferredSize(new Dimension(StaticNum.Width, StaticNum.Height));
			
			jspan.add(jt);
		}
	}*/
	
}
