package com.a.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AdminManagerView {

	public JFrame frame;
	private JDesktopPane desktopPane;
	/**
	 * 显示窗口以及最小化、删除窗口
	 * @param jf
	 */
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
					AdminManagerView window = new AdminManagerView();
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
	public AdminManagerView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(600, 150, 894, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) { 
				//添加用户按钮事件
				AddUserView av = new AddUserView();
				showAnyView(av);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u67E5\u8BE2\u7528\u6237");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//查询用户按钮事件
				ShowUserInfoView sv = new ShowUserInfoView();
				showAnyView(sv);
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u5F71\u9662\u7BA1\u7406");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u67E5\u8BE2\u5F71\u9662");
		
		menuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//查询影院按钮事件
				
				//查询窗口
				ShowCinemaView sv = new ShowCinemaView();
				
				showAnyView(sv);
			}
		});
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_2 = new JMenuItem("\u6DFB\u52A0\u5F71\u9662");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//添加影院按钮事件
				
				AddCinemaView acv = new AddCinemaView();
				
				showAnyView(acv);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("\u5F71\u7247\u7BA1\u7406");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("\u6DFB\u52A0\u5F71\u7247");
		menuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//添加影片按钮事件
				
				AddMovieView amv = new AddMovieView();
				//设置窗口可见
				showAnyView(amv);
			}
		});
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u6392\u7247");
		menuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//排片
				PlatoonView pv = new PlatoonView();
				
				showAnyView(pv);
			}
		});
		menu_2.add(menuItem_5);
		
		desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		//添加标题
		frame.setTitle("影院管理后台");
	}
}
