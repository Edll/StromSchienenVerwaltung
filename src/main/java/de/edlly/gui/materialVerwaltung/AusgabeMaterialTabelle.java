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
 * 
 * TODO: Code ist Wip muss neu strukturiert werden!
 * 
 * 
 * @author Edlly
 *
 */

public class AusgabeMaterialTabelle extends TabMaterialVerwaltung {

    public AusgabeMaterialTabelle(JScrollPane tabellenBereich) {
	this.tabellenBereich = tabellenBereich;
    }

    public JPanel materialTabellePanel(int PositionX, int PositionY) {
	JPanel tabellenAnzeigeBereich = new JPanel();
	tabellenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	tabellenAnzeigeBereich.setLayout(null);
	tabellenAnzeigeBereich.setBounds(PositionX, PositionY, 738, 380);
	tabellenAnzeigeBereich.add(headerMaterialDatenbank());
	tabellenAnzeigeBereich.add(bereichMaterialDatenbank());

	return tabellenAnzeigeBereich;
    }

    public JLabel headerMaterialDatenbank() {

	JLabel header = new JLabel("Material Datenbank");
	header.setFont(Formatierung.headerFont());
	header.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 162, 20);

	return header;
    }

    public JScrollPane bereichMaterialDatenbank() {

	try {
	    Connection sqlConnection;
	    sqlConnection = SQLiteConnect.dbConnection();

	    tabellenBereich = new JScrollPane();
	    tabellenBereich.setBounds(10, 40, 718, 320);
	    tabellenBereich.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    tabellenBereich.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    materialModel = new MaterialTabelle(sqlConnection);
	    materialTabelleJTable = new JTable(materialModel.tabelleErstellen(true));
	    tabellenBereich.setViewportView(materialTabelleJTable);
	    System.out.println("breich =>" + tabellenBereich);
	    SQLiteConnect.closeSqlConnection(sqlConnection);
	} catch (SQLException sqlException) {
	    sqlException.printStackTrace();
	}
	return tabellenBereich;
    }

}
