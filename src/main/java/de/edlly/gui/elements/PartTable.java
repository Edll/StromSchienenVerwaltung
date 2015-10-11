package de.edlly.gui.elements;

import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.gui.Format;
import de.edlly.part.IPart;
import de.edlly.part.IPartList;
import de.edlly.part.PartList;
import de.edlly.part.PartException;
import de.edlly.part.PartUtil;

public class PartTable {
    private SQLiteConnect sqLite;
    private IPartList datensatz;
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

	datensatz = new PartList(sqLite);
	
	List<IPart> list = datensatz.getDataListSort();

	for (int i = 0; i < list.size(); i++) {

	    IPart d = list.get(i);
	    model.addRow(new Object[] { d.getId(), d.getName(), d.getMaterialId(), d.getProjektNr(),
		    PartUtil.erstellDatumFormatieren(d.getErstellDatum()) });
	}
	sqLite.close();

	partTable = new JTable(model);

	partTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	
	partTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	partTable.getColumnModel().getColumn(0).setPreferredWidth(20);
	partTable.getColumnModel().getColumn(1).setPreferredWidth(400);
	partTable.getColumnModel().getColumn(2).setPreferredWidth(150);
	partTable.getColumnModel().getColumn(3).setPreferredWidth(200);
	partTable.getColumnModel().getColumn(4).setPreferredWidth(120);
	partTable.setBackground(Format.BGCOLOR);
	
	JTableHeader header = partTable.getTableHeader();
	      header.setBackground(Color.LIGHT_GRAY);
	      header.setForeground(Color.black);
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
