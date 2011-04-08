package edu.fiu.cis.feedClient;

import java.text.MessageFormat;


public class PersistDBConstants {
	public static MessageFormat INSERT_CATEGORY = new MessageFormat("INSERT INTO entry_category(entry_id, name) values({0},{1});");
	
	public static String INSERT_FEED = 
		"INSERT INTO feed_entry(" +
		"uri, " +
		"author," +
		"title,  " +
		"link," +
		"updated_date," +
		"published_date," +
		"content," +
		"enter_date" +
		")" +
		"VALUES(" +
		"?,?,?,?,?,?,?,?" +
		")";
	
	public static String SELECT_IDENTITY = " SELECT seq_feed_entry_id.CURRVAL FROM dual";
	
	public static String SELECT_URLS = "SELECT * FROM feed_urls ORDER BY enter_date DESC";
	
	public static String UPDATE_URL = "UPDATE feed_urls SET parsed = 'Y' WHERE url = ?";
	
}
