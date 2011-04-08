package edu.fiu.cis.feedClient;

import java.sql.Timestamp;
import java.util.List;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

public class EntryWrapper {
	private static final String DEFAULT_AUTHOR = "Anonymous";
	private SyndEntry entry;

	public EntryWrapper(SyndEntry entry) {
		this.entry = entry;
	}

	public String getAuthor() {
		return ( !Utils.empty( entry.getAuthor() ) 	? 
														entry.getAuthor() 
													: 	DEFAULT_AUTHOR);
	}

	public List getAuthors() {
		return entry.getAuthors();
	}

	public List getCategories() {
		return entry.getCategories();
	}

	public List getContents() {
		return entry.getContents();
	}

	public List getContributors() {
		return entry.getContributors();
	}

	public SyndContent getDescription() {
		return entry.getDescription();
	}

	public List getEnclosures() {
		return entry.getEnclosures();
	}

	public Object getForeignMarkup() {
		return entry.getForeignMarkup();
	}

	public Class getInterface() {
		return entry.getInterface();
	}

	public String getLink() {
		return entry.getLink();
	}

	public List getLinks() {
		return entry.getLinks();
	}

	public Module getModule(String arg0) {
		return entry.getModule(arg0);
	}

	public List getModules() {
		return entry.getModules();
	}

	public Timestamp getPublishedDate() {
		return entry.getPublishedDate() !=null ? new Timestamp(entry.getPublishedDate().getTime()) : null;
		
	}

	public String getTitle() {
		return entry.getTitle();
	}

	public SyndContent getTitleEx() {
		return entry.getTitleEx();
	}

	public Timestamp getUpdatedDate() {
		
		return entry.getUpdatedDate() !=null ? new Timestamp(entry.getUpdatedDate().getTime()) : null;
	
	}

	public String getUri() {
		return entry.getUri();
	}
	
}
