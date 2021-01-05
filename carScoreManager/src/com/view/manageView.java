package com.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.stuScoreDao;
import com.jdatabc.util.DbUtil;
import com.jdatabc.util.StringUtil;
import com.model.StuScore;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class manageView extends JFrame {

	private JPanel contentPane;
	private JTable scTable;
	private DbUtil dbUtil=new DbUtil();
	private stuScoreDao stuScDao=new stuScoreDao();
	private JTextField stuChID;
	private JTextField stuChName;
	private JTextField stuChExam1;
	private JTextField stuChExam2;
	private JTextField stuChExam4;
	private JTextField stuChExam3;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					manageView frame = new manageView(1);
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
	public manageView(int i) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(manageView.class.getResource("/images/car.png")));
		setResizable(false);
		setTitle("\u9A7E\u6821\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 626);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u5B66\u5458\u6210\u7EE9\u7BA1\u7406");
		menu.setIcon(new ImageIcon(manageView.class.getResource("/images/userName.png")));
		menuBar.add(menu);
		
		JMenuItem menuItemAdd = new JMenuItem("\u5B66\u5458\u6210\u7EE9\u6DFB\u52A0");
		menuItemAdd.setIcon(new ImageIcon(manageView.class.getResource("/images/add.png")));
		
		menuItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stuAddactionPerformed(arg0);
			}
		});
		if(i==1)//是教练员
			menuItemAdd.setEnabled(false);
		menu.add(menuItemAdd);
		
		JMenuItem menuItemFind = new JMenuItem("\u5B66\u5458\u6210\u7EE9\u67E5\u8BE2");
		menuItemFind.setIcon(new ImageIcon(manageView.class.getResource("/images/search.png")));
		menuItemFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findStuactionPerformed(arg0);
			}
		});
		menu.add(menuItemFind);
		
		JMenuItem menuItemPass = new JMenuItem("\u901A\u8FC7\u7387\u67E5\u770B");
		menuItemPass.setIcon(new ImageIcon(manageView.class.getResource("/images/search.png")));
		menuItemPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pass pass=new Pass();
				pass.setVisible(true);
			}
		});
		if(i==1)//是教练员
			menuItemPass.setEnabled(false);
		menu.add(menuItemPass);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scTable = new JTable();
		scTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				stuScTableMousePressed(arg0);
			}
		});
		scrollPane.setViewportView(scTable);
		scTable.setFillsViewportHeight(true);
		scTable.setModel(new DefaultTableModel(
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
		scTable.getColumnModel().getColumn(0).setResizable(false);
		scTable.getColumnModel().getColumn(1).setResizable(false);
		scTable.getColumnModel().getColumn(2).setResizable(false);
		scTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		scTable.getColumnModel().getColumn(3).setResizable(false);
		scTable.getColumnModel().getColumn(3).setPreferredWidth(103);
		scTable.getColumnModel().getColumn(4).setResizable(false);
		scTable.getColumnModel().getColumn(4).setPreferredWidth(99);
		scTable.getColumnModel().getColumn(5).setResizable(false);
		scTable.getColumnModel().getColumn(5).setPreferredWidth(102);
		scTable.getColumnModel().getColumn(6).setResizable(false);
		scTable.getColumnModel().getColumn(6).setPreferredWidth(119);
		
		JPanel panel = new JPanel();
		
		JButton buttonUpdate = new JButton("\u5237\u65B0");
		buttonUpdate.setIcon(new ImageIcon(manageView.class.getResource("/images/reset.png")));
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updateactionperformed(e);
			}
		});
		
		JButton button = new JButton("\u7ED3\u679C\u4FDD\u5B58\u4E3A\u6587\u4EF6");
		button.setIcon(new ImageIcon(manageView.class.getResource("/images/edit.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveFileactionperformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		if(i==1)//是教练员
			button.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(120)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(button)
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(143)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(231, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(797, Short.MAX_VALUE)
					.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(buttonUpdate)
					.addContainerGap())
		);
		
		JLabel lblid = new JLabel("\u5B66\u751FID");
		lblid.setIcon(new ImageIcon(manageView.class.getResource("/images/userName.png")));
		lblid.setBounds(18, 28, 77, 18);
		
		stuChID = new JTextField();
		stuChID.setBounds(120, 27, 76, 24);
		stuChID.setEditable(false);
		stuChID.setColumns(10);
		panel.setLayout(null);
		panel.add(lblid);
		panel.add(stuChID);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D");
		label.setIcon(new ImageIcon(manageView.class.getResource("/images/userName.png")));
		label.setBounds(18, 69, 82, 18);
		panel.add(label);
		
		stuChName = new JTextField();
		stuChName.setBounds(120, 62, 76, 24);
		panel.add(stuChName);
		stuChName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u79D1\u76EE\u4E00");
		lblNewLabel.setBounds(245, 30, 45, 18);
		panel.add(lblNewLabel);
		
		stuChExam1 = new JTextField();
		stuChExam1.setBounds(300, 28, 45, 24);
		panel.add(stuChExam1);
		stuChExam1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u79D1\u76EE\u4E8C");
		label_1.setBounds(375, 30, 45, 18);
		panel.add(label_1);
		
		stuChExam2 = new JTextField();
		stuChExam2.setBounds(435, 28, 44, 24);
		panel.add(stuChExam2);
		stuChExam2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u79D1\u76EE\u4E09");
		label_2.setBounds(245, 67, 45, 18);
		panel.add(label_2);
		
		stuChExam3 = new JTextField();
		stuChExam3.setBounds(300, 63, 45, 24);
		panel.add(stuChExam3);
		stuChExam3.setColumns(10);
		
		JLabel label_3 = new JLabel("\u79D1\u76EE\u56DB");
		label_3.setBounds(375, 67, 45, 18);
		panel.add(label_3);
		
		stuChExam4 = new JTextField();
		stuChExam4.setBounds(435, 66, 44, 24);
		panel.add(stuChExam4);
		stuChExam4.setColumns(10);
		
		JButton buttonSet = new JButton("\u4FEE\u6539");
		buttonSet.setIcon(new ImageIcon(manageView.class.getResource("/images/edit.png")));
		buttonSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stuScUpdateActionEvent(arg0);
			}
		});
		buttonSet.setBounds(152, 115, 92, 27);
		panel.add(buttonSet);
		
		JButton buttonDe = new JButton("\u5220\u9664");
		buttonDe.setIcon(new ImageIcon(manageView.class.getResource("/images/delete.png")));
		buttonDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stuScDelactionPerformed(arg0);
			}
		});
		buttonDe.setBounds(330, 115, 93, 27);
		panel.add(buttonDe);
		contentPane.setLayout(gl_contentPane);
		if(i==1)//是教练员
			buttonDe.setEnabled(false);
		
		this.setLocationRelativeTo(null);
		this.fillTable(new StuScore());
		
		
		
	}
	/**
	 * 将结果保存为文件
	 * @param e
	 * @throws IOException 
	 */
	protected void saveFileactionperformed(ActionEvent e) throws IOException {		
		Connection con=null;
		FileWriter fileWriter=new FileWriter("w:\\Result.txt");
		try{
			String s=new String();
			con=dbUtil.getCon();
			ResultSet rs=stuScDao.allList(con, new StuScore());
			while(rs.next()){
				s=rs.getString("stuId")+" "+rs.getString("stuName")+" "+rs.getString("exam1")
				+" "+rs.getString("exam2")+" "+rs.getString("exam3")+" "+rs.getString("exam4");
				if(rs.getInt("getLicence")==0)
					s=s+" 否";
				else
					s=s+" 是";
				fileWriter.write(s+"\n");
			//	System.out.println("成功输入一条"+s);
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		fileWriter.flush();
		fileWriter.close();
		JOptionPane.showMessageDialog(null, "输出成功！");
	}

	/**
	 * 通过率
	 */
	/**
	 * 刷新列表
	 * @param e
	 */
	
	
	
	protected void Updateactionperformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.fillTable(new StuScore());
	}

	/**
	 * 删除学员成绩信息
	 * @param arg0
	 */
	protected void stuScDelactionPerformed(ActionEvent arg0) {
		int id=Integer.parseInt(stuChID.getText());
		if(StringUtil.isEmpty(String.valueOf(id))){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum=stuScoreDao.delete(con, id);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				this.resetValue();
				this.fillTable(new StuScore());
			}else{
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 修改学员成绩
	 * @param arg0
	 */
	protected void stuScUpdateActionEvent(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(stuChID.getText());
		String stuName=stuChName.getText();
		int stuExam1=Integer.parseInt(stuChExam1.getText());
		int stuExam2=Integer.parseInt(stuChExam2.getText());
		int stuExam3=Integer.parseInt(stuChExam3.getText());
		int stuExam4=Integer.parseInt(stuChExam4.getText());
		if(StringUtil.isEmpty(String.valueOf(id))){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(stuName)){
			JOptionPane.showMessageDialog(null, "学生姓名不能为空");
			return;
		}
		StuScore stuSc=new StuScore(id,stuName,stuExam1,stuExam2,stuExam3,stuExam4);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum=stuScoreDao.update(con, stuSc);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new StuScore());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
				System.out.println(modifyNum);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
			System.out.println("抛出异常");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValue() {
		this.stuChID.setText("");
		this.stuChExam1.setText("");
		this.stuChExam2.setText("");
		this.stuChExam3.setText("");
		this.stuChExam4.setText("");
		this.stuChName.setText("");
	}

	/**
	 * 选中学生成绩记录
	 * @param arg0
	 */
	protected void stuScTableMousePressed(MouseEvent arg0) {
		int row=scTable.getSelectedRow();
		stuChID.setText((String)scTable.getValueAt(row, 0));
		stuChExam1.setText((String)scTable.getValueAt(row, 2));
		stuChExam2.setText((String)scTable.getValueAt(row, 3));
		stuChExam3.setText((String)scTable.getValueAt(row, 4));
		stuChExam4.setText((String)scTable.getValueAt(row, 5));
		stuChName.setText((String)scTable.getValueAt(row, 1));
	
	}

	/**
	 * 查询学生成绩
	 * @param arg0
	 */
	protected void findStuactionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		StuScoreFind stufind=new StuScoreFind();
		stufind.setVisible(true);
	//	contentPane.add(stufind);
	}

	/**
	 * 添加学员考试信息
	 * @param arg0
	 */
	protected void stuAddactionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		stuScAd stuscAd=new stuScAd();
		stuscAd.setVisible(true);
	}

	
	
	

	private void fillTable(StuScore stusc){
		DefaultTableModel dtm=(DefaultTableModel) scTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=stuScDao.allList(con, stusc);
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
