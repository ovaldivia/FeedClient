package edu.fiu.cis.feedClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.jsoup.Jsoup;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;

import edu.fiu.cis.dbPooler.ConnectionDB;
import edu.fiu.cis.wordUtils.StopWords;


public class PersistEntryDBImpl  implements PersistEntry{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	
	public PersistEntryDBImpl() {
		conn = ConnectionDB.getInstance().getConnection();
	}

	private String getContent(List<SyndContent> list){
		String sCmd = "";
		for (SyndContent content: list){
			//remove HTML and stop-words
			sCmd += StopWords.remove(  
							Jsoup.parse(  
									content.getValue() 
							).text() 
					) + "\n";
		}
		
		return sCmd;
	}
	
	private void saveCategories(int id, List<SyndCategory> list){
		PreparedStatement pstmt = null;
		String sql = "";
		for (SyndCategory category: list){
			sql += PersistDBConstants.INSERT_CATEGORY.format(new Object[]{id, Utils.setString(category.getName())});
		}
		if (sql.length()>0){
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeBatch();
				
				
			} catch (Exception e) {
				Utils.logError(e.getMessage(), this.getClass());
			}finally{
				try {
					pstmt.close();
				} catch (Exception e) {}
			}
		}
		
	}
	
	/**
	 * Returns the auto generated id after an insert. 
	 * @param pstmt
	 * @return Returns -1 if not found
	 */
	private int getIdentity(PreparedStatement pstmt){
		int id = -1;
		ResultSet rs = null;
		
		try {
			rs = pstmt.getGeneratedKeys();
			if (rs.next()){
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			Utils.logError(e.getMessage(), this.getClass());
		}finally{
			try {
				rs.close();
			} catch (Exception e) {}
		}
		return id;
	}
	
	public String getDescription(SyndContent source){
		
		
		if (source!=null)
			//remove HTML and stop-words
			return StopWords.remove(  
												Jsoup.parse(  
													source.getValue() 
												).text() 
									);
		else
			return "";
		
	}
	
	
	public void add(EntryWrapper entry){
		
		try {
			pstmt = conn.prepareStatement(PersistDBConstants.INSERT_FEED, pstmt.RETURN_GENERATED_KEYS);
			Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
			
			String content = getDescription(entry.getDescription());
			content += getContent(entry.getContents());
			
			pstmt.setString	(1, entry.getUri() );
		    pstmt.setString	(2, entry.getAuthor() );
		    pstmt.setString	(3, entry.getTitle());
			pstmt.setString	(4, entry.getLink() );
			pstmt.setTimestamp	(5, entry.getUpdatedDate() );
		    pstmt.setTimestamp	(6, entry.getPublishedDate() );
		    pstmt.setString(7, content);
		    pstmt.setTimestamp  (8, currentDate );
		    pstmt.executeUpdate();
		    
		    int id = getIdentity(pstmt);
		    saveCategories(id,entry.getCategories());
	        
			
		} catch (SQLException e){
			//If is not a duplicate entry exception then print.
			if (!e.getMessage().contains("Duplicate entry")){
				Utils.logError(e.getMessage(), this.getClass());
			}
			
		} catch (Exception e) {
			Utils.logError(e.getMessage(), this.getClass());
		}
	}
	
	
	
	
	
}
