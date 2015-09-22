package de.edlly.gui.werkstueckVerwaltung;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;
import werkstueck.IWerkstueckDatensatz;
import werkstueck.WerkstueckDatensatz;
import werkstueck.WerkstueckException;

/**
 * Anzeige der Werkstück Verwaltung
 * 
 * @author Edlly java@edlly.de
 *
 */
public class WerkstueckVerwaltung {
    IWerkstueckDatensatz<?> datensatz;
    SQLiteConnect sqlConnection = new SQLiteConnect();
    private JTable materialTabelle;

    public JPanel werkstueckVerwaltungsPanel() throws SQLException, WerkstueckException {

	JPanel werkstueckVerwaltung = new JPanel();
	werkstueckVerwaltung.setLayout(null);

	headerWerkstueckVerwaltung(werkstueckVerwaltung);
	tabelleWerkstuecke(werkstueckVerwaltung);

	return werkstueckVerwaltung;
    }

    private void headerWerkstueckVerwaltung(JPanel werkstueckVerwaltung) {
	JLabel headerWerkstueckVerwaltung = new JLabel("Werkst\u00FCck Verwaltung");
	headerWerkstueckVerwaltung.setBounds(Formatierung.HEADER_POSITION_X, Formatierung.HEADER_POSITION_Y, 200, 20);
	headerWerkstueckVerwaltung.setFont(Formatierung.headerFont());
	werkstueckVerwaltung.add(headerWerkstueckVerwaltung);
    }

    private void tabelleWerkstuecke(JPanel werkstueckVerwaltung) throws SQLException, WerkstueckException {

	sqlConnection.dbConnect();
	datensatz = new WerkstueckDatensatz<IWerkstueckDatensatz<?>>(sqlConnection);

	List<IWerkstueckDatensatz<?>> list = datensatz.getDatensatz(3);

	WerkstueckTabelleModel materialTabellenModel = new WerkstueckTabelleModel();
	materialTabellenModel.addColumn("Id");
	materialTabellenModel.addColumn("Name");
	materialTabellenModel.addColumn("Material");
	materialTabellenModel.addColumn("Projekt");
	materialTabellenModel.addColumn("Erstellt am");

	IWerkstueckDatensatz<?> d = list.get(0);
	materialTabellenModel.addRow(
		new Object[] { d.getId(), d.getName(), d.getMaterialId(), d.getProjektNr(), d.getErstellDatum() });

	sqlConnection.close();
	materialTabelle = new JTable(materialTabellenModel);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 40, 747, 424);
	werkstueckVerwaltung.add(scrollPane);

	scrollPane.setViewportView(materialTabelle);
    }

    public class WerkstueckTabelleModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public int getColumnCount() {

	    return 5;
	}

	public boolean isCellEditable(int r, int c) {

	    return c == 4;
	}

	@SuppressWarnings("rawtypes")
	Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
		java.lang.Object.class, java.lang.Object.class, };

	public Class<?> getColumnClass(int columnIndex) {

	    return types[columnIndex];
	}

    }
}
