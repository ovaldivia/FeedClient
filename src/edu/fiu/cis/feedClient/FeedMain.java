package edu.fiu.cis.feedClient;

import java.net.URL;
import java.util.List;

public class FeedMain {
	private static final int NUMBER_THREADS = 4;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.logInfo("Start Main Client Feed", FeedMain.class);
		
		int size =  UrlFeedServer.getInstance().size();
		int numberURLSperThread = size; 
		
		if (size > NUMBER_THREADS && size % NUMBER_THREADS == 0){
			numberURLSperThread = size / NUMBER_THREADS;
		}else{
			numberURLSperThread = (size / NUMBER_THREADS) + 1;
		}
		
		
		
		//Create NUMBER_THREADS threads
		for (int i = 0 ; i < NUMBER_THREADS ; i++){
			//List of URLS from weblog.com
			List<URL> list = UrlFeedServer.getInstance().getUrlBuffer(numberURLSperThread);
			
			//run feed client thread
			(new Thread(new FeedClient(list))).start();
			
		}
		
		Utils.logInfo("Done", FeedMain.class);

	}
	
	


}
