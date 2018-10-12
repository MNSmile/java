package com.a.view.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.a.pojo.Movie;
import com.a.service.MovieService;
import com.a.service.Impl.MovieServiceImpl;
import com.a.util.IdSave;

public class AddMovieView extends JInternalFrame {
	private JTextField txtMovieName;
	private JTextField txtDetail;
	private JTextField txtDuration;
	private JTextField txtType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieView frame = new AddMovieView();
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
	public AddMovieView() {
		setBounds(100, 100, 294, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7535\u5F71\u540D\u79F0:");
		label.setBounds(15, 15, 81, 27);
		getContentPane().add(label);
		
		txtMovieName = new JTextField();
		txtMovieName.setBounds(123, 15, 96, 27);
		getContentPane().add(txtMovieName);
		txtMovieName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7535\u5F71\u8BE6\u60C5:");
		label_1.setBounds(15, 70, 81, 21);
		getContentPane().add(label_1);
		
		txtDetail = new JTextField();
		txtDetail.setBounds(123, 67, 96, 27);
		getContentPane().add(txtDetail);
		txtDetail.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7535\u5F71\u65F6\u957F:");
		label_2.setBounds(15, 119, 81, 21);
		getContentPane().add(label_2);
		
		txtDuration = new JTextField();
		txtDuration.setBounds(123, 116, 96, 27);
		getContentPane().add(txtDuration);
		txtDuration.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7535\u5F71\u7C7B\u578B:");
		label_3.setBounds(15, 179, 81, 21);
		getContentPane().add(label_3);
		
		txtType = new JTextField();
		txtType.setBounds(123, 176, 96, 27);
		getContentPane().add(txtType);
		txtType.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加电影按钮事件
				
				//得到填入电影数据
				String movieName = txtMovieName.getText();
				String movieDetail = txtDetail.getText();
				String movieDuration = txtDuration.getText();
				String movieType = txtType.getText();
				
				if (!"".equals(movieName)) {
					if (!"".equals(movieDetail)) {
						if (!"".equals(movieDuration)) {
							if (!"".equals(movieType)) {
								if (movieDuration.matches("[0-9]*")) {
									//将数据封装
									Movie movie = new Movie();
									movie.setMovieName(movieName);
									movie.setDetail(movieDetail);
									movie.setDuration(movieDuration);
									movie.setMovieType(movieType);

									//把数据传给service，保存在数据库中，返回ID
									MovieService ms = new MovieServiceImpl();
									int moviePrimaryKey = ms.addMovieReturnId(movie);
									
									String message = moviePrimaryKey>0?"添加电影成功！":"添加失败请联系系统管理员！";
									JOptionPane.showMessageDialog(null, message);
								} else {
									JOptionPane.showMessageDialog(null, "时长只能是数字！");
								}
							} else {
								JOptionPane.showMessageDialog(null, "请填写电影类型！");
							}
						} else {
							JOptionPane.showMessageDialog(null, "请填写电影时长！");
						}
					} else {
						JOptionPane.showMessageDialog(null, "请填写电影详情！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请填入电影名！");
				}
			}
		});
		button.setBounds(80, 215, 123, 29);
		getContentPane().add(button);

	}

}
