package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.a.pojo.Session;
import com.a.service.HallService;
import com.a.service.MovieService;
import com.a.service.SessionService;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.MovieServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.util.RegexUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlatoonView extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTextField txtSessionTime;
	private JTextField txtPrice;
	
	/**
	 *比较日期大小，返回1表示beforeTime加上duration在thisTime之前，0表示不可以 
	 * @param beforeTime 之前的时间
	 * @param thisTime 
	 * @param duration 时长
	 * @param cleaningTime 打扫卫生时间
	 * @return
	 */
	public static int comparaTime(String beforeTime, String thisTime, String duration, int cleaningTime) {
		Calendar nowTime = Calendar.getInstance();// 得到当前时间
		String str  = beforeTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			nowTime.setTime(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		} // 设置成这个时间
		//增加duration分钟
		nowTime.add(Calendar.MINUTE, cleaningTime+new Integer(duration)); 
		String b = thisTime;
		Date bt;
		try {
			bt = sdf.parse(sdf.format(nowTime.getTime()));
			Date et = sdf.parse(b);
			
			System.out.println("bt="+sdf.format(bt));
			System.out.println("et="+sdf.format(et));
			if (bt.before(et)) {
				return 1; //可以排片
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void initMovieTable() {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("影片编号");
		columnNames.add("影片名称");
		columnNames.add("影片时长");
		columnNames.add("影片类型");
		//将数据封装进另一个vector中
		
		MovieService ms = new MovieServiceImpl();
		Vector<Vector<String>> data = ms.findAllMovies();
		
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//将model添加到table上
		table.setModel(dm);
	}
	
	public void initHallTable() {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("影厅编号");
		columnNames.add("影厅名称");
		columnNames.add("影厅余座");
		columnNames.add("影院编号");
		columnNames.add("影院名称");
		//将数据封装进另一个vector中
		
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHall();
		
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//将model添加到table上
		table_1.setModel(dm);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlatoonView frame = new PlatoonView();
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
	public PlatoonView() {
		setBounds(80, 40, 715, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 36, 315, 245);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 36, 300, 245);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel label = new JLabel("\u4E0A\u6620\u65F6\u95F4:");
		label.setBounds(15, 296, 81, 21);
		getContentPane().add(label);
		
		txtSessionTime = new JTextField();
		txtSessionTime.setBounds(111, 293, 189, 27);
		getContentPane().add(txtSessionTime);
		txtSessionTime.setColumns(10);
		
		JButton button = new JButton("\u6392\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//排片按钮事件
				
				//1.得到电影ID，影院ID，影厅ID，容量（余座）
				//1.1如果表格未选中行，返回-1
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer movieId = new Integer(table.getValueAt(rowIndex, 0).toString());
					int rowIndex1 = table_1.getSelectedRow();
					if (rowIndex1 > -1) {
						Integer hallId = new Integer(table_1.getValueAt(rowIndex1, 0).toString());
						Integer cinemaId = new Integer(table_1.getValueAt(rowIndex1, 3).toString());
						String remain = table_1.getValueAt(rowIndex1, 2).toString();
						
						//2.得到要排片电影上映时间，单价
						String sessionTime = txtSessionTime.getText();
						String price = txtPrice.getText();
						
						//校验时间，单价
						if (RegexUtil.TimeRegex(sessionTime) == 1) {
							if (RegexUtil.balanceRegex(price) == 1) {
								if (!"".equals(sessionTime)) {
									if (!"".equals(price)) {
										//3.将所有信息封装
										Session s = new Session();
										s.setCid(cinemaId);
										s.setHid(hallId);
										s.setMovieId(movieId);
										s.setPrice(price);
										s.setRemain(remain);
										s.setSessionTime(sessionTime);
										
										SessionService ss = new SessionServiceImpl();
										
										
										//4.判断当前是否可以排片
										//4.1从场次表中查出你想排片的影厅的最后一场电影的时间b,如果有，则为时间最大值，没有则为0
										Map<String, Object> data = ss.findLatestMovieSessionTime(hallId, cinemaId);
										System.out.println(data);
										
										if (data!=null && !data.isEmpty() && data.get("MOVIEID") != null) {
											//4.2得到影片时长
											Integer movieid = new Integer(data.get("MOVIEID").toString());
											MovieService ms = new MovieServiceImpl();
											String duration = ms.findMovieDurationByMovieId(movieid);
											//4.3最后一场电影放映时间
											String movieTime = (String) data.get("SESSIONTIME");
											int t = comparaTime(movieTime, sessionTime, duration, 20);
											if (t > 0) {
												ss.addSession(s);
											}
											String s1 = "排片失败！"+" 上一场电影放映时间："+movieTime+" 时长："+duration+"分钟"+" 打扫卫生时间："+20+"分钟";
											String message = t>0?"排片成功！":s1;
											JOptionPane.showMessageDialog(null, message);
										} else {
											int k = ss.addSession(s);
											String message = k>0?"排片成功！":"插入失败请联系系统管理员！";
											JOptionPane.showMessageDialog(null, message);
										}
									} else {
										JOptionPane.showMessageDialog(null, "请填写单价！");
									}
								} else {
									JOptionPane.showMessageDialog(null, "请填写上映时间！");
								}
							} else {
								JOptionPane.showMessageDialog(null, "单价只能是数字，小数点后一位");
							}
						} else {
							JOptionPane.showMessageDialog(null, "时间格式 2018-09-09 10:10 \r\n 或者2019-9-9 9:5");
						}
					} else {
						JOptionPane.showMessageDialog(null, "请选择影院！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择电影！");
				}
			}
		});
		button.setBounds(273, 346, 123, 29);
		getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u5355\u4EF7\uFF1A");
		label_1.setBounds(368, 296, 81, 21);
		getContentPane().add(label_1);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(434, 293, 234, 27);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		initMovieTable();
		initHallTable();
	}

}
