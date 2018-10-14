package com.a.view.users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.a.pojo.CinemaUsers;
import com.a.service.CinemaUsersService;
import com.a.service.SessionService;
import com.a.service.TicketService;
import com.a.service.Impl.CinemaUsersServiceImpl;
import com.a.service.Impl.SessionServiceImpl;
import com.a.service.Impl.TicketServiceImpl;
import com.a.util.IdSave;

public class ShowTicketView extends JDialog {
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	
	
	public void initTable() {
		//让table显示数据
		
		//1.将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		//2.电影名，影院，影厅，放映时间，座位号，时长，价格
		columnNames.add("编号");
		columnNames.add("电影");
		columnNames.add("影院");
		columnNames.add("影厅");
		columnNames.add("座位号");
		columnNames.add("时间");
		columnNames.add("时长");
		columnNames.add("价格");
		
		//3.将数据放在另一个vector上
		TicketService ts = new TicketServiceImpl();
		List<Map<String,Object>> findSessionidByUserId = ts.findSessionidByUserId("1", IdSave.userId);
		//System.out.println(IdSave.userId);
		//System.out.println(findSessionidByUserId);
		Set<String> sidSet = new HashSet<String>();
		for (Map<String, Object> map : findSessionidByUserId) {
			sidSet.add(map.get("SID").toString());
		}
		SessionService ss = new SessionServiceImpl();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (String sessionid : sidSet) {
			Vector<Vector<String>> data1 = ss.findMovieRelatedInformationBySessionid(sessionid, "1",IdSave.userId);
			for (Vector<String> vector : data1) {
				data.add(vector);
			}
		}
		
		//4.将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		//5.将model添加到table上
		table.setModel(dm);
		//隐藏编号
		hideTableColumn(table,0);
	}
	
	
	
	public void initTable_1() {
		//让table显示数据
		
		//将列名放在vector上
		Vector<String> columnNames = new Vector<String>();
		//电影名，影院，影厅，放映时间，座位号，时长，价格
		columnNames.add("编号");
		columnNames.add("电影");
		columnNames.add("影院");
		columnNames.add("影厅");
		columnNames.add("座位号");
		columnNames.add("时间");
		columnNames.add("时长");
		columnNames.add("价格");
		
		//将数据放在另一个vector上
		TicketService ts = new TicketServiceImpl();
		List<Map<String,Object>> findSessionidByUserId = ts.findSessionidByUserId("0", IdSave.userId);
		//System.out.println(findSessionidByUserId);
		Set<String> sidSet = new HashSet<String>();
		for (Map<String, Object> map : findSessionidByUserId) {
			sidSet.add(map.get("SID").toString());
		}
		SessionService ss = new SessionServiceImpl();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (String sessionid : sidSet) {
			Vector<Vector<String>> data1 = ss.findMovieRelatedInformationBySessionid(sessionid, "0",IdSave.userId);
			for (Vector<String> vector : data1) {
				data.add(vector);
//				System.out.println(vector);
			}
		}
		
		//将列和数据放到model上
		DefaultTableModel dm = new DefaultTableModel(data, columnNames);
		//将model添加到table上
		table_1.setModel(dm);
		//隐藏编号
		hideTableColumn(table_1,0);
	}
	/**
	 * 隐藏列
	 * @param table
	 * @param column
	 */
	private void hideTableColumn(JTable table,int column) {
		//隐藏列可以取值
		TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowTicketView dialog = new ShowTicketView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowTicketView() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 92, 720, 115);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 310, 720, 141);
			getContentPane().add(scrollPane);
			{
				table_1 = new JTable();
				scrollPane.setViewportView(table_1);
			}
		}
		{
			JButton button = new JButton("\u9000\u7968");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//退票按钮事件
					
					//1.得到票的ID
					int rowIndex = table.getSelectedRow();
					if (rowIndex > -1) {
						String ticketId = (String) table.getValueAt(rowIndex, 0);
						//1.1更新余座，ticketId -> sessionId -> 设置余座加一
						TicketService ts = new TicketServiceImpl();
						String sessionid = ts.findSessionidByTicketid(ticketId);
						SessionService ss = new SessionServiceImpl();
						int k = ss.updateSeatRemainBySessionId(sessionid, -1); //-1代表余座加一
						//1.3.c得到电影单价
						Map<String, Object> sessionInformation = ss.findSessionInformationBysessionId(sessionid);
						Integer price = Integer.parseInt(sessionInformation.get("PRICE").toString());
						System.out.println("price="+price);
						//1.2更新座位未被选中 -> 设置ticket表的status为0，查看座位时加上条件status=1
						int i = ts.setTicketStatus0(ticketId);
						
						//1.3 更新用户余额
						//a.用户ID
						Integer userId = ts.findUserIdByTicketId(ticketId);
						System.out.println(userId);
						CinemaUsersService cs = new CinemaUsersServiceImpl();
						//b.用户余额
						Double balance = new Double(cs.findUserById(userId).get(0).get("BALANCE").toString());
						
						CinemaUsers cu = new CinemaUsers();
						cu.setUserid(userId); //定位到本账号所属
						balance += price; 
						System.out.println("现在余额："+balance);
						cu.setBalance(balance); //更新余额
						//d.执行用户余额更新操作
						cs.updateUserInfoById(cu);
						
						//成功
						String message = i>0?"成功退票！":"退票失败请联系系统管理员！";
						JOptionPane.showMessageDialog(null, message);
						initTable();
						initTable_1();
					} else {
						JOptionPane.showMessageDialog(null, "请选择未出行票所在行！");
					}
				}
			});
			button.setBounds(583, 237, 123, 29);
			getContentPane().add(button);
		}
		{
			textField = new JTextField();
			textField.setText("\u672A\u51FA\u884C\u8BA2\u5355\uFF1A");
			textField.setBounds(29, 50, 123, 27);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setText("\u5386\u53F2\u8BA2\u5355\uFF1A");
			textField_1.setBounds(29, 268, 96, 27);
			getContentPane().add(textField_1);
			textField_1.setColumns(10);
		}
		
		initTable();
		initTable_1();
	}

}
