package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.stuScoreDao;
import com.jdatabc.util.DbUtil;
import com.model.StuScore;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class StuScoreFind extends JFrame {
	private JTextField findStuId;
	private JTable scTable;
	private DbUtil dbUtil=new DbUtil();
	private stuScoreDao stuScDao=new stuScoreDao();
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuScoreFind frame = new StuScoreFind();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public StuScoreFind() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StuScoreFind.class.getResource("/images/car.png")));
		setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		setBounds(100, 100, 640, 415);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 627, 0);
		getContentPane().add(label);
		
		JLabel lblid = new JLabel("\u5B66\u751FID");
		lblid.setIcon(new ImageIcon(StuScoreFind.class.getResource("/images/userName.png")));
		lblid.setBounds(156, 74, 72, 18);
		getContentPane().add(lblid);
		
		findStuId = new JTextField();
		findStuId.setBounds(238, 72, 86, 24);
		getContentPane().add(findStuId);
		findStuId.setColumns(10);
		
		JButton findStuBtn = new JButton("\u67E5\u8BE2");
		findStuBtn.setIcon(new ImageIcon(StuScoreFind.class.getResource("/images/search.png")));
		findStuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findStuBtactionPerformed(arg0);
			}
		});
		findStuBtn.setBounds(361, 71, 92, 27);
		getContentPane().add(findStuBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 122, 543, 217);
		getContentPane().add(scrollPane);
		
		scTable = new JTable();
		scrollPane.setViewportView(scTable);
		scTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u5B66\u751F\u59D3\u540D", "\u79D1\u76EE\u4E00", "\u79D1\u76EE\u4E8C", "\u79D1\u76EE\u4E09", "\u79D1\u76EE\u56DB", "\u662F\u5426\u83B7\u5F97\u9A7E\u7167"
			}
		));
		scTable.getColumnModel().getColumn(2).setResizable(false);
		scTable.getColumnModel().getColumn(5).setResizable(false);
		scTable.getColumnModel().getColumn(6).setResizable(false);
		scTable.getColumnModel().getColumn(6).setPreferredWidth(111);
		this.setLocationRelativeTo(null);
	}

	/**
	 * 通过输入学员id查询信息
	 * @param arg0
	 */
	protected void findStuBtactionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int stuid=Integer.parseInt(this.findStuId.getText());
		this.fillTable(new StuScore(stuid));
		
	}
	
	private void fillTable(StuScore stusc){
		DefaultTableModel dtm=(DefaultTableModel) scTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=stuScDao.list(con, stusc);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("stuId"));				
				v.add(rs.getString("stuName"));
				v.add(rs.getString("exam1"));
				v.add(rs.getString("exam2"));
				v.add(rs.getString("exam3"));
				v.add(rs.getString("exam4"));
				if(rs.getInt("getLicence")==0)
					v.add("否");
				else
					v.add("是");
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
