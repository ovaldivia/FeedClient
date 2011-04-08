package edu.fiu.cis.feedClient;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.fiu.cis.dbPooler.ConnectionDB;

public class UrlFeedServerDBTranslator {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public UrlFeedServerDBTranslator() {
		conn = ConnectionDB.getInstance().getConnection();
	}
	
	public List<URL> list(int N){
		ArrayList<URL> list = new ArrayList<URL>();
		try {
			pstmt = conn.prepareStatement(PersistDBConstants.SELECT_URLS);
			
			pstmt.setInt(1, N );
		    
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				URL url = new URL(rs.getString("rss_url"));
				list.add(url);
			}
			
			for (URL url:list){
				pstmt = conn.prepareStatement(PersistDBConstants.UPDATE_URL);
				pstmt.setString(1, url.toString());
				pstmt.executeUpdate();
				
				pstmt.close();
			}
			
			
		} catch (Exception e) {
			Utils.logError(e.getMessage(), this.getClass());
		}
		return list;
	}
	
	
}
