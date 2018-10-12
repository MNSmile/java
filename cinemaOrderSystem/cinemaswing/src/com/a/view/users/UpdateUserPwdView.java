package com.a.view.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import javax.swing.JPasswordField;

public class UpdateUserPwdView extends JDialog {
	private JTextField txtOldPwd;
	private String passwd;
	private JPasswordField txtNewPwd;
	private JPasswordField txtNewPwd2;
	
	
	public void initTxtInfo() {
		txtOldPwd.setText(passwd);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateUserPwdView dialog = new UpdateUserPwdView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateUserPwdView(CinemaUsers cinemausers) {
		setBounds(100, 100, 319, 337);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label.setBounds(15, 30, 81, 21);
		getContentPane().add(label);
		
		txtOldPwd = new JTextField();
		txtOldPwd.setBounds(102, 27, 117, 27);
		getContentPane().add(txtOldPwd);
		txtOldPwd.setColumns(10);
		txtOldPwd.setEditable(false);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_1.setBounds(15, 78, 81, 21);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_2.setBounds(15, 133, 101, 21);
		getContentPane().add(label_2);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//确认密码按钮事件
				
				
				String newPwd = txtNewPwd.getText();
				String newPwd1 = txtNewPwd2.getText();
				if (newPwd.equals(newPwd1)) {
					//更新用户密码
					cinemausers.setPasswd(newPwd);
					
					//转到service层
					CinemaUsersService cus = new CinemaUsersServiceImpl();
					int i = cus.updateUserInfoById(cinemausers);
					System.out.println(i);
					String message = i>0?"更新密码成功！":"更新密码失败！请联系系统管理员！";
					JOptionPane.showMessageDialog(null, message);
				} else {
					JOptionPane.showMessageDialog(null, "两次密码不同！");
				}
				
			}
		});
		button.setBounds(81, 202, 123, 29);
		getContentPane().add(button);
		
		txtNewPwd = new JPasswordField();
		txtNewPwd.setBounds(102, 75, 117, 27);
		getContentPane().add(txtNewPwd);
		
		txtNewPwd2 = new JPasswordField();
		txtNewPwd2.setBounds(102, 130, 117, 24);
		getContentPane().add(txtNewPwd2);
		
		this.passwd = cinemausers.getPasswd();
		initTxtInfo();
	}
}
