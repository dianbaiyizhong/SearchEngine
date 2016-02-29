package org.hhm.lucene.excute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.hhm.lucene.pojo.Item;
import org.hhm.lucene.util.StringTool;

public class IndexSearcher {

	static StringTool stringTool = new StringTool();

	public List<Item> searchIndexer(Analyzer analyzer, Directory directory,
			String keyword) {
		DirectoryReader ireader = null;
		List<Item> result = new ArrayList<Item>();
		try {
			// 设定搜索目录
			ireader = DirectoryReader.open(directory);
			org.apache.lucene.search.IndexSearcher isearcher = new org.apache.lucene.search.IndexSearcher(
					ireader);

			// 对多field进行搜索
			java.lang.reflect.Field[] fields = Item.class.getDeclaredFields();
			int length = fields.length;
			String[] multiFields = new String[length];
			for (int i = 0; i < length; i++) {
				multiFields[i] = fields[i].getName();
			}
			MultiFieldQueryParser parser = new MultiFieldQueryParser(
					Version.LUCENE_47, multiFields, analyzer);

			// 设定具体的搜索词
			Query query = parser.parse(keyword);
			ScoreDoc[] hits = isearcher.search(query, null, 10).scoreDocs;

			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				Item item = new Item();
				for (String field : multiFields) {
					String setMethodName = "set"
							+ StringTool.toFirstLetterUpperCase(field);
					item.getClass().getMethod(setMethodName, String.class)
							.invoke(item, hitDoc.get(field));
				}
				result.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ireader.close();
				directory.close();
			} catch (IOException e) {
			}
		}
		return result;
	}

	public String displayHtmlHighlight(Query query, Analyzer analyzer,
			String fieldName, String fieldContent, int fragmentSize)
			throws IOException, InvalidTokenOffsetsException {
		// 创建一个高亮器
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(
				"<font color='red'>", "</font>"), new QueryScorer(query));
		Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
		highlighter.setTextFragmenter(fragmenter);
		return highlighter.getBestFragment(analyzer, fieldName, fieldContent);
	}

}
