package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;

public class ShowUserInfoView extends JInternalFrame {
	private JTextField txtUserName;
	private JTextField txtAccount;
	private JTable table;
	private JTextField txtUserName1;
	private JTextField txtBanlance1;
	private JTextField txtLevel;
	
	/**
	 * 隐藏表格列
	 * @param table
	 * @param column
	 */
	private void hideTableColumn(JTable table,int column) {
		//隐藏列，当不能取值
		/*TableColumnModel tcm = table.getColumnModel();
		TableColumn tc = tcm.getColumn(column);
		tcm.removeColumn(tc);*/
		
		//隐藏列可以取值
		TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}
	
	/**
	 * 查询用户信息并放在表格中
	 */
	public void initTable(String name, String account) {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("编号");
		columnNames.add("姓名");
		columnNames.add("账号");
		columnNames.add("等级");
		columnNames.add("余额");
		columnNames.add("密码");
		columnNames.add("状态");
		
		//将数据放在另一个vector上
		CinemaUsersService cs = new CinemaUsersServiceImpl();
		Vector<Vector> findUserInfo = cs.findUserInfo(name, account);
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(findUserInfo, columnNames);
		//将model添加到table上
		table.setModel(dm);
		//隐藏列
		hideTableColumn(table, 6);
		hideTableColumn(table, 5);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUserInfoView frame = new ShowUserInfoView();
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
	public ShowUserInfoView() {
		setTitle("\u7528\u6237\u4FE1\u606F\u67E5\u8BE2");
		setBounds(10, 10, 848, 578);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(31, 37, 81, 21);
		getContentPane().add(label);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(77, 34, 80, 27);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(195, 40, 81, 21);
		getContentPane().add(label_1);
		
		txtAccount = new JTextField();
		txtAccount.setBounds(242, 37, 80, 27);
		getContentPane().add(txtAccount);
		txtAccount.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//查询按钮事件
				
				//得到填入数据
				String userName = txtUserName.getText();
				String account = txtAccount.getText();
				//更新查询结果
				initTable(userName, account);
			}
		});
		button.setBounds(343, 33, 81, 29);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 101, 714, 299);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//table点击事件
				
				//获取数据
				int rowIndex = table.getSelectedRow();
				Integer userid = new Integer(table.getValueAt(rowIndex, 0).toString());
				String  username = (String) table.getValueAt(rowIndex, 1);
				String account = (String) table.getValueAt(rowIndex, 2);
				String level = (String) table.getValueAt(rowIndex, 3);
				Double balance = new Double(table.getValueAt(rowIndex, 4).toString());
				String pwd = (String) table.getValueAt(rowIndex, 5);
				String states = (String) table.getValueAt(rowIndex, 6);
				
				
				//把需要修改的放在文本框中
				txtUserName1.setText(username);
				txtBanlance1.setText(balance.toString());
				txtLevel.setText(level);
				
			}
		});
		table.setBounds(50, 100, 10, 1);
		
		//将表格放在滚动盘中
		scrollPane.setViewportView(table);
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//删除按钮事件
				
				int t = JOptionPane.showConfirmDialog(null, "是否删除");
				if (t == 0) { //0表示删除，1表示不删，2表示取消，-1表示未选列
					//1.收集数据
					//1.1得到要删除行行号
					int rowIndex = table.getSelectedRow();
					//1.1.1 如果rowIndex = 1 代表未选择行
					if (rowIndex > -1) {
						//1.2得到要删除行用户id
						Integer userid = new Integer(table.getValueAt(rowIndex, 0).toString());
						//将id传入service
						CinemaUsersService cs = new CinemaUsersServiceImpl();
						int i = cs.deleteUserById(userid);
						
						String message = i>0?"删除成功!":"删除失败，请联系系统管理员！";
						JOptionPane.showMessageDialog(null,message);
						//删除之后刷新表格数据
						String name = txtUserName.getText();
						String account = txtAccount.getText();
						//让表格重新加载数据
						initTable(name, account);
					} else {
						JOptionPane.showMessageDialog(null, "请选择要删除数据所在行！");
					}
				}
			}
		});
		button_1.setBounds(439, 33, 81, 29);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u66F4\u65B0");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//更新按钮事件
				int t = JOptionPane.showConfirmDialog(null, "是否更新？");
				
				if (t == 0) {
					//更新数据
					int rowIndex = table.getSelectedRow();
					//如果rowIndex = 1 代表未选择行
					if (rowIndex > -1) {
						Integer userid = new Integer((String)table.getValueAt(rowIndex, 0));
						//String  username = (String) table.getValueAt(rowIndex, 1);
						String account = (String) table.getValueAt(rowIndex, 2);
						//String level = (String) table.getValueAt(rowIndex, 3);
						//Double balance = new Double((String)table.getValueAt(rowIndex, 4));
						String pwd = (String) table.getValueAt(rowIndex, 5);
						String states = (String) table.getValueAt(rowIndex, 6);
						
						//从修改的文本框中得到修改数据
						String username = txtUserName1.getText();
						Double balance = new Double(txtBanlance1.getText());
						String level = txtLevel.getText();
						
						//将信息封装进Javabean中
						CinemaUsers c = new CinemaUsers();
						c.setUserid(userid);
						c.setName(username);
						c.setUaccount(account);
						c.setBalance(balance);
						c.setLevels(level);
						c.setPasswd(pwd);
						c.setStates(states);
						c.setStatus("1");
						
						//将JavaBean传给service
						CinemaUsersService cs = new CinemaUsersServiceImpl();
					
						int i = cs.updateUserInfoById(c);
						//根据service返回信息给用户提示
						String masseage = i>0?"更新成功！":"更新失败！请联系系统管理员！";
						JOptionPane.showMessageDialog(null, masseage);
						//更新之后刷新数据
						String name = txtUserName.getText();
						String account1 = txtAccount.getText();
						//让表格重新加载数据
						initTable(name, account1);
					} else {
						JOptionPane.showMessageDialog(null, "请选择要更新数据所在行！");
					}
				}
			}
		});
		button_2.setBounds(535, 33, 81, 29);
		getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("\u59D3\u540D\uFF1A");
		label_2.setBounds(91, 434, 66, 21);
		getContentPane().add(label_2);
		
		txtUserName1 = new JTextField();
		txtUserName1.setBounds(142, 431, 96, 27);
		getContentPane().add(txtUserName1);
		txtUserName1.setColumns(10);
		
		txtBanlance1 = new JTextField();
		txtBanlance1.setColumns(10);
		txtBanlance1.setBounds(328, 428, 96, 27);
		getContentPane().add(txtBanlance1);
		
		JLabel label_3 = new JLabel("\u4F59\u989D\uFF1A");
		label_3.setBounds(277, 431, 66, 21);
		getContentPane().add(label_3);
		
		txtLevel = new JTextField();
		txtLevel.setColumns(10);
		txtLevel.setBounds(503, 428, 96, 27);
		getContentPane().add(txtLevel);
		
		JLabel label_4 = new JLabel("\u7B49\u7EA7\uFF1A");
		label_4.setBounds(452, 431, 66, 21);
		getContentPane().add(label_4);
		
		JButton button_3 = new JButton("\u521D\u59CB\u5316\u5BC6\u7801");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//初始化密码按钮事件
				//得到数据
				int t = JOptionPane.showConfirmDialog(null, "是否初始化密码？");
				if (t == 0) {
					int rowIndex = table.getSelectedRow();
					if (rowIndex > -1) {
						Integer userid = new Integer((String)table.getValueAt(rowIndex, 0));
						CinemaUsersService cs = new CinemaUsersServiceImpl();
						int i = cs.updateUserPwdById(userid);
						System.out.println(i);
						String message = i>0?"初始化密码成功！":"初始化失败请联系系统管理员！";
						JOptionPane.showMessageDialog(null, message);
						//更新之后刷新数据
						String name = txtUserName.getText();
						String account1 = txtAccount.getText();
						//让表格重新加载数据
						initTable(name, account1);
					} else {
						JOptionPane.showMessageDialog(null, "请选择要初始化密码数据所在行！");
					}
				}
			}
		});
		button_3.setBounds(633, 33, 128, 29);
		getContentPane().add(button_3);
		
		initTable(null, null); //初始化
	}
}
