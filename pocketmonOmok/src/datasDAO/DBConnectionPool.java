package datasDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

class ConnectionObject {
	public Connection connection = null;
	public boolean inUse = false;
	
	public ConnectionObject(Connection connection, boolean useFlag) {
		this.connection = connection;
		this.inUse = useFlag;
	}
}

public class DBConnectionPool {
	private Vector<ConnectionObject> connections = new Vector<ConnectionObject>(10);
	private String driver = "oracle.jdbc.driver.OracleDriver";       
	private String url    = "jdbc:oracle:thin:@localhost:1521:xe";   
	private String user   = "sirupe";                                
	private String passwd = "7133";                                  
	
	private boolean traceOn = true;;
	private boolean initialized = false;
	private int openConnections = 10;
	
	private static DBConnectionPool instance = null; 
	
	public static DBConnectionPool getInstance() {
		if(instance == null) {
			synchronized (DBConnectionPool.class) {
				if(instance == null) {
					instance = new DBConnectionPool();
				}
			}
		}
		
		return instance;
	}

	
	private Connection createConnection() throws SQLException {
		Connection conn = null; 
		
		try {
			if(this.user == null) {
				this.user = "";
			}
			
			if(this.passwd == null) {
				this.passwd = "";
			}
			
			Properties propers = new Properties();
			propers.put("user", this.user);
			propers.put("password", this.passwd);
			conn = DriverManager.getConnection(this.url, propers);
		} catch(Throwable t) {
			throw new SQLException(t.getMessage());
		}
		
		return conn;
	}
	
	public synchronized void setInitOpenConnections(int count) throws SQLException {
		Connection connection = null;
		ConnectionObject connObject = null;
		
		for(int i = 0; i < count; i++) {
			connection = this.createConnection();
			connObject = new ConnectionObject(connection, false);
			this.connections.add(connObject);
			this.trace("ConnectionPoolManager : Adding new DB Connection to pool(" + connections.size() + ")");
		}
	}
	
	
	public synchronized Connection getConnection() throws Exception {
		if(!this.initialized) {
			Class c = Class.forName(this.driver);
			DriverManager.registerDriver((Driver)c.newInstance());
			this.initialized = true;
		}
		
		Connection connection = null;
		ConnectionObject connObject = null;
		boolean badConnection = false;
		
		for(int i = 0; i < connections.size(); i++) {
			connObject = connections.elementAt(i);
			if(!connObject.inUse) {
				try {
					badConnection = connObject.connection.isClosed();
					if(!badConnection) {
						badConnection = (connObject.connection.getWarnings() != null);
					}
				} catch(Exception e) {
					badConnection = true;
					e.printStackTrace();
				}
				
				if(badConnection) {
					connections.removeElement(i);
					continue;
				}
				
				connection = connObject.connection;
				connObject.inUse = true;
				
//				this.trace("ConnectionPoolManager: Using existing DB connection #" + (i + 1));
				break;
			}
		}
		
		if(connection == null) {
			connection = createConnection();
			connObject = new ConnectionObject(connection, true);
			
			
			this.connections.addElement(connObject);
			this.trace("ConnectionPoolManager: Creating new DB connection #" + connections.size());
		} 
		return connection;
	}
	
	public void releaseFreeConnections() {
		this.trace("ConnectionPoolManager.releaseFreeConnections()");
		ConnectionObject connObject = null;
		
		for(int i = 0, size = this.connections.size(); i < size; i++) {
			connObject = this.connections.elementAt(i);
			
			if(!connObject.inUse) {
				removeConnection(connObject.connection);
			}
		}
	}
	
	public void finalize() {
		this.trace("ConnectionPoolManager.finalize()");
		ConnectionObject connObject = null;
		
		for(int i = 0, size = this.connections.size(); i < size; i++) {
			connObject = this.connections.elementAt(i);
			
			try {
				connObject.connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			connObject = null;
		}
		
		this.connections.removeAllElements();
	}
	
	public synchronized void freeConnection(Connection connection) {
		if(connection == null) {
			return;
		}
		
		ConnectionObject connObject = null;
		
		for(int i = 0, size = this.connections.size(); i < size; i++) {
			connObject = this.connections.elementAt(i);
			
			if(connection == connObject.connection) {
				connObject.inUse = false;
				break;
			}
		}
		
		for(int i = 0; i < connections.size(); i++) {
			connObject = this.connections.elementAt(i);
			if((i + 1) > this.openConnections && !connObject.inUse) {
				this.removeConnection(connObject.connection);
			}
		}
	}
	
	public void freeConnection(Connection connection, PreparedStatement pre, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			
			if(pre != null) {
				pre.close();
			}
			
			this.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection connection, Statement state, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			
			if(state != null) {
				state.close();
			}
			
			this.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection connection, PreparedStatement pre) {
		try {
			if(pre != null) {
				pre.close();
			}
			
			this.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void freeConnection(Connection connection, Statement state) {
		try {
			if(state != null) {
				state.close();
			}
			
			this.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void removeConnection(Connection connection) {
		if(connection == null) {
			return;
		}
		
		ConnectionObject connObject = null;
		
		for(int i = 0, size = connections.size(); i < size; i ++) {
			if(connection == connObject.connection) {
				try {
					connection.close();
					connections.removeElementAt(i);
					this.trace("Removed" + connection.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	private void trace(String s) {
		if(this.traceOn) {
			System.out.println(s);
		}
	}
	
	
	
	public void setOpenConnectionCount(int count) {
		this.openConnections = count;
	}
	
	public void setEnableTrace(boolean enable) {
		this.traceOn = enable;
	}
	
	public int getConnectionCount() {
		return this.connections.size();
	}
	
	public Vector<ConnectionObject> getConnections() {
		return connections;
	}
	
	
}
