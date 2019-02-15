package br.com.home.c3p0sample;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseUtility {
	public static ComboPooledDataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/desenv");
		cpds.setUser("desenv");
		cpds.setPassword("desenv");

		// Optional Settings
		cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(100);

		return cpds;
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			ComboPooledDataSource dataSource = DatabaseUtility.getDataSource();
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM account");

			System.out.println("The Connection Object is of Class: " + connection.getClass());

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				System.out
						.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
			}

		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.close();
			}
		}
		
		System.out.println("Finish!");
	}
}