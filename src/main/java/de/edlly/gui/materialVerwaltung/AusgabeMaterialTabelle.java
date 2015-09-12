package de.edlly.gui.materialVerwaltung;

import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;

import de.edlly.material.MaterialTabelle;

/**
 * Material Tabellen erstellen, dass Tabellenmodell kommt aus der Klasse material.MaterialTabelle.
 * 
 * TODO: Das refresh des Models ist nicht gut gelöst so. Überarbeiten!
 * 
 * @author Edlly
 *
 */

public class AusgabeMaterialTabelle extends TabMaterialVerwaltung {
    private MaterialTabelle materialModel;
    private JTable materialTabelle;
    private JScrollPane tabellenBereich = new JScrollPane();

    public JPanel materialTabellePanel(int PositionX, int PositionY) throws SQLException {

	JPanel tabellenAnzeigeBereich = new JPanel();
	tabellenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	tabellenAnzeigeBereich.setLayout(null);
	tabellenAnzeigeBereich.setBounds(PositionX, PositionY, 738, 380);

	tabellenAnzeigeBereich.add(headerMaterialDatenbank());
	tabellenAnzeigeBereich.add(bereichMaterialDatenbank());

	return tabellenAnzeigeBereich;
    }

    public JScrollPane bereichMaterialDatenbank() throws SQLException {

	scrollpaneErstellen();

	try {
	    Connection sqlConnection;
	    sqlConnection = SQLiteConnect.dbConnection();

	    materialModel = new MaterialTabelle(sqlConnection);
	    materialTabelle = new JTable(materialModel.tabelleErstellen(true));

	    tabellenBereich.setViewportView(materialTabelle);

	    SQLiteConnect.closeSqlConnection(sqlConnection);
	} catch (SQLException sqlException) {

	    throw new SQLException(sqlException);
	}
	return tabellenBereich;
    }

    public void refreshMaterialTabelle() throws SQLException {
	try {
	    Connection sqlConnection;
	    sqlConnection = SQLiteConnect.dbConnection();

	    materialModel = new MaterialTabelle(sqlConnection);
	    materialTabelle = new JTable(materialModel.tabelleErstellen(true));
	    tabellenBereich.setViewportView(materialTabelle);

	    SQLiteConnect.closeSqlConnection(sqlConnection);
	} catch (SQLException sqlException) {
	    throw new SQLException(sqlException);
	}
    }
    

    private void scrollpaneErstellen() {
	tabellenBereich = new JScrollPane();
	tabellenBereich.setBounds(10, 40, 718, 320);
	tabellenBereich.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	tabellenBereich.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JLabel headerMaterialDatenbank() {

	JLabel header = new JLabel("Material Datenbank");
	header.setFont(Formatierung.headerFont());
	header.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 162, 20);

	return header;
    }
}
