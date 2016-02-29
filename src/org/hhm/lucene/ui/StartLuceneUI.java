package org.hhm.lucene.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hhm.common.pojo.Config;
import org.hhm.common.util.xml.XMLElement;
import org.hhm.common.util.xml.XmlBean;
import org.hhm.lucene.build.Build;

public class StartLuceneUI extends JFrame {

	static Config config = new Config();
	static XmlBean xmlBean = new XmlBean();

	private JTextField textField_msgPath;
	private JButton btn_browseMsgPath;
	private JTextField textField_indexPath;
	private JButton btn_browseIndexPath;
	private JButton btn_start;

	public StartLuceneUI() {
		getContentPane().setLayout(null);
		textField_msgPath = new JTextField();
		textField_msgPath.setBounds(10, 10, 311, 21);

		textField_indexPath = new JTextField();
		textField_indexPath.setBounds(10, 50, 311, 21);

		getContentPane().add(textField_msgPath);
		getContentPane().add(textField_indexPath);
		textField_msgPath.setColumns(10);
		textField_indexPath.setColumns(10);

		btn_browseMsgPath = new JButton("选择信息目录");
		btn_browseMsgPath.setBounds(331, 9, 150, 23);
		btn_browseIndexPath = new JButton("选择索引目录");
		btn_browseIndexPath.setBounds(331, 49, 150, 23);

		getContentPane().add(btn_browseMsgPath);
		getContentPane().add(btn_browseIndexPath);

		btn_start = new JButton("开始建立索引");
		btn_start.setBounds(0, 284, 434, 40);
		getContentPane().add(btn_start);

		InitValue();
		InitEvent();

	}

	private void InitEvent() {
		btn_browseMsgPath.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showDialog(new JLabel(), "选择");

				File file = chooser.getSelectedFile();
				if (file.isDirectory()) {
					textField_msgPath.setText(file.getAbsolutePath());

				} else if (file.isFile()) {
					System.out.println("文件:" + file.getAbsolutePath());
				}

			}
		});

		btn_browseIndexPath.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showDialog(new JLabel(), "选择");

				File file = chooser.getSelectedFile();
				if (file.isDirectory()) {
					textField_indexPath.setText(file.getAbsolutePath());

				} else if (file.isFile()) {
					System.out.println("文件:" + file.getAbsolutePath());
				}

			}
		});

		btn_start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Config.setMsgPath(textField_msgPath.getText());
				Config.setIndexPath(textField_indexPath.getText());
				try {
					xmlBean.setConfig(config, new XMLElement(
							"config/Config.xml").set(), new FileWriter(
							"config/Config.xml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 开始索引
				new Build().Start();
			}

		});

	}

	private void InitValue() {
		xmlBean.getConfig(new XMLElement("config/Config.xml").get());
		// 把xml中索引文件路径的值放在文本框里
		textField_msgPath.setText(Config.getMsgPath());
		textField_indexPath.setText(Config.getIndexPath());

	}

	public static void main(String[] args) {

		StartLuceneUI start = new StartLuceneUI();
		start.setSize(500, 500);
		start.setVisible(true);

	}
}
