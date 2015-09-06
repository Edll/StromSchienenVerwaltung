package de.edlly.material;

import java.sql.*;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MaterialTabelle {

    private Connection sqlConnection;

    public MaterialTabelle(Connection sqlConnection) {
	this.sqlConnection = sqlConnection;
    }

    
    
    

    public JTable tabelleErstellen(boolean ausgeblendeteAnzeigen) throws SQLException {

	MaterialListe kompletteMaterialListe = new MaterialListe(sqlConnection);

	// Erstellen des Table Models
	MaterialTabelleModel materialTabellenModel = new MaterialTabelleModel();
	materialTabellenModel.addColumn("Id");
	materialTabellenModel.addColumn("Sorte");
	materialTabellenModel.addColumn("Größe");
	materialTabellenModel.addColumn("Maximale Länge");
	materialTabellenModel.addColumn("Sichtbar");
	
	Object[][] materialListe = kompletteMaterialListe.getMaterialListeFormatiert(ausgeblendeteAnzeigen);
	
	
	for(int anzahlDerDatensatze=0; anzahlDerDatensatze != materialListe.length; anzahlDerDatensatze++){
	    materialTabellenModel.addRow(new Object[] { materialListe[anzahlDerDatensatze][0], materialListe[anzahlDerDatensatze][1], materialListe[anzahlDerDatensatze][2], materialListe[anzahlDerDatensatze][3], materialListe[anzahlDerDatensatze][4] }); 
	}
	

	JTable materialTabelle = new JTable(materialTabellenModel);

	return materialTabelle;
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
	// TODO: letzte klasse zu Boolean für checkbox ändern
	Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
		java.lang.Object.class, java.lang.Boolean.class, };

	public Class<?> getColumnClass(int columnIndex) {

	    return types[columnIndex];
	}

    }

}
