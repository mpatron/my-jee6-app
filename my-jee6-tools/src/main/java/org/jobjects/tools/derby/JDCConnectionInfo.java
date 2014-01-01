package org.jobjects.tools.derby;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe donne des informations du dictionaire de la base de données.
 * 
 * @see org.jobjects.dao.jdbc.v1
 * @author Mickaël PATRON
 * @version $Id: ConnectionInfo.java,v 1.0 2001/05/22 21:10:35 curcuru Exp $
 */

public class JDCConnectionInfo {
	private DatabaseMetaData metadata = null;
	private String schemaName = null;

	// ---------------------------------------------------------------------------

	/**
	 * Constructeur.
	 * 
	 * @param conn
	 *            est la connection vers la base de donnée.
	 * @param schemaName
	 *            est le nom du schema de la base de donnée.
	 */
	public JDCConnectionInfo(Connection conn, String schemaName)
			throws SQLException {
		this.metadata = conn.getMetaData();
		this.schemaName = schemaName;
	}

	// ---------------------------------------------------------------------------

	/**
	 * La liste des tables parentes de la table pass�e en paramêtre.
	 * {@link #getChildTables(String) getComponentAt}
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des tables sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getParentTables(String tableName) throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getImportedKeys(null, schemaName, tableName);
		String chaine;
		while (rs.next()) {
			chaine = rs.getString("FKTABLE_NAME");
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des tables enfantes de la table pass�e en paramêtre.
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des tables sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getChildTables(String tableName) throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getExportedKeys(null, schemaName, tableName);
		String chaine;
		while (rs.next()) {
			chaine = rs.getString("FKTABLE_NAME");
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des champs de la clef primaire
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des champs sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getPrimaryColumns(String tableName) throws SQLException {
		String chaine;
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getPrimaryKeys(null, schemaName, tableName);
		while (rs.next()) {
			chaine = rs.getString("COLUMN_NAME").toUpperCase();
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des champs de la clef primaire qui provienneent des tables
	 * parentes.
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des champs sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getImportedPrimaryColumns(String tableName)
			throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getImportedKeys(null, schemaName, tableName);
		String chaine;
		while (rs.next()) {
			chaine = rs.getString("FKCOLUMN_NAME");
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des champs nom null.
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des tables sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getMandatoryColumns(String tableName)
			throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		try {
			ResultSet rs = metadata.getIndexInfo(null, schemaName, tableName,
					false, true); // SQLException ici
			String buffer;
			while (rs.next()) {
				if (!rs.getBoolean("NON_UNIQUE")) {
					buffer = rs.getString("INDEX_NAME");
					if (buffer != null) {
						if (!buffer.equals("")) {
							returnValue.add(rs.getString("COLUMN_NAME"));
						}
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des champs de la clef primaire qui sont propre à la table
	 * et qui ne font pas r�ference aux tables parentes.
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des tables sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getOwnPrimaryColumns(String tableName)
			throws SQLException {
		String chaine;

		List<String> Imported_v = new ArrayList<String>();
		try {
			ResultSet Imported_rs = metadata.getImportedKeys(null, schemaName,
					tableName);
			while (Imported_rs.next()) {
				chaine = Imported_rs.getString("FKCOLUMN_NAME");
				if (!Imported_v.contains(chaine))
					Imported_v.add(chaine);
			}
			Imported_rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> returnValue = new ArrayList<String>();
		try {
			ResultSet rs = metadata.getPrimaryKeys(null, schemaName, tableName);
			while (rs.next()) {
				chaine = rs.getString("COLUMN_NAME");
				if (!returnValue.contains(chaine))
					returnValue.add(chaine);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//TODO
		//Ne doit pas être supprimer à reprendre
//		@SuppressWarnings("unchecked")
//		List<String> clone = (List<String>) returnValue.clone();
//		int length = clone.size();
//		for (int i = 0; i < length; i++) {
//			chaine = (String) clone.get(i);
//			if (Imported_v.contains(chaine))
//				returnValue.removeElement(chaine);
//		}
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des champs.
	 * 
	 * @param tableName
	 *            est le nom de la table
	 * @return un List<String> contenant le nom des champs sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getColumnNames(String tableName) throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getColumns(null, schemaName, tableName, "%");
		String chaine;
		while (rs.next()) {
			chaine = rs.getString("COLUMN_NAME");
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Liste des noms des tables.
	 * 
	 * @return un List<String> contenant le nom des tables sous la forme
	 *         d'éléments de type String
	 * @throws SQLException
	 *             peut être lancé pour un problème de connexion.
	 */
	public List<String> getTables() throws SQLException {
		List<String> returnValue = new ArrayList<String>();
		ResultSet rs = metadata.getTables(null, schemaName, "%",
				new String[] { "TABLE" });
		String chaine;
		while (rs.next()) {
			chaine = rs.getString("TABLE_NAME");
			if (!returnValue.contains(chaine))
				returnValue.add(chaine);
		}
		rs.close();
		return returnValue;
	}

	// ---------------------------------------------------------------------------

	/**
	 * Affiche sur la sortie standard le contenu du RecordSet.
	 * 
	 * @param rs
	 *            est le RecordSet à Afficher
	 * @return rien.
	 */
	public static void Affiche(ResultSet rs) {
		try {
			List<String[]> lignes = new ArrayList<String[]>();

			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();

			String[] chaine = new String[colcount];
			int[] colsize = new int[colcount];

			for (int i = 1; i <= colcount; i++) {
				chaine[i - 1] = rsmd.getColumnName(i);
				colsize[i - 1] = chaine[i - 1].length();
			}
			lignes.add(chaine);

			while (rs.next()) {
				chaine = new String[colcount];
				for (int i = 1; i <= colcount; i++) {
					chaine[i - 1] = rs.getString(i);
					if (chaine[i - 1] != null) {
						if (colsize[i - 1] < chaine[i - 1].length()) {
							colsize[i - 1] = chaine[i - 1].length();
						}
					}
				}
				lignes.add(chaine);
			}
			rs.close();

			int diff;
			for (int i = 0; i < lignes.size(); i++) {
				chaine = (String[]) lignes.get(i);
				for (int j = 0; j < chaine.length; j++) {
					if (chaine[j] == null)
						chaine[j] = "Null";
					diff = colsize[j] - chaine[j].length();
					System.out.print(chaine[j] + " ");
					for (int k = 0; k < diff; k++)
						System.out.print(" ");
					System.out.print(" ");
				}
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	// ------------------------------------------------------------------------------

}