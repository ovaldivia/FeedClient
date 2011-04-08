package edu.fiu.cis.feedClient;


import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

/**
 * Retrieves rss feeds from URL list and saves them in a DB
 * @author omvaldiv
 *
 */
public class FeedClient implements Runnable
{
	private PersistEntryDBImpl persist = new PersistEntryDBImpl();
	private List<URL> list;
	private static String FEED_TYPE = "rss_2.0";
	
    /** Namespace URI for content:encoded elements */
    private static String CONTENT_NS =
            "http://purl.org/rss/1.0/modules/content/";

    /** Parses RSS or Atom to instantiate a SyndFeed. */
    private SyndFeedInput input;

    /** Transforms SyndFeed to RSS or Atom XML. */
    private SyndFeedOutput output;

    /**
     * Default constructor.
     */
    public FeedClient(List<URL> list)
    {
        input = new SyndFeedInput();
        output = new SyndFeedOutput();
        this.list = list;
    }
    
    /**
     * Runs thread, needs List of URLs to be set
     */
    public void run() 
    	
    {
    	Utils.logInfo("Start ClientFeed Thread: " + this.toString(), this.getClass());
    	if (list==null)
    		return;
    	
    	for (URL url: list){
    		addFeed(url);
    	}
    	Utils.logInfo("End ClientFeed Thread: " + this.toString(), this.getClass());
    }
    
    
    private void addFeed(URL url){
    	try {
    		// Load the feed, regardless of RSS or Atom type
            SyndFeed feed = input.build(new XmlReader(url));

            // Set the output format of the feed
            feed.setFeedType(FEED_TYPE);

            
            // Iterate through feed items, adding a footer each item
            Iterator entryIter = feed.getEntries().iterator();
            while (entryIter.hasNext())
            {
                SyndEntry entry = (SyndEntry) entryIter.next();
                if (entry!=null){
                	entry.setUri(url.toString());
                	
                	persist(entry);
                }
                	
            }
		} catch (Exception e) {
			Utils.logError("Error parsing url:"+ url.toString() + " - " + e.getMessage(), this.getClass());
		}
    	
   
    }

    
    private void persist(SyndEntry entry)
    {
    	persist.add(new EntryWrapper(entry));
        
    }


}
