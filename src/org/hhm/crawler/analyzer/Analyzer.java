package org.hhm.crawler.analyzer;

import org.hhm.common.pojo.Content;
import org.hhm.crawler.database.impl.DataImpl;
import org.hhm.crawler.pojo.Seeds;
import org.hhm.crawler.store.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Analyzer {

	Seeds seeds_plan;
	String sourceCode;

	static Store store = new Store();
	static DataImpl dataImpl = new DataImpl();

	public Analyzer(Seeds seeds_plan, String sourceCode) {
		this.seeds_plan = seeds_plan;
		this.sourceCode = sourceCode;
	}

	public void start() {
		Content content = new Content();

		content.setUrl(seeds_plan.getUrl());
		content.setSeedName(seeds_plan.getSeedName());
		content.setMd5(seeds_plan.getMd5());
		Document doc = Jsoup.parse(sourceCode);

		if (seeds_plan.getTitle() != null) {

			content.setTitle(doc.title());

		}

		if (seeds_plan.getText() != null) {

			String lablename = seeds_plan.getText().attributeValue("lablename");

			String labelclass = seeds_plan.getText().attributeValue(
					"labelclass");
			String labelid = seeds_plan.getText().attributeValue("labelid");

			String text = "";
			if (labelclass != "") {
				text = doc.select(lablename + "[class=" + labelclass + "]")
						.text();
			} else if (labelid != "") {
				text = doc.select(lablename + "[id=" + labelid + "]").text();

			}

			if (labelclass == null && labelid == null) {
				text = doc.text();
			}

			content.setText(text);
		}

		if (seeds_plan.getAuthor() != null) {

			String lablename = seeds_plan.getAuthor().attributeValue(
					"lablename");

			String labelclass = seeds_plan.getAuthor().attributeValue(
					"labelclass");
			String labelid = seeds_plan.getAuthor().attributeValue("labelid");

			String author = "";
			if (labelclass != "") {

				author = doc.select(lablename + "[class=" + labelclass + "]")
						.get(0).text();

			} else if (labelid != "") {
				author = doc.select(lablename + "[id=" + labelid + "]").get(0)
						.text();

			}

			// 匹配不同网站
			AnalyzerAssiste aa = new AnalyzerAssiste(seeds_plan.getSeedName(),
					author);

			author = aa.getAuthor();

			content.setAuthor(author);

		}

		if (seeds_plan.getTime() != null) {

			String lablename = seeds_plan.getTime().attributeValue("lablename");

			String labelclass = seeds_plan.getTime().attributeValue(
					"labelclass");
			String labelid = seeds_plan.getTime().attributeValue("labelid");

			String time = "";
			if (labelclass != "") {

				time = doc.select(lablename + "[class=" + labelclass + "]")
						.get(0).text();

			} else if (labelid != "") {
				time = doc.select(lablename + "[id=" + labelid + "]").get(0)
						.text();

			}

			// 匹配不同网站

			AnalyzerAssiste aa = new AnalyzerAssiste(seeds_plan.getSeedName(),
					time);

			time = aa.getTime();

			content.setTime(time);

		}

		if (content.getText().length() != 0) {
			System.out.println(content);

			// store.Save(content);
			// 采用数据库存储

			// dataImpl.saveData(content);

		}

	}
}
