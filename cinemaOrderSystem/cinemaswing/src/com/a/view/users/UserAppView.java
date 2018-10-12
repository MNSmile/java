package com.a.view.users;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAppView {
	
	public JFrame frame; //窗口类对象
	private JDesktopPane desktopPane;
	
	
	public void showAnyView(JInternalFrame jf) { 
		desktopPane.add(jf);
		//设置最小化
		jf.setIconifiable(true);
		//关闭窗口
		jf.setClosable(true);
		//让窗口类显示
		jf.setVisible(true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAppView window = new UserAppView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserAppView() { //构造方法
		initialize();  
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(600, 150, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的时候停止程序
		
		JMenuBar menuBar = new JMenuBar(); //菜单栏
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u8D2D\u7968\u7BA1\u7406"); //菜单列名1
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u5F71\u8BAF");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//影讯按钮事件
				
				VideoNewsView vnv = new VideoNewsView();
				
				showAnyView(vnv);
			}
		});
		
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("\u4E2A\u4EBA\u4E2D\u5FC3");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4E2A\u4EBA\u8D26\u53F7");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//个人账号按钮事件
				ShowPersonalAccountView spav = new ShowPersonalAccountView();
				showAnyView(spav);
				
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u8BA2\u5355\u7BA1\u7406");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//订单管理按钮事件
				
				ShowTicketView dialog = new ShowTicketView();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		menu_1.add(menuItem_2);
		
		desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER); 
		desktopPane.setLayout(null); //设置为绝对布局
		
	}
}
