package org.hhm.crawler.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import net.sf.json.JSONObject;

import org.hhm.common.pojo.Config;
import org.hhm.common.pojo.Content;

public class Store {
	static Config config = new Config();

	public void Save(Content content) {
		File file = new File(Config.getMsgPath() + "/"
				+ content.getSeedName());

		// 先创建一个seedName的文件夹
		if (!file.exists()) {
			file.mkdir();

		}

		// 在对应的seedName文件夹中创建txt
		File file1 = new File(file.getAbsolutePath() + "/" + content.getMd5()
				+ ".txt");
		try {
			// 如果不存在txt文件，就创建
			if (!file1.exists()) {
				file1.createNewFile();

				JSONObject jSONObject = JSONObject.fromObject(content);

				WriteInTxt(file1, jSONObject.toString());

			} else {

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void WriteInTxt(File file, String Content) {
		try {
			// 创建文件
			file.createNewFile();
			// 声明字符输出流
			Writer out = null;
			// 通过子类实例化，表示可以追加
			out = new FileWriter(file, true);
			// 写入数据
			out.write(Content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
