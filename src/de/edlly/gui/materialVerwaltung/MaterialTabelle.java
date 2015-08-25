package de.edlly.gui.materialVerwaltung;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;
import de.edlly.material.DbAbfrage;

/**
 * material.Manager Stellt die Anzeige, Tabelle, und Abfragen für den Material Manager zu Verfügung.
 * 
 * Attribute: Connection SqlConn: Database Connection aus aufgerufenen Klasse
 * 
 * UnterKlassen: ManagerModel das Tabellen Render Model für die Ausgabe Table
 * 
 * Methoden: AusgabeTable(): gibt die Darstellung einer
 * 
 * @author Edlly
 *
 */

public class MaterialTabelle {

    private JScrollPane tabellenBereich;

    public MaterialTabelle() {

    }

    public JPanel tabellenAnzeigeBereich(int PositionX, int PositionY) {

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

	tabellenBereich = new JScrollPane();
	tabellenBereich.setBounds(10, 40, 718, 320);
	tabellenBereich.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	tabellenBereich.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	MaterialTabelle materialTabelle = new MaterialTabelle();
	tabellenBereich.setViewportView(materialTabelle.AusgabeTable());

	return tabellenBereich;
    }

    /**
     * Erstellt aus der Datenbank Material eine Table
     * 
     * @return JTable
     */
    public JTable AusgabeTable() {

	// Abfrage des Materials aus der DB
	DbAbfrage MaterialSql = new DbAbfrage();

	// Erstellen des Table Models
	ManagerModel JTableWerkstueckWerteModel = new ManagerModel();
	JTableWerkstueckWerteModel.addColumn("Id");
	JTableWerkstueckWerteModel.addColumn("Sorte");
	JTableWerkstueckWerteModel.addColumn("Größe");
	JTableWerkstueckWerteModel.addColumn("Maximale Länge");
	JTableWerkstueckWerteModel.addColumn("Benutzen");

	String[][] MaterialDBData = MaterialSql.SelectTableFormat();
	// NullPointerException nicht auslösen
	if (MaterialDBData != null) {
	    int Arraylength = MaterialDBData.length;

	    for (int i = 0; i != Arraylength; i++) {
		Boolean checkbox = Boolean.TRUE;

		if (Integer.parseInt(MaterialDBData[i][4]) == 0) {
		    checkbox = Boolean.FALSE;
		}

		JTableWerkstueckWerteModel.addRow(new Object[] { MaterialDBData[i][0], MaterialDBData[i][1],
			MaterialDBData[i][2], MaterialDBData[i][3], checkbox });
	    }
	}

	JTable JTableWerkstueckWerte = new JTable(JTableWerkstueckWerteModel);

	JTableWerkstueckWerte.getModel().addTableModelListener(new TableModelListener() {

	    // Checkboxen abfrage für die in der Tabelle vorhandenen Werkstücke
	    @Override
	    public void tableChanged(TableModelEvent e) {

		JTableWerkstueckWerte.getSelectedRow();

		for (int i = 0; i < JTableWerkstueckWerte.getRowCount(); i++) {
		    if ((Boolean) JTableWerkstueckWerte.getValueAt(i, 4)) {
			// Update der Sichtbarkeit des Materials im System
			de.edlly.material.NeuerMaterialDatensatz UpdateSql = new de.edlly.material.NeuerMaterialDatensatz(SQLiteConnect.dbConnection());
			int id = Integer.parseInt(
				(String) JTableWerkstueckWerte.getValueAt(JTableWerkstueckWerte.getSelectedRow(), 0));
			int value = 0;
			if ((Boolean) JTableWerkstueckWerte.getValueAt(JTableWerkstueckWerte.getSelectedRow(), 4)) {
			    value = 1;
			}
			UpdateSql.updateVisibly(id, value);
			break;
		    }

		}
	    }

	});

	return JTableWerkstueckWerte;

    }

    // Eigenes TableModel um die Zellen nicht Editierbar zu machen und Boolean
    // für die Checkbox zu erzwingen.
    public class ManagerModel extends DefaultTableModel {
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
