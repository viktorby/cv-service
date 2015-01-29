package com.cv.dao;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

/**
 * This class returns the MSSQL database connect object.
 * 
 * The method and variable in this class are static to save resources. You only
 * need one instance of this class running.
 * 
 * @author Viktar Charnarutski
 * 
 */

public class CVDataBase {

	private static final String JDBC_CV_DATA_SOURCE = "jdbc/cv";

	// hold the database object
	private static DataSource cvDataSource = null;

	// used to lookup the database connection in Apache
	private static Context context = null;

	/**
	 * This is a public method that will return a database connection.
	 * 
	 * @return Database object
	 */
	private static DataSource getCVConn() {

		/**
		 * check to see if the database object is already defined... if it is,
		 * then return the connection, no need to look it up again.
		 */
		if (cvDataSource != null) {
			return cvDataSource;
		}

		try {
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in Apache
			 * cvDataSource will hold the database object
			 */
			if (context == null) {

				context = new InitialContext();

			}

			cvDataSource = (DataSource) context
					.lookup(JDBC_CV_DATA_SOURCE);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cvDataSource;
	}

	/**
	 * This method will return the connection to the MSSQL CV schema.
	 * 
	 * Note that the scope is protected which means only java class in the dao
	 * package can use this method.
	 * 
	 * @return Connection to CV MSSQL database.
	 */
	public static Connection mssqlPcPartsConnection() {
		Connection conn = null;
		try {
			conn = getCVConn().getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}