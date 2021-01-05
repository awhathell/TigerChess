package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import com.dao.*;
import com.jdatabc.util.*;
import com.model.*;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class loginOn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserTxt;
	private JTextField pwTxt;
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	private mgDao mgDao=new mgDao();
	private coachDao coachDao=new coachDao();
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton mgRbt = new JRadioButton("\u7BA1\u7406\u5458");
	public JRadioButton coachRbt = new JRadioButton("\u6559\u7EC3\u5458");
	public JRadioButton stuRbt = new JRadioButton("\u5B66\u751F");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginOn frame = new loginOn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginOn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(loginOn.class.getResource("/images/car.png")));
		setResizable(false);
		setTitle("\u9A7E\u6821\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u9A7E\u6821\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		label.setIcon(new ImageIcon(loginOn.class.getResource("/images/car.png")));
		label.setBounds(173, 53, 157, 18);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D");
		label_1.setIcon(new ImageIcon(loginOn.class.getResource("/images/userName.png")));
		label_1.setBounds(115, 92, 66, 18);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setIcon(new ImageIcon(loginOn.class.getResource("/images/password.png")));
		label_2.setBounds(115, 126, 51, 18);
		
		UserTxt = new JTextField();
		UserTxt.setBounds(242, 89, 118, 24);
		UserTxt.setColumns(10);
		
		pwTxt = new JTextField();
		pwTxt.setBounds(242, 123, 118, 24);
		pwTxt.setColumns(10);
		
		
		
		JButton loginBt = new JButton("\u767B\u5F55");
		loginBt.setIcon(new ImageIcon(loginOn.class.getResource("/images/login.png")));
		loginBt.setBounds(97, 203, 84, 27);
		loginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);
			}
		});
		
		JButton resetBt = new JButton("\u91CD\u7F6E");
		resetBt.setIcon(new ImageIcon(loginOn.class.getResource("/images/reset.png")));
		resetBt.setBounds(317, 203, 85, 27);
		resetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetActionPerformed(arg0);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(label_1);
		contentPane.add(label_2);
		contentPane.add(pwTxt);
		contentPane.add(UserTxt);
		contentPane.add(loginBt);
		contentPane.add(resetBt);
		contentPane.add(label);
		
		
		buttonGroup.add(mgRbt);
		mgRbt.setBounds(118, 156, 80, 27);
		contentPane.add(mgRbt);
		
		
		buttonGroup.add(coachRbt);
		coachRbt.setBounds(204, 156, 73, 27);
		contentPane.add(coachRbt);
		
		buttonGroup.add(stuRbt);
		stuRbt.setSelected(true);
		stuRbt.setBounds(297, 156, 63, 27);
		contentPane.add(stuRbt);
		
		JButton addUserBt = new JButton("\u6CE8\u518C");
		addUserBt.setIcon(new ImageIcon(loginOn.class.getResource("/images/add.png")));
		addUserBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUseractionperformed(e);
			}
		});
		addUserBt.setBounds(214, 203, 83, 27);
		contentPane.add(addUserBt);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 用户注册
	 * @param e
	 */
	protected void addUseractionperformed(ActionEvent e) {
		String userName=this.UserTxt.getText();
		String password=new String(this.pwTxt.getText());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		/**
		 * 依据不同Rbt被选择的情况创建不同的数据库连接对象
		 */
		Connection con=null;
		try {
			con=dbUtil.getCon();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(mgRbt.isSelected()){//注册管理员
			mg mguser=new mg(userName,password);
			mg currentUser;
			try {
				if(mgDao.addUser(con, mguser)==1){
					dispose();
					new manageView(2).setVisible(true);
					JOptionPane.showMessageDialog(null, "注册成功");
				}else if(mgDao.addUser(con, mguser)==2){
					JOptionPane.showMessageDialog(null, "用户已注册");
				}else{
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}else if(coachRbt.isSelected()){
			coach couser=new coach(userName,password);
			coach currentUser;
			try {
				if(coachDao.addUser(con, couser)==1){
					dispose();
					new manageView(1).setVisible(true);
					JOptionPane.showMessageDialog(null, "注册成功");
				}else if(coachDao.addUser(con, couser)==2){
					JOptionPane.showMessageDialog(null, "用户已注册");
				}else{
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}else{
			User user=new User(userName,password);
			User currentUser;
			try {
				if(userDao.addUser(con, user)==1){
					dispose();
					new stuScore(userName).setVisible(true);
					JOptionPane.showMessageDialog(null, "注册成功");
				}else if(userDao.addUser(con, user)==2){
					JOptionPane.showMessageDialog(null, "用户已注册");
				}else{
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}		
		
}

	protected void resetActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		this.UserTxt.setText("");
		this.pwTxt.setText("");
		
	}

	protected void loginActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String userName=this.UserTxt.getText();
		String password=new String(this.pwTxt.getText());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		/**
		 * 依据不同Rbt被选择的情况创建不同的数据库连接对象
		 */
		Connection con=null;
		try {
			con=dbUtil.getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mgRbt.isSelected()){
			mg mguser=new mg(userName,password);
			mg currentUser;
			try {
				currentUser = mgDao.login(con, mguser);
				if(currentUser!=null){
					dispose();
					new manageView(2).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(coachRbt.isSelected()){
			coach couser=new coach(userName,password);
			coach currentUser;
			try {
				currentUser = coachDao.login(con, couser);
				if(currentUser!=null){
					dispose();
					new manageView(1).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			User user=new User(userName,password);
			User currentUser;
			try {
				currentUser = userDao.login(con, user);
				if(currentUser!=null){
					dispose();
					new stuScore(userName).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
