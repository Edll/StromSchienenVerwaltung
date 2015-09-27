package de.edlly.material;

import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.edlly.db.SQLiteConnect;

public class MaterialTable {
    private SQLiteConnect sqLite;
    private MaterialListe datensatz;
    private JTable materialTable;
    private boolean ausgeblendeteAnzeigen;

    public MaterialTable() {
	sqLite = new SQLiteConnect();
    }

    public JTable getMaterialTabel(boolean ausgeblendeteAnzeigen) throws IllegalArgumentException, SQLException {
	this.ausgeblendeteAnzeigen = ausgeblendeteAnzeigen;
	makePartTable();

	return materialTable;
    }

    public void makePartTable() throws IllegalArgumentException, SQLException {
	MaterialTabelModel model = new MaterialTabelModel();
	model.addColumn("Id");
	model.addColumn("Sorte");
	model.addColumn("Größe");
	model.addColumn("Maximale Länge");
	model.addColumn("Sichtbar");

	sqLite.dbConnect();

	datensatz = new MaterialListe(sqLite);
	datensatz.setAusgeblendetDatenAnzeigen(ausgeblendeteAnzeigen);

	Object[][] materialListe = datensatz.getMaterialListeFormatiert();

	for (int anzahlDerDatensatze = 0; anzahlDerDatensatze != materialListe.length; anzahlDerDatensatze++) {
	    model.addRow(new Object[] { materialListe[anzahlDerDatensatze][0], materialListe[anzahlDerDatensatze][1],
		    materialListe[anzahlDerDatensatze][2], materialListe[anzahlDerDatensatze][3],
		    materialListe[anzahlDerDatensatze][4] });
	}

	materialTable = new JTable(model);
	materialTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    }

    public class MaterialTabelModel extends DefaultTableModel {

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
