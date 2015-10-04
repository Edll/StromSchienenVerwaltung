package de.edlly.part;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BendTable {
    private JTable bendTable;

    public JTable get() {
	make();

	return bendTable;
    }

    public void make() {
	DefaultTableModel model = new DefaultTableModel();
	model.addColumn("Y Position");
	model.addColumn("Winkel");
	model.addColumn("Richtung");

	bendTable = new JTable(model);
	bendTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    }
}
