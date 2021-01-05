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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.stuScoreDao;
import com.jdatabc.util.DbUtil;
import com.model.StuScore;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class Pass extends JFrame {

	private JPanel contentPane;
	private JTextField allPass;
	private JTextField exam1Pass;
	private JTextField exam2Pass;
	private JTextField exam3Pass;
	private JTextField exam4Pass;
	private DbUtil dbUtil=new DbUtil();
	private stuScoreDao stuScDao=new stuScoreDao();
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pass frame = new Pass();
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
	public Pass() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pass.class.getResource("/images/car.png")));
		setTitle("\u5B66\u5458\u901A\u8FC7\u7387\u67E5\u770B");
		setBounds(100, 100, 372, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "\u901A\u8FC7\u7387", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("\u603B\u4F53\uFF1A");
		label.setBounds(9, 33, 47, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u79D1\u76EE\u4E00\uFF1A");
		label_1.setBounds(9, 73, 60, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u79D1\u76EE\u4E8C\uFF1A");
		label_2.setBounds(9, 103, 61, 18);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u79D1\u76EE\u4E09\uFF1A");
		label_3.setBounds(9, 133, 73, 18);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u79D1\u76EE\u56DB\uFF1A");
		label_4.setBounds(9, 163, 66, 18);
		panel.add(label_4);
		
		allPass = new JTextField();
		allPass.setEditable(false);
		allPass.setColumns(10);
		allPass.setBounds(86, 32, 61, 24);
		panel.add(allPass);
		
		exam1Pass = new JTextField();
		exam1Pass.setEditable(false);
		exam1Pass.setColumns(10);
		exam1Pass.setBounds(86, 72, 61, 24);
		panel.add(exam1Pass);
		
		exam2Pass = new JTextField();
		exam2Pass.setEditable(false);
		exam2Pass.setColumns(10);
		exam2Pass.setBounds(86, 102, 61, 24);
		panel.add(exam2Pass);
		
		exam3Pass = new JTextField();
		exam3Pass.setEditable(false);
		exam3Pass.setColumns(10);
		exam3Pass.setBounds(86, 132, 61, 24);
		panel.add(exam3Pass);
		
		exam4Pass = new JTextField();
		exam4Pass.setEditable(false);
		exam4Pass.setColumns(10);
		exam4Pass.setBounds(86, 162, 60, 24);
		panel.add(exam4Pass);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(73)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
		this.fillPass();
	}

/**
 * 计算通过率
 */
	public void fillPass(){		
		Connection con=null;
		double allNum=0,exam1=0,exam2=0,exam3=0,exam4=0,passnum=0;
		try{
			con=dbUtil.getCon();
			ResultSet rs=stuScDao.allList(con, new StuScore());
			while(rs.next()){
				allNum++;
			//	System.out.println(Integer.parseInt(rs.getString("exam2")));
				if(rs.getInt("getLicence")==1){
					passnum++;exam2++;exam3++;exam4++;exam1++;
				}
				else{	
					if(Integer.parseInt(rs.getString("exam1"))>90)
						exam1++; 
					if(Integer.parseInt(rs.getString("exam2"))>90)
						exam2++;
					if(Integer.parseInt(rs.getString("exam3"))>90)
						exam3++;
					if(Integer.parseInt(rs.getString("exam4"))>90)
						exam4++;
				}
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
		allPass.setText(String.format("%.2f", passnum/allNum*100)+"%");
		exam1Pass.setText(String.format("%.2f", exam1/allNum*100)+"%");
		exam2Pass.setText(String.format("%.2f", exam2/allNum*100)+"%");
		exam3Pass.setText(String.format("%.2f", exam3/allNum*100)+"%");
		exam4Pass.setText(String.format("%.2f", exam4/allNum*100)+"%");
		System.out.println(exam1);
		
	}
}