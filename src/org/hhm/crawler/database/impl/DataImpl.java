package org.hhm.crawler.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hhm.common.pojo.Content;
import org.hhm.crawler.database.DBConnByMySql;

public class DataImpl {

	public void saveData(Content content) {
		PreparedStatement stmt = null;
		Connection conn = DBConnByMySql.getConnection();

		try {

			stmt = conn
					.prepareStatement("INSERT INTO content2 (`md5`,`source`,`url`,`title`,`text`,`author`,`time`) VALUES (?,?,?,?,?,?,?)");
			stmt.setString(1, content.getMd5());
			stmt.setString(2, content.getSeedName());
			stmt.setString(3, content.getUrl());
			stmt.setString(4, content.getTitle());
			stmt.setString(5, content.getText());
			stmt.setString(6, content.getAuthor());
			stmt.setString(7, content.getTime());

			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("该文章已经存在于数据库");
			e.getCause();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
