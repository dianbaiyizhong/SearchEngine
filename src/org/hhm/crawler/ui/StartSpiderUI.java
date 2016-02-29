package org.hhm.crawler.ui;

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
import org.hhm.crawler.spider.Spider;

public class StartSpiderUI extends JFrame {

	static Config config = new Config();
	static XmlBean xmlBean = new XmlBean();

	private JTextField textField_indexPath;
	private JButton btn_browseIndexPath;
	private JButton btn_start;

	public StartSpiderUI() {
		getContentPane().setLayout(null);
		textField_indexPath = new JTextField();
		textField_indexPath.setBounds(10, 10, 311, 21);
		getContentPane().add(textField_indexPath);
		textField_indexPath.setColumns(10);

		btn_browseIndexPath = new JButton("选择索引文件目录");
		btn_browseIndexPath.setBounds(331, 9, 150, 23);
		getContentPane().add(btn_browseIndexPath);

		btn_start = new JButton("开始爬虫");
		btn_start.setBounds(0, 284, 434, 40);
		getContentPane().add(btn_start);

		InitValue();
		InitEvent();

	}

	private void InitValue() {

		xmlBean.getConfig(new XMLElement("config/Config.xml").get());
		// 把xml中索引文件路径的值放在文本框里
		textField_indexPath.setText(Config.getMsgPath());

	}

	private void InitEvent() {
		btn_browseIndexPath.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser_indexPath = new JFileChooser();
				chooser_indexPath
						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser_indexPath.showDialog(new JLabel(), "选择");

				File file = chooser_indexPath.getSelectedFile();
				if (file.isDirectory()) {
					textField_indexPath.setText(file.getAbsolutePath());

				} else if (file.isFile()) {
					System.out.println("文件:" + file.getAbsolutePath());
				}

			}
		});

		btn_start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Config.setMsgPath(textField_indexPath.getText());
				try {
					xmlBean.setConfig(config, new XMLElement(
							"config/Config.xml").set(), new FileWriter(
							"config/Config.xml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 开始爬虫
				new Spider().Start();
			}

		});

	}

	public static void main(String[] args) {

		StartSpiderUI start = new StartSpiderUI();
		start.setSize(500, 500);
		start.setVisible(true);

	}
}
