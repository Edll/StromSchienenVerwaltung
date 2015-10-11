package de.edlly.gui.elements;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.edlly.bend.IBend;
import de.edlly.bend.IBendList;

public class BendTable extends JTable {
    private static final long serialVersionUID = -5081097819010063413L;
    private JTable bendTable;
    private DefaultTableModel model;

    public BendTable() {
	createTable();
	addHeadColumn();
	setTableModel();
    }

    public JTable getTable() {
	return bendTable;
    }

    public void createTable() {
	bendTable = new JTable();
	bendTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    public void setTableModel() {
	bendTable.setModel(model);
    }

    public void addData(IBendList bend) {
	List<IBend<?>> bendList = bend.getBends();

	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	    model.removeRow(i);
	}

	for (int i = 0; i < bendList.size(); i++) {
	    Double y = bendList.get(i).getY().doubleValue();
	    Double angel = bendList.get(i).getAngel().doubleValue();
	    String richtung = "Vor";

	    if (angel < 0) {
		angel = angel * -1;
		richtung = "Zurück";
	    }
	    model.addRow(new Object[] { y, angel, richtung });
	}
    }

    public void addHeadColumn() {
	model = new DefaultTableModel();
	model.addColumn("Y Position");
	model.addColumn("Winkel");
	model.addColumn("Richtung");
    }

}
