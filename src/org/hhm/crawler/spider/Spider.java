package org.hhm.crawler.spider;

import org.hhm.crawler.controller.Controller;
import org.hhm.crawler.init.Init;
import org.hhm.crawler.update.Crawldb;

public class Spider {
	static Crawldb crawldb = Crawldb.getInstance();

	public void Start() {
		Init init = new Init();
		init.action();

		Controller controller = new Controller();
		controller.Start();
	}

}
