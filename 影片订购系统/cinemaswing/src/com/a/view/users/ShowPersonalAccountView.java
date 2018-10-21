package com.a.view.users;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.util.IdSave;

public class ShowPersonalAccountView extends JInternalFrame {
	private JTextField txtUserName;
	private JTextField txtUserAccount;
	private JTextField txtPwd;
	private JTextField txtBalance;
	private JTextField txtLevel;
	private JTextField textField;
	
	
	public void initTxt() {
		CinemaUsersService cus = new CinemaUsersServiceImpl();
		List<Map<String,Object>> findUserById = cus.findUserById(IdSave.userId);
		String userName = findUserById.get(0).get("NAME").toString();
		String Account = findUserById.get(0).get("UACCOUNT").toString();
		String pwd = findUserById.get(0).get("PASSWD").toString();
		String balance = findUserById.get(0).get("BALANCE").toString();
		String level = findUserById.get(0).get("LEVELS").toString();
		
		txtUserName.setText(userName);
		txtUserAccount.setText(Account);
		txtPwd.setText(pwd);
		txtBalance.setText(balance);
		txtLevel.setText(level);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPersonalAccountView frame = new ShowPersonalAccountView();
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
	public ShowPersonalAccountView() {
		setBounds(100, 100, 450, 386);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(15, 15, 81, 21);
		getContentPane().add(label);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(84, 12, 104, 27);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		txtUserName.setEditable(false);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(15, 70, 81, 21);
		getContentPane().add(label_1);
		
		txtUserAccount = new JTextField();
		txtUserAccount.setBounds(84, 67, 104, 27);
		getContentPane().add(txtUserAccount);
		txtUserAccount.setColumns(10);
		txtUserAccount.setEditable(false);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(15, 136, 81, 21);
		getContentPane().add(label_2);
		
		txtPwd = new JTextField();
		txtPwd.setBounds(84, 133, 104, 27);
		getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		txtPwd.setEditable(false);
		
		JLabel label_3 = new JLabel("\u4F59\u989D\uFF1A");
		label_3.setBounds(15, 203, 81, 21);
		getContentPane().add(label_3);
		
		txtBalance = new JTextField();
		txtBalance.setBounds(84, 200, 81, 27);
		getContentPane().add(txtBalance);
		txtBalance.setColumns(10);
		txtBalance.setEditable(false);
		
		JLabel label_4 = new JLabel("\u7B49\u7EA7\uFF1A");
		label_4.setBounds(15, 274, 81, 21);
		getContentPane().add(label_4);
		
		txtLevel = new JTextField();
		txtLevel.setBounds(84, 271, 104, 27);
		getContentPane().add(txtLevel);
		txtLevel.setColumns(10);
		txtLevel.setEditable(false);
		
		JButton button = new JButton("\u5145\u503C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//充值按钮事件
				
				int t = JOptionPane.showConfirmDialog(null, "是否充值？");
				if (t == 0) { //更新
					//取得用户余额
					CinemaUsersService cus = new CinemaUsersServiceImpl();
					List<Map<String,Object>> findUserById = cus.findUserById(IdSave.userId);

					Double balance = new Double(findUserById.get(0).get("BALANCE").toString());
					
					//封装到JavaBean
					CinemaUsers cu = new CinemaUsers();
					cu.setUserid(IdSave.userId);
					cu.setBalance(balance);
					
					
					RechargeView rv = new RechargeView(cu);
					
					rv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					rv.setModal(true);
					rv.setVisible(true);
					
					
				}
			}
		});
		button.setBounds(272, 224, 123, 29);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u66F4\u6539\u5BC6\u7801");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//更新密码按钮事件
				
				int t = JOptionPane.showConfirmDialog(null, "是否更改密码？");
				if (t == 0) { //更新
					//取得用户密码
					CinemaUsersService cus = new CinemaUsersServiceImpl();
					List<Map<String,Object>> findUserById = cus.findUserById(IdSave.userId);

					String passwd = findUserById.get(0).get("PASSWD").toString(); 

					//封装到JavaBean
					CinemaUsers cu = new CinemaUsers();
					cu.setUserid(IdSave.userId);
					cu.setPasswd(passwd);
					
					UpdateUserPwdView uup = new UpdateUserPwdView(cu);
					uup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					uup.setModal(true);
					uup.setVisible(true);
					
					
				}
			}
		});
		button_1.setBounds(272, 80, 123, 29);
		getContentPane().add(button_1);
		
		textField = new JTextField();
		textField.setText("\u5143");
		textField.setBounds(164, 200, 24, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		initTxt();
	}
}
