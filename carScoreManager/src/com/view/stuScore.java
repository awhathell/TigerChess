package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.stuScoreDao;
import com.jdatabc.util.DbUtil;
import com.model.StuScore;

import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class stuScore extends JFrame {

	private JPanel contentPane;
	private JTable stuScTable;
	private DbUtil dbUtil=new DbUtil();
	private stuScoreDao stuScDao=new stuScoreDao();
	private String userName;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		String s=new String("ming");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stuScore frame = new stuScore(s);
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
	public stuScore(String userName) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(stuScore.class.getResource("/images/car.png")));
		this.userName=userName;
		setTitle("\u5B66\u5458\u4FE1\u606F");
		setBounds(100, 100, 730,510 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(175, Short.MAX_VALUE))
		);
		
		stuScTable = new JTable();
		scrollPane.setViewportView(stuScTable);
		stuScTable.setFillsViewportHeight(true);
		stuScTable.setColumnSelectionAllowed(true);
		stuScTable.setCellSelectionEnabled(true);
		stuScTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u5458\u7F16\u53F7", "\u5B66\u5458\u59D3\u540D", "\u79D1\u76EE\u4E00\u6210\u7EE9", "\u79D1\u76EE\u4E8C\u6210\u7EE9", "\u79D1\u76EE\u4E09\u6210\u7EE9", "\u79D1\u76EE\u56DB\u6210\u7EE9", "\u662F\u5426\u53D6\u5F97\u9A7E\u7167"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		stuScTable.getColumnModel().getColumn(0).setResizable(false);
		stuScTable.getColumnModel().getColumn(1).setResizable(false);
		stuScTable.getColumnModel().getColumn(2).setResizable(false);
		stuScTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		stuScTable.getColumnModel().getColumn(3).setResizable(false);
		stuScTable.getColumnModel().getColumn(3).setPreferredWidth(103);
		stuScTable.getColumnModel().getColumn(4).setResizable(false);
		stuScTable.getColumnModel().getColumn(4).setPreferredWidth(99);
		stuScTable.getColumnModel().getColumn(5).setResizable(false);
		stuScTable.getColumnModel().getColumn(5).setPreferredWidth(102);
		stuScTable.getColumnModel().getColumn(6).setResizable(false);
		stuScTable.getColumnModel().getColumn(6).setPreferredWidth(119);
		contentPane.setLayout(gl_contentPane);
		
		
		this.setLocationRelativeTo(null);
		this.fillTable(new StuScore(userName));
	}
	
	
	private void fillTable(StuScore stusc){
		DefaultTableModel dtm=(DefaultTableModel) stuScTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=stuScDao.selfList(con, stusc);
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
