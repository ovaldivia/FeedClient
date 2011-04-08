package edu.fiu.cis.dbPooler;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import edu.fiu.cis.feedClient.Utils;

/**
 * JDBC Pooler abstract class that uses Apache common pooler
 * @author omvaldiv
 *
 */
public abstract class ConnectionPooler {
	private static final String POOL_NAME = "flsppool";
	/**
	 * Must be set call before anything else
	 * @param JDBCdriver
	 * @param connectURI
	 */
	public void setDriver(String JDBCdriver, String connectURI){
		try {
			Class.forName(JDBCdriver);
			setupDriver(connectURI);
			
		}catch (Exception e){
			Utils.logError(e.getMessage(), this.getClass());
		}
	}
	
	/**
	 * Returns a connection object from the pooler
	 * @return java.sql.Connection
	 */
	public Connection getConnection(){
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:" + POOL_NAME);
			
		}catch (Exception e){
			Utils.logError(e.getMessage(), this.getClass());
		}
		return conn;
	}
	
	public static void setupDriver(String connectURI) throws Exception {
        
        ObjectPool connectionPool = new GenericObjectPool(null);
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI,null);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
        
        Class.forName("org.apache.commons.dbcp.PoolingDriver");
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.registerPool(POOL_NAME,connectionPool);

    }

    public static void printDriverStats() throws Exception {
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        ObjectPool connectionPool = driver.getConnectionPool(POOL_NAME);
        
    }

    public static void shutdownDriver() throws Exception {
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.closePool(POOL_NAME);
    }
}
