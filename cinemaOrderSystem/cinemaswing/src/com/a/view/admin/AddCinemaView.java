package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.Cinema;
import com.a.service.CinemaService;
import com.a.service.Impl.CinemaServiceImpl;

public class AddCinemaView extends JInternalFrame {
	private JTextField txtCinemaName;
	private JTextField txtAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCinemaView frame = new AddCinemaView();
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
	public AddCinemaView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F71\u9662\u540D\u79F0\uFF1A");
		label.setBounds(61, 36, 98, 21);
		getContentPane().add(label);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(149, 33, 148, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5F71\u9662\u5730\u5740\uFF1A");
		label_1.setBounds(61, 115, 98, 21);
		getContentPane().add(label_1);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(151, 112, 148, 27);
		getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加影院按钮事件
				
				//1.得到添加影院信息
				String cinemaName = txtCinemaName.getText();
				String cinemaAddress = txtAddress.getText();
				if (!"".equals(cinemaName)) {
					if (!"".equals(cinemaAddress)) {
						//2.封装进JavaBean
						Cinema cinema = new Cinema();
						cinema.setCinemaName(cinemaName);
						cinema.setAddress(cinemaAddress);
						//3.转到service层
						CinemaService cs = new CinemaServiceImpl();
						int i = cs.AddCinema(cinema);
						
						String message = i>0?"添加成功！":"添加失败！请联系系统管理员！";
						JOptionPane.showMessageDialog(null, message);
					} else {
						JOptionPane.showMessageDialog(null, "请填写影院地址！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请填入影院名称！");
				}
			}
		});
		button.setBounds(134, 187, 123, 29);
		getContentPane().add(button);

	}
}
