package de.edlly.gui.elements;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.material.MaterialListe;

public class MaterialTable {
    private SQLiteConnect sqLite;
    private MaterialListe datensatz;
    private JTable materialTable;
    private boolean ausgeblendeteAnzeigen;

    public MaterialTable() {
        sqLite = new SQLiteConnect();
    }

    public JTable getMaterialTabel(boolean ausgeblendeteAnzeigen) throws IllegalArgumentException, SQLiteException {
        this.ausgeblendeteAnzeigen = ausgeblendeteAnzeigen;
        makePartTable();

        return materialTable;
    }

    public void makePartTable() throws IllegalArgumentException, SQLiteException {
        MaterialTabelModel model = new MaterialTabelModel();
        model.addColumn("Id");
        model.addColumn("Sorte");
        model.addColumn("Größe");
        model.addColumn("Maximale Länge");
        if (ausgeblendeteAnzeigen) {
            model.addColumn("Sichtbar");
        }

        sqLite.dbConnect();

        datensatz = new MaterialListe(sqLite);
        datensatz.setAusgeblendetDatenAnzeigen(ausgeblendeteAnzeigen);

        Object[][] materialListe = datensatz.getMaterialListeFormatiert();

        if (ausgeblendeteAnzeigen) {
            for (int anzahlDerDatensatze = 0; anzahlDerDatensatze != materialListe.length; anzahlDerDatensatze++) {
                model.addRow(new Object[] { materialListe[anzahlDerDatensatze][0],
                        materialListe[anzahlDerDatensatze][1], materialListe[anzahlDerDatensatze][2],
                        materialListe[anzahlDerDatensatze][3], materialListe[anzahlDerDatensatze][4] });
            }
        } else {
            for (int anzahlDerDatensatze = 0; anzahlDerDatensatze != materialListe.length; anzahlDerDatensatze++) {
                model.addRow(
                        new Object[] { materialListe[anzahlDerDatensatze][0], materialListe[anzahlDerDatensatze][1],
                                materialListe[anzahlDerDatensatze][2], materialListe[anzahlDerDatensatze][3] });
            }
        }

        materialTable = new JTable(model);
        materialTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = materialTable.getTableHeader();
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.black);
        sqLite.close();
    }

    public int getSelectedMaterialId() {
        int materialId = Integer.parseInt((materialTable.getValueAt(materialTable.getSelectedRow(), 0)).toString());

        if (materialId == -1) {
            materialId = 0;
        }
        return materialId;
    }

    public class MaterialTabelModel extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        public int getColumnCount() {
            if (ausgeblendeteAnzeigen) {
                return 5;
            } else {
                return 4;
            }

        }

        public boolean isCellEditable(int r, int c) {
            if (ausgeblendeteAnzeigen) {
                return c == 4;
            } else {
                return c == 0;
            }

        }

        @SuppressWarnings("rawtypes")
        Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class, };

        public Class<?> getColumnClass(int columnIndex) {

            return types[columnIndex];
        }

    }

}
