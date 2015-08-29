package de.edlly.material;

import java.sql.*;
import de.edlly.material.MaterialKonstanten;

/**
 * Legt einen neuen Material Datensatz aus den objektVariabeln an.
 * 
 * TODO updateVisibly in eine geeignete Klasse verschieben.
 * 
 * @author Edlly java@edlly.de
 *
 */
public class NeuerMaterialDatensatz {

	private int koordinateX;
	private int koordinateZ;
	private int koordinateyMax;
	private int materialSorteId;
	private Connection sqlConnection;

	public NeuerMaterialDatensatz(Connection SqlConnection) {
		this.sqlConnection = SqlConnection;

	}

	public void setMaterialDaten(int koordinateX, int koordinateZ, int koordinateyMax, int materialSorteId) {

		if (koordinateXIstImDefinitionsbereich(koordinateX)) {
			this.koordinateX = koordinateX;
		}

		if (koordinateZIstImDefinitionsbereich(koordinateZ)) {
			this.koordinateZ = koordinateZ;
		}

		if (koordinateyMaxIstImDefinitionsbereich(koordinateyMax)) {
			this.koordinateyMax = koordinateyMax;
		}

		if (materialSorteIdIstVorhanden(materialSorteId)) {
			this.materialSorteId = materialSorteId;
		}

	}

	public Boolean datensatzAusObjektWertenAnlegen() throws SQLException {

		PreparedStatement sqlPreparedStatment = null;

		try {
			String query = "INSERT INTO Material (\"MaterialSorteId\",\"x\",\"z\",\"yMax\",\"visibly\") VALUES (?1,?2,?3,?4,?5)";

			sqlPreparedStatment = sqlConnection.prepareStatement(query);

			sqlPreparedStatment.setInt(1, this.materialSorteId);
			sqlPreparedStatment.setInt(2, this.koordinateX);
			sqlPreparedStatment.setInt(3, this.koordinateZ);
			sqlPreparedStatment.setInt(4, this.koordinateyMax);
			sqlPreparedStatment.setBoolean(5, true);
			sqlPreparedStatment.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			try {
				sqlPreparedStatment.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return true;
	}

	public Boolean koordinateXIstImDefinitionsbereich(int koordinateX) {

		if (koordinateX <= 0) {
			throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
		}

		if (koordinateX > MaterialKonstanten.MAXIMALER_X_WERT) {
			throw new IllegalArgumentException(
					"Die maximal Material Breite ist: " + MaterialKonstanten.MAXIMALER_X_WERT);
		}

		return true;
	}

	public Boolean koordinateZIstImDefinitionsbereich(int koordinateZ) {

		if (koordinateZ <= 0) {
			throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ oder 0 sein.");
		}

		if (koordinateZ > MaterialKonstanten.MAXIMALER_Z_WERT) {
			throw new IllegalArgumentException(
					"Die maximal Material Dicke ist: " + MaterialKonstanten.MAXIMALER_Z_WERT);
		}

		return true;
	}

	public Boolean koordinateyMaxIstImDefinitionsbereich(int koordinatey) {

		if (koordinatey <= 0) {
			throw new IllegalArgumentException("Die Materialbreite darf nicht Negativ sein.");
		}

		if (koordinatey > MaterialKonstanten.MAXIMALER_Y_WERT) {
			throw new IllegalArgumentException(
					"Die maximal Material L�nge ist: " + MaterialKonstanten.MAXIMALER_Y_WERT);
		}

		return true;
	}

	public Boolean materialSorteIdIstVorhanden(int materialSorteId) {

		if (materialSorteId < 0) {
			throw new IllegalArgumentException("Die materialSorteId darf nicht Negativ sein.");
		}
		/**
		 * @TODO Abfrage einbauen die abfragt ob die Sorte in der Datenbank
		 *       vorhanden ist.
		 */

		return true;
	}

	

}