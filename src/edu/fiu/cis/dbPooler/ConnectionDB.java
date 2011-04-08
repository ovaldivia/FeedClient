package edu.fiu.cis.dbPooler;

import java.util.Properties;
import edu.fiu.cis.feedClient.Utils;



/**
 * Singleton class that obtains connections from pooler. Uses db_connection property file
 * @author omvaldiv
 *
 */
public class ConnectionDB extends ConnectionPooler{
	private static final String JDBC_DRIVER = "jdbc_driver";
	private static final String CONNECTION_STRING = "connection_string";
	private static final String PROPERTIES = "db_connection.properties";
	private static ConnectionDB instance = null;
	
	protected ConnectionDB(){
		Properties properties = Utils.loadProperties(PROPERTIES);
		String jdbcDriver = properties.getProperty(JDBC_DRIVER);
		String connString = properties.getProperty(CONNECTION_STRING);
		
		super.setDriver(jdbcDriver,connString);
	}
	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();		
		}
		return instance;
	}
	
}
