package org.jobjects.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DerbyStop {

	private static Logger LOGGER = Logger.getLogger(DerbyStop.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			Connection conn=DriverManager.getConnection("jdbc:derby:memory:MyDerbyDB");
			final ResultSet tables = conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });
			StringBuffer sb =new StringBuffer(); 
			while (tables.next()) {
				sb.append(tables.getString("TABLE_SCHEM"));
				sb.append(" ");
				sb.append(tables.getString("TABLE_NAME"));
				sb.append(SystemUtils.LINE_SEPARATOR);
			}
			conn.close();
			LOGGER.info(sb.toString());
			
			LOGGER.info("Extinction de Derby");
			DriverManager.getConnection("jdbc:derby:memory:MyDerbyDB;shutdown=true");
		} catch (Exception ignored) {
			LOGGER.log(Level.INFO, "Extinction de memory:MyDerbyDB : " + ignored.getLocalizedMessage());
		}
		try {
			LOGGER.info("Extinction de Derby");
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (Exception ignored) {
			LOGGER.log(Level.INFO, "Extinction de derby : " + ignored.getLocalizedMessage());
		}
	}

	@Test(groups = "MaSuite")
	public void testStop() {
		LOGGER.log(Level.INFO, "Derby Stop");
	}

}
