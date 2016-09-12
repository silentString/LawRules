package util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	
	private ComboPooledDataSource dataSource;
	
	public DBUtil() {
		
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setUser("dev");
			dataSource.setPassword("dev1234");
			dataSource.setJdbcUrl("jdbc:mysql://120.26.132.104/law_rules?autoReconnect=true");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setInitialPoolSize(3);
			dataSource.setMinPoolSize(1);
			dataSource.setMaxPoolSize(10);
			dataSource.setMaxStatements(50);
			dataSource.setMaxIdleTime(600);
			dataSource.setAcquireRetryAttempts(2);
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	
	public final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public void close(Connection conn) {
		try {
			if (conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement stat){
		try {
			if (stat != null) {
				stat.close();
				stat = null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet set) {
		try {
			if (set != null) {
				set.close();
				set = null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		
 		Connection connection = new DBUtil().getConnection();
		if (connection == null) {
			System.out.println("failed");
		} else {
			System.out.println("success");
		}
		
	}

}
