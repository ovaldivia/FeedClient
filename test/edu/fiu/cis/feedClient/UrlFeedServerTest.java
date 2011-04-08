package edu.fiu.cis.feedClient;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

public class UrlFeedServerTest extends TestCase {

	public void testGetInstance() {
		List<URL> list = UrlFeedServer.getInstance().getUrlBuffer();
		
		assertTrue(list.size()>0);
	}
	
	
	public void testFeedClient(){
		List<URL> list = UrlFeedServer.getInstance().getUrlBuffer();
		
		FeedClient feed = new FeedClient(list);
		feed.run();
	}

}
