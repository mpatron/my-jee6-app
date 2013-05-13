package org.jobjects.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DerbyStart {

	private static Logger LOGGER = Logger.getLogger(DerbyStart.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver).newInstance();
			Properties p = new Properties();
			p.setProperty("user", "sa");
			p.setProperty("password", "manager");
			p.setProperty("create", "true");

			Connection conn = DriverManager.getConnection("jdbc:derby:memory:MyDerbyDB", p);

			final ResultSet tables = conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });
			List<String> tableNames = new ArrayList<String>();
			while (tables.next()) {
				tableNames.add(tables.getString("TABLE_NAME").toLowerCase());
			}
			conn.close();
			
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
		}
	}

	@Test(groups = "MaSuite")
	public void testStart() {
		LOGGER.log(Level.INFO, "Derby Stating");
	}

}