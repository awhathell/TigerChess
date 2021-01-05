package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.stuScoreDao;
import com.jdatabc.util.DbUtil;
import com.jdatabc.util.StringUtil;
import com.model.StuScore;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class stuScAd extends JFrame {

	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField exam1;
	private JTextField exam2;
	private JTextField exam3;
	private JTextField exam4;
	private JButton button_1;
	private DbUtil dbUtil=new DbUtil();
	private stuScoreDao stuScDao=new stuScoreDao();
	private JLabel lblNewLabel;
	private JTextField fileAddress;
	private JButton addFromFile;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stuScAd frame = new stuScAd();
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
	public stuScAd() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(stuScAd.class.getResource("/images/car.png")));
		setTitle("\u5B66\u751F\u4FE1\u606F\u6DFB\u52A0");
		setBounds(100, 100, 640, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("\u5B66\u751FID");
		lblid.setIcon(new ImageIcon(stuScAd.class.getResource("/images/userName.png")));
		lblid.setBounds(71, 51, 72, 18);
		contentPane.add(lblid);
		
		idTxt = new JTextField();
		idTxt.setBounds(157, 50, 86, 24);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u79D1\u76EE\u4E00");
		label_1.setBounds(71, 151, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u79D1\u76EE\u4E8C");
		label_2.setBounds(71, 191, 72, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u79D1\u76EE\u4E09");
		label_3.setBounds(271, 151, 72, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u79D1\u76EE\u56DB");
		label_4.setBounds(271, 191, 72, 18);
		contentPane.add(label_4);
		
		exam1 = new JTextField();
		exam1.setBounds(157, 150, 86, 24);
		contentPane.add(exam1);
		exam1.setColumns(10);
		
		exam2 = new JTextField();
		exam2.setBounds(157, 190, 86, 24);
		contentPane.add(exam2);
		exam2.setColumns(10);
		
		exam3 = new JTextField();
		exam3.setBounds(346, 150, 86, 24);
		contentPane.add(exam3);
		exam3.setColumns(10);
		
		exam4 = new JTextField();
		exam4.setBounds(346, 190, 86, 24);
		contentPane.add(exam4);
		exam4.setColumns(10);
		
		button_1 = new JButton("\u6DFB\u52A0\u5B66\u5458\u6210\u7EE9");
		button_1.setIcon(new ImageIcon(stuScAd.class.getResource("/images/add.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stuScAddactionperformed(e);
			}
		});
		button_1.setBounds(456, 228, 155, 27);
		contentPane.add(button_1);
		
		lblNewLabel = new JLabel("\u4ECE\u6587\u4EF6\u4E2D\u8BFB\u53D6");
		lblNewLabel.setBounds(71, 285, 105, 18);
		contentPane.add(lblNewLabel);
		
		fileAddress = new JTextField();
		fileAddress.setText("w:\\\\inputTest.txt");
		fileAddress.setBounds(176, 281, 250, 24);
		contentPane.add(fileAddress);
		fileAddress.setColumns(10);
		
		addFromFile = new JButton("\u6DFB\u52A0\u6587\u4EF6\u6570\u636E");
		addFromFile.setIcon(new ImageIcon(stuScAd.class.getResource("/images/add.png")));
		addFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addFromFileactionperformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		addFromFile.setBounds(456, 307, 157, 27);
		this.setLocationRelativeTo(null);
		contentPane.add(addFromFile);
	}
	/**
	 * 从文件中读取学员成绩
	 * @param e
	 * @throws Exception 
	 */
	protected void addFromFileactionperformed(ActionEvent e) throws Exception {
		String address=new String(fileAddress.getText());
		Connection con=dbUtil.getCon();
		File file = new File(address);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "未找到文件！");
		}else{
			try{   
	            BufferedReader bufRead=new BufferedReader(new InputStreamReader(new FileInputStream(address)));   
	            String  str;   
	            while((str=bufRead.readLine())!=null){   
	                    int id,exam1,exam2,exam3,exam4;String name="";
	                    String []arrayStr=str.split("\\s+");
	                    id=Integer.valueOf(arrayStr[0]);
	                    name=arrayStr[1];
	                    exam1=Integer.valueOf(arrayStr[2]);
	                    exam2=Integer.valueOf(arrayStr[3]);
	                    exam3=Integer.valueOf(arrayStr[4]);
	                    exam4=Integer.valueOf(arrayStr[5]);
	                    StuScore score=new StuScore(id,name,exam1,exam2,exam3,exam4);
	                    if(stuScDao.addStuSc(con, score)==2){
	                    	System.out.println(arrayStr[0]+"学员信息已存在");
	                    }else if(stuScDao.addStuSc(con, score)==3){
	                    	System.out.println(arrayStr[0]+"学员还未注册");
	                    }
	            }
	        }catch(IOException ioe){
	             ioe.printStackTrace();
	        }
		}
		
		JOptionPane.showMessageDialog(null, "读取成功！");
		
	}

	/**
	 * 添加学员成绩
	 * @param e
	 */
	protected void stuScAddactionperformed(ActionEvent e) {
		
		if(StringUtil.isEmpty(this.idTxt.getText())){
			JOptionPane.showMessageDialog(null, "学员ID不能为空！");
			return;
		}
		if(StringUtil.isEmpty(this.exam1.getText())&&StringUtil.isEmpty(this.exam2.getText())&&StringUtil.isEmpty(this.exam3.getText())&&StringUtil.isEmpty(this.exam4.getText())){
			JOptionPane.showMessageDialog(null, "请至少输入一门成绩！");
			return;
		}
		int stuId=Integer.parseInt(this.idTxt.getText());
		int stuExam1=0,stuExam2=0,stuExam3=0,stuExam4=0;
		if(!StringUtil.isEmpty(this.exam1.getText()))
			stuExam1=Integer.parseInt(this.exam1.getText());
		if(!StringUtil.isEmpty(this.exam2.getText()))
			stuExam2=Integer.parseInt(this.exam2.getText());
		if(!StringUtil.isEmpty(this.exam3.getText()))
			stuExam3=Integer.parseInt(this.exam3.getText());
		if(!StringUtil.isEmpty(this.exam4.getText()))
			stuExam4=Integer.parseInt(this.exam4.getText());
		StuScore stuSc=new StuScore(stuId,null,stuExam1,stuExam2,stuExam3,stuExam4);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum=stuScoreDao.addStuSc(con, stuSc);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				this.resetValue();
			//	this.fillTable(new StuScore());
			}else if(modifyNum==3){
				JOptionPane.showMessageDialog(null, "该学员还未注册");
			}else if(modifyNum==2){
					JOptionPane.showMessageDialog(null, "该学员成绩信息已经存在");
			}else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		}catch(Exception e2){
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}
		
	}

	/**
	 * 添加学员方法
	 * @param e
	 */
/*	protected void stuAddactionperformed(ActionEvent e) {
		int stuId=Integer.parseInt(this.idTxt.getText());
		String stuName=this.nameTxt.getText();
		if(StringUtil.isEmpty(String.valueOf(stuId))||StringUtil.isEmpty(stuName)){
			JOptionPane.showMessageDialog(null, "学员ID和姓名不能为空！");
			return;
		}
		StuScore stuSc=new StuScore(stuId,stuName);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum=stuScoreDao.addStu(con, stuSc);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
			//	this.fillTable(new StuScore());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}*/

	private void resetValue() {
		this.idTxt.setText("");
		this.exam1.setText("");
		this.exam2.setText("");
		this.exam3.setText("");
		this.exam4.setText("");
	}

}
