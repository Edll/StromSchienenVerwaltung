package de.edlly.material;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class MaterialTabelle {

    private Connection sqlConnection;

    public MaterialTabelle(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    public MaterialTabelleModel tabelleErstellen(boolean ausgeblendeteAnzeigen) throws SQLException {

	MaterialListe kompletteMaterialListe = new MaterialListe(sqlConnection);
	kompletteMaterialListe.setAusgeblendetDatenAnzeigen(true);

	MaterialTabelleModel materialTabellenModel = new MaterialTabelleModel();
	materialTabellenModel.addColumn("Id");
	materialTabellenModel.addColumn("Sorte");
	materialTabellenModel.addColumn("Größe");
	materialTabellenModel.addColumn("Maximale Länge");
	materialTabellenModel.addColumn("Sichtbar");

	Object[][] materialListe = kompletteMaterialListe.getMaterialListeFormatiert();

	for (int anzahlDerDatensatze = 0; anzahlDerDatensatze != materialListe.length; anzahlDerDatensatze++) {
	    materialTabellenModel.addRow(new Object[] { materialListe[anzahlDerDatensatze][0],
		    materialListe[anzahlDerDatensatze][1], materialListe[anzahlDerDatensatze][2],
		    materialListe[anzahlDerDatensatze][3], materialListe[anzahlDerDatensatze][4] });
	}
	return materialTabellenModel;
    }

    public class MaterialTabelleModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public int getColumnCount() {

	    return 5;
	}

	public boolean isCellEditable(int r, int c) {

	    return c == 4;
	}

	@SuppressWarnings("rawtypes")
	Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
		java.lang.Object.class, java.lang.Boolean.class, };

	public Class<?> getColumnClass(int columnIndex) {

	    return types[columnIndex];
	}

    }

}
