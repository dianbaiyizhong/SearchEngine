package org.hhm.common.pojo;

public class Config {
	private static int taskID = 0;
	private static int isLucene = 0;
	private static int monitorTime = 3000;
	private static int threads = 1;
	private static int threadGatherMax = 20;
	private static int isApplyTemplate = 0;
	private static String indexPath;

	public static String getMsgPath() {
		return msgPath;
	}

	public static void setMsgPath(String Parameter) {
		msgPath = Parameter;
	}

	private static String msgPath;

	public static String getIndexPath() {
		return indexPath;
	}

	public static void setIndexPath(String Parameter) {
		indexPath = Parameter;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int Parameter) {
		taskID = Parameter;
	}

	public int getIsLucene() {
		return isLucene;
	}

	public void setIsLucene(int Parameter) {
		isLucene = Parameter;
	}

	public int getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(int Parameter) {
		monitorTime = Parameter;
	}

	public int getThreads() {
		return threads;
	}

	public static void setThreads(int Parameter) {
		threads = Parameter;
	}

	public int getThreadGatherMax() {
		return threadGatherMax;
	}

	public void setThreadGatherMax(int Parameter) {
		threadGatherMax = Parameter;
	}

	public int getIsApplyTemplate() {
		return isApplyTemplate;
	}

	public void setIsApplyTemplate(int Parameter) {
		isApplyTemplate = Parameter;
	}

}
