package edu.fiu.cis.feedClient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlFeedServerFixTranslator {
	private final static String URL_FILE = "urlfile.txt";
	private ArrayList<URL> list;
	private int nextIndex;
	
	public UrlFeedServerFixTranslator() {
		//Initializes list with URLs from URL_FILE
		nextIndex = 0;
		list = new ArrayList<URL>();
		
		String urlString = null;
		
		try {
			FileInputStream fstream = new FileInputStream(URL_FILE);
			
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        
	        //Iterate the file line by line
	        while ((urlString = br.readLine()) != null)   {
			      if ( !Utils.empty(urlString) ){
			    	  
			    	  //if not empty string add the URL to result 
			    	  URL url = new URL(urlString);
						list.add(url);
			      }
			      
			}
			  
			in.close();
			    
		} catch (Exception e) {
			Utils.logError(e.getMessage(), this.getClass());
		}
		
	}
	
	public int size(){
		return list.size();
	}
	
	/**
	 * Gets partial list base of size n
	 * @param n size of partial sublist
	 * @return sublist of size n
	 */
	public List<URL> list(int n){
		int cLastIndex = nextIndex + n;
		int cFirstIndex = nextIndex;
		
		//Got to the end of the list
		if (nextIndex == cLastIndex){
			return null;
		}else if (cLastIndex > list.size()){ 
			//rest of the list
			cLastIndex = list.size();
		}
		
		nextIndex = cLastIndex;
		
		return list.subList(cFirstIndex, cLastIndex);
	}
	
	
	public List<URL> list(){
		
		return list;
	}
	
	
}
