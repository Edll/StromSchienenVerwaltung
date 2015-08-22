/**
 * 
 */
package de.edlly.material;

import java.sql.*;

/**
 * DbAbfrage Abfrage des Material aus der DB
 * 
 * 
 * Attribute: id: Id des Materials MaterialSorteId: Id der Material Sorte x:
 * Breite des Materials g z: Dicke des Materials SqlConn: Database Connection
 * aus aufgerufenen Klasse
 * 
 * Methoden: SelectId() Abfrage eine Materials Anhand der Material Id. Gibt
 * diese als int Array zur�ck SelectTableString() Gibt die Komplette Table als
 * String Array zur�ck SelectTableInt() Gibt die Komplette Table als Int Array
 * zur�ck SelectTableFormat() Gibt die Komplette Table als String mit
 * Formatierung zur�ck SelectMaterialSorteId() Gibt den Material String aus der
 * DB wieder SelectTableMaterialSorteString() Gibt die Komplette Table
 * MaterialSorte als String Array zur�ck
 * 
 * @author Edlly
 *
 */
public class DbAbfrage {
	public int id;
	public int MaterialSorteId;
	public int x;
	public int z;
	public Connection SqlConn;


	
	public DbAbfrage(){
	
			this.SqlConn  = de.edlly.db.SQLiteConnect.dbConnection();
	
	}


	public int[] SelectId(int id) {
		int RowNumber = 0;
		Statement pst = null;
		ResultSet rs = null, ArrayRs = null;
		int[] result = new int[4];

		if (id == 0) {
			id = this.id;
		}

		try {
			pst = SqlConn.createStatement();
			String query = "SELECT MaterialSorteId, x, z, yMax FROM Material WHERE id = \"" + id
					+ "\" AND visibly = \"1\" ";
			rs = pst.executeQuery(query);
			while (rs.next()) {
				RowNumber++;
			}
			if (RowNumber != 1) {
				throw new IllegalArgumentException("Das von ihnen gew�hlte Material steht leider nicht zur verf�gung");
			}
			ArrayRs = pst.executeQuery(query);

			result[0] = ArrayRs.getInt(1);
			result[1] = ArrayRs.getInt(2);
			result[2] = ArrayRs.getInt(3);
			result[3] = ArrayRs.getInt(4);

			return result;

		} catch (Exception e) {

			throw new IllegalArgumentException(e);
		} finally {
			try {
				pst.close();
				rs.close();
				ArrayRs.close();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}

	}

	/**
	 * Gibt die Komplette Table Material als Int Array zur�ck.
	 * 
	 * @return int Array mit der Table Feldgr��e ist 6.
	 */
	public int[][] SelectTableInt() {
		int count = 0;
		int RowNumber = 0;
		Statement pst = null;
		ResultSet rs = null, ArrayRs = null;
		try {
			pst = SqlConn.createStatement();
			String query = "SELECT * FROM Material";
			rs = pst.executeQuery(query);
			// SQLite unterst�tzt keine Forward Backward Kommandos deshalb diese
			// unsaubere WorkAround um die Gr��e des Arrays zu ermitteln
			while (rs.next()) {
				RowNumber++;
			}
			rs.close();
			int[][] result = new int[RowNumber][6];

			ArrayRs = pst.executeQuery(query);
			// Abfrage der Werte f�r Das Table Array.
			while (ArrayRs.next()) {
				result[count][0] = rs.getInt(1);
				result[count][1] = rs.getInt(2);
				result[count][2] = rs.getInt(3);
				result[count][3] = rs.getInt(4);
				result[count][4] = rs.getInt(5);
				result[count][5] = rs.getInt(6);
				count++;
			}

			pst.close();
			ArrayRs.close();

			return result;

		} catch (Exception e) {

			throw new IllegalArgumentException(e);
		} finally {
			try {
				pst.close();
				rs.close();
				ArrayRs.close();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Gibt die Komplette Table Material als String Array zur�ck.
	 * 
	 * @return int Array mit der Table Feldgr��e ist 5.
	 */
	public String[][] SelectTableString() {
		int count = 0;
		int RowNumber = 0;
		Statement pst = null;
		ResultSet rs = null, ArrayRs = null;

		try {
			pst = SqlConn.createStatement();
			String query = "SELECT * FROM Material";
			rs = pst.executeQuery(query);
			// SQLite unterst�tzt keine Forward Backward Kommandos deshalb diese
			// unsaubere WorkAround um die Gr��e des Arrays zu ermitteln
			while (rs.next()) {
				RowNumber++;
			}

			rs.close();
			String[][] result = new String[RowNumber][6];

			ArrayRs = pst.executeQuery(query);
			// Abfrage der Werte f�r Das Table Array.
			while (ArrayRs.next()) {
				result[count][0] = Integer.toString(rs.getInt(1));
				result[count][1] = Integer.toString(rs.getInt(2));
				result[count][2] = Integer.toString(rs.getInt(3));
				result[count][3] = Integer.toString(rs.getInt(4));
				result[count][4] = Integer.toString(rs.getInt(5));
				result[count][5] = Integer.toString(rs.getInt(6));
				count++;
			}
			return result;
		} catch (Exception e) {

			throw new IllegalArgumentException(e);

		} finally {
			try {
				pst.close();
				rs.close();
				ArrayRs.close();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}

	}

	/**
	 * Erstellt ein Array das eine Formatierte Ausgabe der Material Table zur
	 * Verf�gung stellt.
	 * 
	 * @return
	 */

	public String[][] SelectTableFormat() {

		// Abfrage der Werte Table aus der Datenbank
		DbAbfrage TableInt = new DbAbfrage();
		TableInt.SqlConn = this.SqlConn;
		TableInt.SelectTableInt();
		int[][] MaterialDBData = TableInt.SelectTableInt();

		int SizeofResult = MaterialDBData.length;
		if (SizeofResult == 0) {
			String[][] result = null;
			return result;
		}

		String[][] result = new String[SizeofResult][5];

		for (int i = 0; i < SizeofResult; i++) {
			// Id
			result[i][0] = Integer.toString(MaterialDBData[i][0]);
			// Material Sorte
			result[i][1] = SelectMaterialSorteId(MaterialDBData[i][1]);
			// Material Ausma�e ...x...
			result[i][2] = Integer.toString(MaterialDBData[i][2]) + "x" + Integer.toString(MaterialDBData[i][3]);
			// Maximale L�nge
			result[i][3] = Integer.toString(MaterialDBData[i][4]);
			// L�schen
			result[i][4] = Integer.toString(MaterialDBData[i][5]);
		}
		return result;
	}

	/**
	 * Gibt die MaterialSorte als String zur�ck
	 * 
	 * @param MaterialSorteId
	 * @return
	 */
	public String SelectMaterialSorteId(int MaterialSorteId) {
		String result = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			if (MaterialSorteId == 0) {
				throw new IllegalArgumentException("Material Sorte in Abfrage MaterialSorteId 0");
			}
			String query = "SELECT MaterialSorte FROM MaterialSorten Where id=" + MaterialSorteId;
			pst = SqlConn.prepareStatement(query);

			rs = pst.executeQuery();
			result = rs.getString(1);

			return result;
		} catch (Exception e) {

			throw new IllegalArgumentException(e);

		} finally {
			try {
				pst.close();
				rs.close();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Gibt die MaterialSorte als Id zur�ck
	 * 
	 * @param MaterialSorteString
	 * @return
	 */
	public int SelectMaterialSorteString(String MaterialSorteString) {
		int result = 0;
		ResultSet rs = null;
		PreparedStatement pst = null;

		try {
			if (MaterialSorteString == null) {
				throw new IllegalArgumentException("Material Sorte in Abfrage MaterialSorteId 0");

			}
			String query = "SELECT id FROM MaterialSorten Where MaterialSorte='" + MaterialSorteString + "'";
			pst = SqlConn.prepareStatement(query);
			rs = pst.executeQuery();
			result = rs.getInt(1);

			return result;
		} catch (Exception e) {

			throw new IllegalArgumentException(e);

		} finally {
			try {
				pst.close();
				rs.close();
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Gibt die in der Datenbank vorhandenen MaterialSorten als String zur�ck
	 * 
	 * @return String Array Material Sorten
	 */
	public String[] SelectTableMaterialSorteString() {
		int count = 0;
		int RowNumber = 0;
		Statement pst = null;
		ResultSet rs = null, ArrayRs = null;

		try {
			pst = SqlConn.createStatement();
			String query = "SELECT MaterialSorte FROM MaterialSorten";
			rs = pst.executeQuery(query);
			// SQLite unterst�tzt keine Forward Backward Kommandos deshalb diese
			// unsaubere WorkAround um die Gr��e des Arrays zu ermitteln
			while (rs.next()) {
				RowNumber++;
			}
			rs.close();
			String[] result = new String[RowNumber];

			ArrayRs = pst.executeQuery(query);
			// Abfrage der Werte f�r Das Table Array.
			while (ArrayRs.next()) {
				result[count] = rs.getString(1);
				count++;
			}
			return result;
		} catch (Exception e) {

			throw new IllegalArgumentException(e);

		} finally {
			try {
				pst.close();
				rs.close();
				ArrayRs.close();
			} catch (Exception e) {
				// throw new IllegalArgumentException(e);
			}
		}
	}

}
