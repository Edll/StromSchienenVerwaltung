package de.edlly.gui.elements;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.part.IPartData;
import de.edlly.part.PartData;
import de.edlly.part.PartException;
import de.edlly.part.PartUtil;

public class PartTable {
    private SQLiteConnect sqLite;
    private IPartData datensatz;
    private JTable partTable;

    public PartTable() {
	sqLite = new SQLiteConnect();
    }

    public JTable getPartTable() throws SQLiteException, PartException {
	makePartTable();
	return partTable;
    }

    public void makePartTable() throws SQLiteException, PartException {
	PartTableModel model = new PartTableModel();

	model.addColumn("Id");
	model.addColumn("Name");
	model.addColumn("Material");
	model.addColumn("Projekt Nr.");
	model.addColumn("Erstellt am");

	sqLite.dbConnect();

	datensatz = new PartData(sqLite);
	List<IPartData> list = datensatz.getDataList();

	for (int i = 0; i < list.size(); i++) {

	    IPartData d = list.get(i);

	    model.addRow(new Object[] { d.getId(), d.getName(), d.getMaterialId(), d.getProjektNr(),
		    PartUtil.erstellDatumFormatieren(d.getErstellDatum()) });

	}
	sqLite.close();

	partTable = new JTable(model);
	partTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    public class PartTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 3076396854047535023L;

	@Override
	public int getColumnCount() {

	    return 5;
	}

	@Override
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
