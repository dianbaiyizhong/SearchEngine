package com.hhm.searchengine.dao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.hhm.common.pojo.Content;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.hhm.searchengine.util.PathUtil;

public class SearchServlet extends HttpServlet {

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	private String str;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String keyword = request.getParameter("keyWord");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		List<Content> list = getResult(keyword, pageSize, currentPage);

		JSONArray jSONArray = JSONArray.fromObject(list);

		out.write(jSONArray.toString());

		out.flush();
		out.close();
	}

	// 使用的分词器是ik分词器
	IKAnalyzer analyzer = new IKAnalyzer(true);
	PathUtil pathUtile = new PathUtil();

	// 获取索引文件的路径
	File file = new File(pathUtile.getIndexPath());

	// 设定搜索的区域为
	String[] fields = { "url", "title", "text" };
	// 最大的查询数量，当然是越多越好
	int MaxQueryCount = 2147483647;

	public List getResult(String keyword, int pageSize, int currentPage) {

		IndexSearcher isearcher = getIndexSearcher();

		Query query = getQuery(keyword);
		System.out.println("Query = " + query);
		// 搜索相似度最高的n条记录
		TopDocs topDocs = null;
		try {
			topDocs = isearcher.search(query, MaxQueryCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("命中：" + topDocs.totalHits);

		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		// 查询起始记录位置
		int begin = pageSize * (currentPage - 1);
		// 查询终止记录位置

		int end = Math.min(begin + pageSize, scoreDocs.length);

		List<Content> resultList = new ArrayList<Content>();
		for (int i = begin; i < end; i++) {
			Content content = new Content();
			Document targetDoc = null;
			try {
				targetDoc = isearcher.doc(scoreDocs[i].doc);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String title;
			try {
				title = getStringByHighlight(query, analyzer, "title",
						targetDoc.get("title"), 50);
				content.setTitle(title);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
				e.printStackTrace();
			}

			String text;
			try {
				text = getStringByHighlight(query, analyzer, "text",
						targetDoc.get("text"), 300);
				content.setText(text);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
				e.printStackTrace();
			}

			content.setUrl(targetDoc.getField("url").stringValue());

			resultList.add(content);

		}

		return resultList;
	}

	public int getResultTotal(String keyword) {

		Query query = null;
		try {
			query = new MultiFieldQueryParser(Version.LUCENE_47, fields,
					analyzer).parse(keyword);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TopDocs topDocs = null;
		try {
			topDocs = getIndexSearcher().search(query, MaxQueryCount);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return topDocs.totalHits;
	}

	public IndexSearcher getIndexSearcher() {
		Directory directory = null;
		try {
			directory = FSDirectory.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		IndexReader ireader = null;
		try {
			ireader = DirectoryReader.open(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		IndexSearcher isearcher = new IndexSearcher(ireader);

		return isearcher;
	}

	public Query getQuery(String keyword) {
		Query query = null;
		try {
			query = new MultiFieldQueryParser(Version.LUCENE_47, fields,
					analyzer).parse(keyword);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return query;
	}

	/**
	 * 
	 * @param query
	 * @param analyzer
	 * @param fieldName
	 * @param fieldContent
	 *            文本所有的内容
	 * @param fragmentSize
	 *            限制文本大小
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 */
	public String getStringByHighlight(Query query, Analyzer analyzer,
			String fieldName, String fieldContent, int fragmentSize)
			throws IOException, InvalidTokenOffsetsException {
		// 创建一个高亮器
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(
				"<font color='red'>", "</font>"), new QueryScorer(query));

		Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
		highlighter.setTextFragmenter(fragmenter);

		String result = highlighter.getBestFragment(analyzer, fieldName,
				fieldContent);

		// 如果没有高亮词，限制长度的方法也没有效果，那就自己限制长度
		if (result == null) {
			if (fieldContent.length() >= 300) {
				return fieldContent.substring(0, 299);

			} else {
				return fieldContent;
			}
		} else {
			return highlighter.getBestFragment(analyzer, fieldName,
					fieldContent);

		}

	}

	@Override
	public void init() throws ServletException {
	}

}
