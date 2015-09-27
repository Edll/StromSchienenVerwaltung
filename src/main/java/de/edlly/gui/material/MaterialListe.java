package de.edlly.gui.material;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Element;
import de.edlly.gui.Formatierung;
import de.edlly.gui.IElement;
import de.edlly.material.MaterialLoeschen;
import de.edlly.material.MaterialTabelle;

/**
 * Material Tabellen erstellen, dass Tabellenmodell kommt aus der Klasse material.MaterialTabelle.
 * 
 * TODO: Das refresh des Models ist nicht gut gelöst so. Überarbeiten!
 * 
 * @author Edlly
 *
 */

public class MaterialListe extends Element implements IElement  {
    
    
    private MaterialTabelle materialModel;
    private JTable materialTabelle;
    private MaterialListe ausgabeMaterialTabelle;
    private JButton makiertesMaterialLoeschen;
    private MaterialLoeschen materialLoeschen;
    private JScrollPane tabellenBereich;
    private SQLiteConnect sqlConnection;

    public MaterialListe() {
	 tabellenBereich = new JScrollPane();
	 sqlConnection = new SQLiteConnect();
    }

    public JPanel createAndGet() {

	JPanel tabellenAnzeigeBereich = new JPanel();
	tabellenAnzeigeBereich.setLayout(null);
	
	tabellenAnzeigeBereich.add(headerMaterialDatenbank());
	try {
	    tabellenAnzeigeBereich.add(bereichMaterialDatenbank());
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	tabellenAnzeigeBereich.add(auswahlLoeschenPanel(550, 420));
	return tabellenAnzeigeBereich;
    }

    public JPanel auswahlLoeschenPanel(int positionX, int positionY) {

	JPanel materialLoeschenAnzeigeBereich = new JPanel();
	materialLoeschenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialLoeschenAnzeigeBereich.setLayout(null);
	materialLoeschenAnzeigeBereich.setBounds(550, 420, 170, 100);

	makiertesMaterialLoeschen = new JButton("Makierte Löschen");
	makiertesMaterialLoeschen.setFont(Formatierung.buttonFont());
	makiertesMaterialLoeschen.setBounds(10, 10, 150, 23);
	materialLoeschenAnzeigeBereich.add(makiertesMaterialLoeschen);

	makiertesMaterialLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		int selectedId = ausgabeMaterialTabelle.getSelectedMaterialId();
		try {

		    sqlConnection.dbConnect();
		    materialLoeschen = new MaterialLoeschen(sqlConnection);
		    if (materialLoeschen.loschen(selectedId)) {
			JOptionPane.showMessageDialog(null, "Das ausgewählte Material ist gelöscht worden.");
			ausgabeMaterialTabelle.refreshMaterialTabelle();
		    }
		    sqlConnection.close();
		} catch (SQLException e) {
		    e.printStackTrace();

		} finally {
		    try {
			sqlConnection.close();
		    } catch (SQLException e) {

			e.printStackTrace();
		    }
		}

	    }
	});

	return materialLoeschenAnzeigeBereich;
    }

    public JScrollPane bereichMaterialDatenbank() throws SQLException {

	scrollpaneErstellen();

	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();

	materialModel = new MaterialTabelle(sqlConnection);
	materialTabelle = new JTable(materialModel.tabelleErstellen(true));

	tabellenBereich.setViewportView(materialTabelle);

	sqlConnection.close();

	return tabellenBereich;
    }

    public void refreshMaterialTabelle() throws SQLException {

	SQLiteConnect sqlConnection = new SQLiteConnect();
	sqlConnection.dbConnect();

	materialModel = new MaterialTabelle(sqlConnection);
	materialTabelle = new JTable(materialModel.tabelleErstellen(true));
	tabellenBereich.setViewportView(materialTabelle);

	sqlConnection.close();

    }

    public int getSelectedMaterialId() {
	int materialId = Integer.parseInt((materialTabelle.getValueAt(materialTabelle.getSelectedRow(), 0)).toString());

	if (materialId == -1) {
	    materialId = 0;
	}
	return materialId;
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

    public void create() {
	// TODO Auto-generated method stub
	
    }

}
