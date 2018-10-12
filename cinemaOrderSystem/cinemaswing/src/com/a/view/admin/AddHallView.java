package com.a.view.admin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.Hall;
import com.a.service.HallService;
import com.a.service.Impl.HallServiceImpl;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddHallView extends JDialog {
	private JTextField txtHallName;
	private JTextField txtCapacity;
	private Integer cinemaId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddHallView dialog = new AddHallView(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public AddHallView(Integer cinemaId) {
		setTitle("\u6DFB\u52A0\u5F71\u5385\u7BA1\u7406\u540E\u53F0");
		setBounds(100, 100, 406, 301);
		getContentPane().setLayout(null);
		{
			JLabel label = new JLabel("\u5F71\u5385\u540D\u79F0\uFF1A");
			label.setBounds(63, 43, 109, 21);
			getContentPane().add(label);
		}
		{
			txtHallName = new JTextField();
			txtHallName.setBounds(156, 40, 136, 27);
			getContentPane().add(txtHallName);
			txtHallName.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5F71\u5385\u5BB9\u91CF");
			label.setBounds(65, 92, 81, 21);
			getContentPane().add(label);
		}
		{
			txtCapacity = new JTextField();
			txtCapacity.setBounds(156, 89, 136, 27);
			getContentPane().add(txtCapacity);
			txtCapacity.setColumns(10);
		}
		{
			JButton button = new JButton("\u6DFB\u52A0");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					//影厅添加按钮事件
					
					
					//取得添加的影厅数据
					String hallName = txtHallName.getText();
					String capacity = txtCapacity.getText();
					
					if (!"".equals(hallName) && !"".equals(capacity)) {
						if (capacity.matches("[0-9]*")) {
							//将数据封装在Hall类对象中
							Hall hall = new Hall();
							hall.setCid(cinemaId);
							hall.setHallname(hallName);
							hall.setCapacity(capacity);
							
							//将封装数据传给service 
							HallService hs = new HallServiceImpl();
							int i = hs.AddHallByCinemaId(hall);
							String message = i>0?"添加影厅成功！":"添加失败请联系系统管理员！";
							JOptionPane.showMessageDialog(null, message);
						} else {
							JOptionPane.showMessageDialog(null, "影厅容量数据错误！");
						}
					} else {
						JOptionPane.showMessageDialog(null, "影厅信息不完整！");
					}
				}
			});
			button.setBounds(132, 155, 123, 29);
			getContentPane().add(button);
		}
		this.cinemaId = cinemaId;
	}
}
