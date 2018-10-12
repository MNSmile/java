package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.a.pojo.Cinema;
import com.a.service.CinemaService;
import com.a.service.HallService;
import com.a.service.Impl.CinemaServiceImpl;
import com.a.service.Impl.HallServiceImpl;

public class ShowCinemaView extends JInternalFrame {
	private JTextField txtCinemaName;
	private JTable table;
	
	
	public void initTable(Cinema c) {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("影院编号");
		columnNames.add("影院名称");
		columnNames.add("影院地址");
		
		//将数据封装进另一个vector中
		CinemaService cs = new CinemaServiceImpl();
		
		Vector data = cs.findCinema(c);
		
		
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//将model添加到table上
		table.setModel(dm);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowCinemaView frame = new ShowCinemaView();
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
	public ShowCinemaView() {
		setBounds(60, 20, 589, 494);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F71\u9662\u540D\u79F0\uFF1A");
		label.setBounds(40, 40, 101, 21);
		getContentPane().add(label);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(128, 37, 96, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//查询影院按钮事件
				
				//查询条件
				String cinemaname = txtCinemaName.getText();
				Cinema c = new Cinema();
				c.setCinemaName(cinemaname);
				//刷新数据
				initTable(c);
			}
		});
		
		button.setBounds(239, 36, 101, 29);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 100, 529, 201);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//table点击事件
				
				//获取数据
				int rowIndex = table.getSelectedRow();
				Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
				
			}
		});
		table.setBounds(296, 222, 1, 1);
		
		scrollPane.setViewportView(table);
		
		JButton button_2 = new JButton("\u6DFB\u52A0\u5F71\u5385");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//添加影厅按钮事件
				
				//得到影院ID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					//添加影厅窗口
					AddHallView ahv = new AddHallView(cinemaId);
					
					ahv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "请选择影院所在行！");
				}
			}
		});
		button_2.setBounds(366, 36, 113, 29);
		getContentPane().add(button_2);
		
		JButton button_1 = new JButton("\u5220\u9664\u5F71\u9662");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除影院按钮事件
				System.out.println("jfsl");
				//1.得到要删除影院ID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {

					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					
					//2.查询影院帐下是否有影厅
					HallService hs = new HallServiceImpl();
					Vector<Vector<String>> cinemaData = hs.findAllHallByCinemaId(cinemaId);
					
					System.out.println(cinemaData);
					if (cinemaData.size() == 0) {
						//2.1如果没有则直接执行删除影厅操作
						
						CinemaService cs = new CinemaServiceImpl();
						int i = cs.deleteCinema(cinemaId);
						String message = i>0?"成功删除影院！":"删除失败请联系系统管理员！";
						JOptionPane.showMessageDialog(null, message);
						
					} else {
						//2.2 如果有影厅则返回一个窗口并说明先删除影厅
						System.out.println("no");
						JOptionPane.showMessageDialog(null, "请先删除影厅！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择影院所在行！");
				}
			}
		});
		button_1.setBounds(40, 349, 123, 29);
		getContentPane().add(button_1);
		
		JButton button_3 = new JButton("\u5220\u9664\u5F71\u5385");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除影厅按钮事件
				
				//1.得到要删除影院ID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer cinemaId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					//2.调用删除影厅窗口
					DeleteHallView dhv = new DeleteHallView(cinemaId);
					
					//关闭窗口，不影响主窗口，已经再 DeleteHallView 构造方法中声明的话此处不需要
					//dhv.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
					
					dhv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "请选择影院所在行！");
				}
			}
		});
		button_3.setBounds(356, 349, 123, 29);
		getContentPane().add(button_3);
		
		Cinema c = new Cinema();
		initTable(c);
	}
}
