package com.a.view.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.service.CinemaUsersService;
import com.a.service.HallService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.service.Impl.HallServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;
import com.a.util.IdSave;

public class ShoppingTicketView extends JDialog {
	private String sessionid;
	private JTextField txtCinemaName;
	private JTextField txtHallName;
	private JTextField txtMovieName;
	private JTextField txtSesstionTime;
	private String price;
	private String hallId;
	
	/**用户购买的票**/
	private Set ticketSet = new HashSet();
	
	private void initTxt() {
		SessionService ss = new SessionServiceImpl();
		Map<String, Object> movieMap = ss.findMovieInfoBySessionId(this.sessionid);
		
		//给文本赋值
		txtCinemaName.setText(movieMap.get("CINEMANAME").toString());
		txtHallName.setText(movieMap.get("HALLNAME").toString());
		txtMovieName.setText(movieMap.get("MOVIENAME").toString());
		txtSesstionTime.setText(movieMap.get("SESSIONTIME").toString());
		
		//单价
		this.price = (String) movieMap.get("PRICE");
		//影厅ID，用于选座时查找影厅容量
		this.hallId = (String) movieMap.get("HALLID");
	}
	
	private void initSeat() {
		//1.查找某一影院某一电影已经卖出的座位，由sessionID查找
		SessionService ss = new SessionServiceImpl();
		Set<String> seatSet = ss.findSeatSetBySessionId(sessionid);
		int count = seatSet.size(); //已选座位个数
		//System.out.println("已选座位个数:"+count);
		
		//查询影院影厅容量 sessionid-->hid-->capacity
		HallService hs = new HallServiceImpl();
		String Hallcapacity = hs.findHallCapacityById(hallId);
		
		int n = new Integer(Hallcapacity);
		//System.out.println("影厅容量："+n);
		int s = 1;
		for (int y = 0; y < n/5; y++) {
			for (int x = 0; x < 5; x++) {
				JCheckBox CheckBox = new JCheckBox(s + "");
				CheckBox.setBounds(11+x*100, 158+y*50, 45, 29);
				
				getContentPane().add(CheckBox);
				
				seatSet.add(s+"");
				if (seatSet.size() == count) {  //说明此座位号被选过
					CheckBox.setSelected(true); //设置被选中了
					CheckBox.setEnabled(false); //设置不能再次被选
				} else {
					seatSet.remove(s+""); //说明此座位没有被选过，先将其移除，以免影响选座
				}
				
				CheckBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean flag = CheckBox.isSelected();
						if (flag) {
							ticketSet.add(CheckBox.getText());
						} else {
							ticketSet.remove(CheckBox.getText());
						}
						//System.out.println(CheckBox.isSelected()+"----"+CheckBox.getText());
						//System.out.println(ticketSet.toString());
					}
				});
				s++;
			}
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShoppingTicketView dialog = new ShoppingTicketView("1");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShoppingTicketView(String sessionid) {
		setTitle("\u9009\u5EA7");
		setBounds(100, 100, 528, 579);
		
		this.sessionid = sessionid;

		getContentPane().setLayout(null);
		
		txtCinemaName = new JTextField();
		txtCinemaName.setBounds(15, 27, 96, 27);
		getContentPane().add(txtCinemaName);
		txtCinemaName.setColumns(10);
		
		txtHallName = new JTextField();
		txtHallName.setBounds(126, 27, 130, 27);
		getContentPane().add(txtHallName);
		txtHallName.setColumns(10);
		
		txtMovieName = new JTextField();
		txtMovieName.setBounds(15, 84, 96, 27);
		getContentPane().add(txtMovieName);
		txtMovieName.setColumns(10);
		
		txtSesstionTime = new JTextField();
		txtSesstionTime.setBounds(126, 84, 130, 27);
		getContentPane().add(txtSesstionTime);
		txtSesstionTime.setColumns(10);
		
		initTxt(); //文本赋值,初始化
		
		JButton button = new JButton("\u8D2D\u7968");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//购票按钮事件
				
				//1.取到用户ID，sessionID，座位号
				//IdSave.userId
				//System.out.println(ticketSet.toString());
				
				TicketService ts = new TicketServiceImpl();
				//2.判断余额是否支持现有票价*票个数
				//2.1取到余额--得到用户ID，IdSave.userId;
				Integer userId = IdSave.userId;
				CinemaUsersService cus = new CinemaUsersServiceImpl();
				Double balance = new Double(cus.findUserById(userId).get(0).get("BALANCE").toString());
				//Double balance = cus.findBalanceById(userId);
				
				//2.2取到票价和买票个数
				Double moviePrice = new Double(price);
				int count = ticketSet.size();
				//3.比对价钱和余额
				//3.1余额充足
				if (balance >= moviePrice*count) { 
					//3.2买票，余额减少
					int i = ts.shoppingTicket(IdSave.userId, sessionid, ticketSet,new Double(price));
					
					String message = i>0?"购票成功！":"购票失败请联系系统管理员！";
					JOptionPane.showMessageDialog(null, message);
					
					//3.3买票成功后余座减少  sessionid --> remain, 选种座位数 ticketSet.size();
					SessionService ss = new SessionServiceImpl();
					int seatUpdate = ss.updateSeatRemainBySessionId(sessionid, ticketSet.size());
					String message1 = i>0?"余座数更新成功！":"余座数更新失败!请联系系统管理员!";
					JOptionPane.showMessageDialog(null, message1);
				} else { //余额不足
					JOptionPane.showMessageDialog(null, "余额不足请前往<个人中心><个人账号>充值!");
					
				}
			}
		});
		button.setBounds(335, 43, 123, 29);
		getContentPane().add(button);
		
		initSeat();
	}
}
