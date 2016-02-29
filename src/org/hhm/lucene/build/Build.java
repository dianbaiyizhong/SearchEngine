package org.hhm.lucene.build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONObject;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.hhm.common.pojo.Config;
import org.hhm.common.pojo.Content;
import org.hhm.common.util.xml.XmlBean;
import org.hhm.lucene.excute.IndexBuilder;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Build {

	static XmlBean xmlBean = new XmlBean();
	static Config config = new Config();

	public void Start() {
		File file = new File(Config.getMsgPath());
		File[] list = file.listFiles();

		List<Content> items = new ArrayList<Content>();
		for (int i = 0; i < list.length; i++) {

			File file1 = new File(list[i].getAbsolutePath());
			System.out.println(file1.getName().toString());
			if (getExtName(file1.getName().toString(), '.').equals(".txt")) {
				JSONObject jsonObject = JSONObject
						.fromObject(ReadFromTxt(file1));
				Content content = (Content) JSONObject.toBean(jsonObject,
						Content.class);
				items.add(content);

			}

		}

		BuildIndex(items);

	}

	public void BuildIndex(List<Content> items) {
		IKAnalyzer analyzer = new IKAnalyzer(true);
		// analyzer.setUseSmart(true);
		// 设置产生索引文件的目录
		File file = new File(Config.getIndexPath());
		Directory directory = null;
		try {
			directory = FSDirectory.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		IndexBuilder indexBuilder = new IndexBuilder();
		indexBuilder.buildIndexer(analyzer, directory, items);
	}

	public static String ReadFromTxt(File file) {
		try {
			Scanner in = new Scanner(file);
			String str = "";
			while (in.hasNextLine()) {
				str = str + in.nextLine();
			}

			return str;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getExtName(String s, char split) {
		int i = s.indexOf(split);
		int leg = s.length();
		return (i > 0 ? (i + 1) == leg ? " " : s.substring(i, s.length()) : " ");
	}
}
