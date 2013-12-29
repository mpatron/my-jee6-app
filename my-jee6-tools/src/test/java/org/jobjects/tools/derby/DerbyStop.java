package org.jobjects.tools.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SystemUtils;
import org.jobjects.tools.AppConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DerbyStop {

	private static Logger LOGGER = Logger.getLogger(DerbyStop.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass(groups = "MaSuite")
	public static void tearDownAfterClass() throws Exception {
		try {
			Connection conn=DriverManager.getConnection("jdbc:derby:memory:MyDerbyDB");
			StringBuffer sb =new StringBuffer();
			sb.append("Schéma :");
			sb.append(SystemUtils.LINE_SEPARATOR);
//			final ResultSet tables = conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });
//			while (tables.next()) {
//				sb.append(tables.getString("TABLE_SCHEM"));
//				sb.append(".");
//				sb.append(tables.getString("TABLE_NAME"));
//				sb.append(SystemUtils.LINE_SEPARATOR);
//			}

			
			JDCConnectionInfo ji = new JDCConnectionInfo(conn, AppConstants.SCHEMA_NAME); 
			List<String> tables=ji.getTables();
			for (String table : tables) {
				sb.append(table);
				sb.append(SystemUtils.LINE_SEPARATOR);
				List<String> columnNames= ji.getColumnNames(table);
				for (String columnName : columnNames) {
					sb.append(" -"+columnName);
					sb.append(SystemUtils.LINE_SEPARATOR);
				}
				List<String> childTables =ji.getChildTables(table);
				for (String childTable : childTables) {
					sb.append("-->");
					sb.append(childTable);
					sb.append(SystemUtils.LINE_SEPARATOR);
				}
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
	public void testDerbyStop() {
		LOGGER.log(Level.INFO, "Derby Stop");
	}

}
