package de.edlly.gui.elements;

import javax.swing.table.DefaultTableModel;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.gui.*;
import de.edlly.part.Bend;
import de.edlly.part.BendTable;
import de.edlly.part.IBend;
import de.edlly.part.IPartBend;
import de.edlly.part.PartBend;
import de.edlly.part.PartException;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementsPartBend extends Element implements IElement {
    private JPanel panel;
    private JPanel formPanel;
    private JComboBox inputRichtung;
    private SQLiteConnect sqLite;
    private JTextField inputAngel;
    private JTextField inputY;
    private JTable table;
    private BendTable bendTable;
    private JButton btnAdd;
    private IPartBend partBend;
    private JScrollPane scrollPane;

    public ElementsPartBend() {
	panel = new JPanel();
	formPanel = new JPanel();
	sqLite = new SQLiteConnect();
	bendTable = new BendTable();
	table = bendTable.get();
    }

    public JPanel createAndGet() {
	create();
	return panel;
    }

    public void create() {
	try {
	    sqLite.dbConnect();
	    partBend = new PartBend(sqLite);
	} catch (SQLiteException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	} catch (PartException e) {
	    userExceptionHandling(e.getLocalizedMessage());
	}
	panel.setLayout(new MigLayout("", "[grow,left]", "[top][300px:n,grow,top]"));

	scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setViewportView(table);
	panel.add(scrollPane, "cell 0 1,alignx left,aligny top");

	addForm();

    }

    public void addForm() {
	panel.add(formPanel, "cell 0 0,grow");
	formPanel.setLayout(new FormLayout(
		new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
			FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
			FormSpecs.DEFAULT_COLSPEC, },
		new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
			FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
			FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

	JLabel lblWinkel = new JLabel("Winkel");
	formPanel.add(lblWinkel, "2, 2");

	inputAngel = new JTextField();
	formPanel.add(inputAngel, "4, 2");
	inputAngel.setColumns(10);

	JLabel lblY = new JLabel("Y");
	formPanel.add(lblY, "2, 4");

	inputY = new JTextField();
	formPanel.add(inputY, "4, 4");
	inputY.setColumns(10);

	JLabel lblRichtung = new JLabel("Richtung");
	formPanel.add(lblRichtung, "2, 6");

	inputRichtung = new JComboBox();
	formPanel.add(inputRichtung, "4, 6");
	inputRichtung.setModel(new DefaultComboBoxModel(new String[] { "Vor", "Zurück" }));

	btnAdd = new JButton("Hinzufügen");
	formPanel.add(btnAdd, "6, 8");
	addActionWeiter();
    }

    public void addActionWeiter() {
	btnAdd.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    double angelGet = Double.parseDouble(inputAngel.getText());
		    double YGet = Double.parseDouble(inputY.getText());
		    int richtung = inputRichtung.getSelectedIndex();

		    if (richtung == 1) {
			angelGet = angelGet * -1;
		    }

		    IBend<Double> bend = new Bend<Double>(4000D, angelGet, YGet);
		    partBend.addBend(bend);
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.addRow(new Object[] { YGet, angelGet, inputRichtung.getSelectedItem() });

		} catch (PartException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		} catch (NumberFormatException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		}

		panel.validate();
		panel.repaint();
	    }
	});
    }

}
