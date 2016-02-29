package org.hhm.crawler.fetch;

import java.util.ArrayList;
import java.util.List;

import org.hhm.crawler.analyzer.Analyzer;
import org.hhm.crawler.fetch.jsoup.Parser;
import org.hhm.crawler.pojo.Seeds;
import org.hhm.crawler.update.Crawldb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Gather implements Runnable {

	static Parser parser = new Parser();
	Crawldb crawldb = Crawldb.getInstance();
	private List<Seeds> list;

	public Gather(List<Seeds> list) {
		this.list = list;
	}

	public void run() {

		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {

				// 获取url
				Seeds seeds_plan = list.get(i);

				String url = seeds_plan.getUrl();

				// 根据url获取源代码
				String sourseCode = parser.getSourseCode(url);
				if (sourseCode == null) {
					continue;
				}

				// 判断是否已经达到指定深度
				if (seeds_plan.getNow_depth() <= seeds_plan.getPoint_depth()) {

					getNewUrl(sourseCode, seeds_plan);

					// 在这里开始解析

					if (seeds_plan.getSeedName().equals("天涯论坛")) {
						if (seeds_plan.getUrl().contains(
								"http://bbs.tianya.cn/post")) {
							Analyzer analyzer = new Analyzer(seeds_plan,
									sourseCode);
							analyzer.start();
						}

					} else if (seeds_plan.getSeedName().equals("豆瓣社区")) {
						if (seeds_plan.getUrl().contains(
								"http://www.douban.com/note/")) {
							Analyzer analyzer = new Analyzer(seeds_plan,
									sourseCode);
							analyzer.start();
						}
					}

					else {
						Analyzer analyzer = new Analyzer(seeds_plan, sourseCode);
						analyzer.start();
					}

				} else {
					continue;
				}

			}

		}

	}

	/**
	 * 获取新链接
	 * 
	 * @param sourseCode
	 * @param seeds_plan
	 */
	private void getNewUrl(String sourseCode, Seeds seeds_plan) {
		// 获取a标签
		Document doc = Jsoup.parse(sourseCode);
		Elements el_lable_A = doc.select("a");

		List<String> list_A = new ArrayList<String>();
		for (int k = 0; k < el_lable_A.size(); k++) {

			list_A.add(el_lable_A.get(k).attr("href"));

		}
		// 把url存进crawldb
		for (int j = 0; j < list_A.size(); j++) {

			// 先判断是否为符合规则的url,返回null，则不符合
			String new_url = crawldb.filter(seeds_plan, list_A.get(j).trim());

			if (new_url != null) {

				Seeds seeds = new Seeds();

				seeds = (Seeds) seeds_plan.clone();

				seeds.setUrl(new_url);

				seeds.setNow_depth(seeds_plan.getNow_depth() + 1);

				crawldb.set(seeds);
			}

		}
	}
}
