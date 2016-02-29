package org.hhm.lucene.excute;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.hhm.common.pojo.Content;

public class IndexBuilder {
	public boolean buildIndexer(Analyzer analyzer, Directory directory,
			List<Content> items) {
		IndexWriter iwriter = null;
		try {
			// 配置索引
			iwriter = new IndexWriter(directory, new IndexWriterConfig(
					Version.LUCENE_47, analyzer));
			// 删除所有document
			iwriter.deleteAll();
			// 将文档信息存入索引
			Document doc[] = new Document[items.size()];
			for (int i = 0; i < items.size(); i++) {

				System.out.println("正在建立索引:已经完成了" +i+1+ "/" + items.size());
				doc[i] = new Document();

				Content item = items.get(i);
				java.lang.reflect.Field[] fields = item.getClass()
						.getDeclaredFields();
				for (java.lang.reflect.Field field : fields) {
					String fieldName = field.getName();
					String getMethodName = "get"
							+ toFirstLetterUpperCase(fieldName);
					Object obj = item.getClass().getMethod(getMethodName)
							.invoke(item);
					doc[i].add(new Field(fieldName, (String) obj,
							TextField.TYPE_STORED));
				}

				iwriter.addDocument(doc[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				iwriter.close();
			} catch (IOException e) {
			}
		}
		return true;
	}

	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}
}
