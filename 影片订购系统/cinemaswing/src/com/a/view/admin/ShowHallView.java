package com.a.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.a.service.HallService;
import com.a.service.Impl.HallServiceImpl;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowHallView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Integer cinemaId;
	
	public void initTable() {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("编号");
		columnNames.add("影厅名字");
		columnNames.add("容量");
		
		//将数据封装进另一个vector中
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHallByCinemaId(cinemaId);
		
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
					ShowHallView frame = new ShowHallView(null);
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
	public ShowHallView(Integer cinemaId) {
		//关闭此窗口，不影响主窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 500, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 398, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		this.cinemaId = cinemaId;
		initTable();
	}
}
