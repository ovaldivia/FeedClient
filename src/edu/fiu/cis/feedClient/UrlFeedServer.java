package edu.fiu.cis.feedClient;

import java.net.URL;
import java.util.List;


public class UrlFeedServer {
	private static UrlFeedServer instance = null;
	private UrlFeedServerFixTranslator translator;
	
	protected UrlFeedServer(){
		translator = new UrlFeedServerFixTranslator();
	}
	public static UrlFeedServer getInstance() {
		if (instance == null) {
			instance = new UrlFeedServer();		
		}
		return instance;
	}
	
	public int size(){
		return translator.size();
	}
	
	public List<URL> getUrlBuffer(int n){
		return translator.list(n);
	}
	
	public List<URL> getUrlBuffer(){
		return translator.list();
	}
	
	
	
	
}
