package org.hhm.crawler.config;

public class Config {
	public static int getTaskID() {
		return taskID;
	}

	public static void setTaskID(int taskID) {
		Config.taskID = taskID;
	}

	public static int getIsLucene() {
		return isLucene;
	}

	public static void setIsLucene(int isLucene) {
		Config.isLucene = isLucene;
	}

	public static int getMonitorTime() {
		return monitorTime;
	}

	public static void setMonitorTime(int monitorTime) {
		Config.monitorTime = monitorTime;
	}

	public static int getThreads() {
		return threads;
	}

	public static void setThreads(int threads) {
		Config.threads = threads;
	}

	public static int getThreadGatherMax() {
		return threadGatherMax;
	}

	public static void setThreadGatherMax(int threadGatherMax) {
		Config.threadGatherMax = threadGatherMax;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	private static int taskID = 0;
	private static int isLucene = 0;
	private static int monitorTime = 3000;
	private static int threads = 1;
	private static int threadGatherMax = 20;
	private static int isApplyTemplate = 0;

	public static int getIsApplyTemplate() {
		return isApplyTemplate;
	}

	public static void setIsApplyTemplate(int isApplyTemplate) {
		Config.isApplyTemplate = isApplyTemplate;
	}

}
