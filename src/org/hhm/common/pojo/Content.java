package org.hhm.common.pojo;

public class Content {

	@Override
	public String toString() {
		return "Content [author=" + author + ", md5=" + md5 + ", seedName="
				+ seedName + ", text=" + text + ", time=" + time + ", title="
				+ title + ", url=" + url + "]";
	}

	private String url;
	private String md5;
	private String seedName;
	private String time;
	private String title;
	private String author;
	private String text;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSeedName() {
		return seedName;
	}

	public void setSeedName(String seedName) {
		this.seedName = seedName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
