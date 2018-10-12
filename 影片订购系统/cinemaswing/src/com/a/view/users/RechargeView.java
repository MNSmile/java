package com.a.view.users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.Impl.CinemaUsersServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RechargeView extends JDialog {
	private JTextField txtBalance;
	private JTextField txtNewBalance;
	private Double balance;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RechargeView dialog = new RechargeView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RechargeView(CinemaUsers cinemausers) {
		setTitle("\u5145\u503C\u7BA1\u7406");
		setBounds(100, 100, 294, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4F59\u989D\uFF1A");
		label.setBounds(15, 30, 81, 21);
		getContentPane().add(label);
		
		txtBalance = new JTextField();
		txtBalance.setBounds(76, 27, 139, 27);
		getContentPane().add(txtBalance);
		txtBalance.setColumns(10);
		txtBalance.setEditable(false);
		
		JLabel label_1 = new JLabel("\u5145\u503C\uFF1A");
		label_1.setBounds(15, 85, 81, 21);
		getContentPane().add(label_1);
		
		txtNewBalance = new JTextField();
		txtNewBalance.setBounds(75, 82, 140, 27);
		getContentPane().add(txtNewBalance);
		txtNewBalance.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//充值确定按钮事件
				//余额更新
				Double oldBalance = new Double(txtBalance.getText());
				Double rechargeBalance = new Double(txtNewBalance.getText());
				Double newBalance = oldBalance + rechargeBalance;
				
				cinemausers.setBalance(newBalance);
				System.out.println("yes,there is RechargeView!");
				//转到Service层
				CinemaUsersService cus = new CinemaUsersServiceImpl();
				int i = cus.updateUserInfoById(cinemausers);
				
				String message = i>0?"充值成功！":"充值失败！请联系系统管理员！";
				JOptionPane.showMessageDialog(null, message);
				
				JOptionPane.showMessageDialog(null, "余额："+newBalance);
			}
		});
		button.setBounds(63, 156, 123, 29);
		getContentPane().add(button);
		
		this.balance = new Double(cinemausers.getBalance());
		txtBalance.setText(this.balance.toString());
	}

}
