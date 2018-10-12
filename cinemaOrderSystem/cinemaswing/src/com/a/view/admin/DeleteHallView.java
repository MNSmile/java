package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.a.service.HallService;
import com.a.service.MovieService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.MovieServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;

public class DeleteHallView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Integer cinemaId;
	
	public void initTable() {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("编号");
		columnNames.add("名字");
		columnNames.add("容量");
		
		//将数据封装进另一个vector中
		HallService hs = new HallServiceImpl();
		Vector<Vector<String>> data = hs.findAllHallByCinemaId(cinemaId);
		
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		
		//将model添加到table上
		table.setModel(dm);
	}
	
	/*public int compareTime(String time) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(sdf.format(date));
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date bt;
		try {
			bt = sdf1.parse(sdf.format(date));
			Date et=sdf1.parse(time); 
			if (bt.before(et)){ 
			   
				System.out.println("不能删除影厅！");
				return 1;
			}else{ 
				System.out.println("可以删除影厅");
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		} 
	}*/
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteHallView frame = new DeleteHallView(1);
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
	public DeleteHallView(Integer cinemaId) {
		setTitle("\u5220\u9664\u5F71\u5385");
		//关闭窗口，不影响主窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 31, 398, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除影厅按钮事件
				
				//1.得到要删除影厅ID
				int rowIndex = table.getSelectedRow();
				if (rowIndex > -1) {
					Integer hallId = new Integer(table.getValueAt(rowIndex, 0).toString());
					
					//2.判断该影厅是否有已经排好的片且没放映
					SessionService ss = new SessionServiceImpl();
					Map<String, Object> data = ss.findLatestMovieSessionTime(hallId, cinemaId);
					
					//2.1如果有，则弹出窗口说明不能删除影厅，有已经排好片的电影
					if (data != null && !data.isEmpty()) {
						//2.1比对影片放映（结束）时间，
						//2.1.1得到最后一场电影放映时间
						String lastTime = data.get("SESSIONTIME").toString();
						System.out.println("放映时间："+lastTime);
						//2.1.2电影ID---> 时长
						Integer movieid = new Integer(data.get("MOVIEID").toString());
						System.out.println("movieId:"+movieid);
						
						MovieService ms = new MovieServiceImpl();
						String duration = ms.findMovieDurationByMovieId(movieid);
						System.out.println("duration:"+duration);
						//取得现在时间：
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String nativeTime = sdf.format(date);
						
						System.out.println(nativeTime);
						
						//i=0 代表lastTime+duration > nativeTime, 即还有未上映的电影
						int i = PlatoonView.comparaTime(lastTime, nativeTime, duration, 0);
						
						System.out.println(i);
						
						if (i == 1) {
							//2.1.1如果已经放映，删除影厅
							HallService hs = new HallServiceImpl();
							int t = hs.deleteHallById(hallId);
							String message = t>0?"成功删除影厅！":"删除失败请联系系统管理员！";
							JOptionPane.showMessageDialog(null, message);
						
						} else {
							//2.1.2不能删除，取消操作
							
							//看是否有人订票 sid -> tickets
							TicketService ts = new TicketServiceImpl();
							Integer sessionid = new Integer(data.get("SESSIONSID").toString());
							
							List<Map<String, Object>> ticketsList = ts.findTicketsBySessionid(sessionid);
							
							if (ticketsList != null && !ticketsList.isEmpty()) {
								JOptionPane.showMessageDialog(null, "该影厅下还有未放映完的影片！\r\n且已有人购票了！不能删除");
								
							} else {
								int j = JOptionPane.showConfirmDialog(null, "该影厅下还有未放映完的影片！\r\n  但还没人购票！\r\n  是否继续删除！");
								if (j == 0) {
									HallService hs = new HallServiceImpl();
									int t = hs.deleteHallById(hallId);
									String message = t>0?"成功删除影厅！":"删除失败请联系系统管理员！";
									JOptionPane.showMessageDialog(null, message);
								}
							}
						}
					} else {
						//2.2如果没有，则直接执行删除影厅方法，将状态置为0
						HallService hs = new HallServiceImpl();
						int t = hs.deleteHallById(hallId);
						String message = t>0?"成功删除影厅！":"删除失败请联系系统管理员！";
						JOptionPane.showMessageDialog(null, message);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择影厅所在行！");
				}
			}
		});
		button.setBounds(146, 279, 123, 29);
		contentPane.add(button);
		
		this.cinemaId = cinemaId;
		initTable();
	}

}
