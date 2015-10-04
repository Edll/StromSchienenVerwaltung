package de.edlly.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.layout.*;
import net.miginfocom.swing.MigLayout;
import de.edlly.db.*;
import de.edlly.gui.*;
import de.edlly.material.*;
import de.edlly.part.*;

public class ElementPartNeu extends Element implements IElement {
    private JPanel panel;
    private MaterialTable table;
    private SQLiteConnect sqLite;

    private JTextField inputName;
    private JTextField inputProjektNr;
    private JButton btnWeiter;
    private int step;

    public ElementPartNeu() {
	panel = new JPanel();
	sqLite = new SQLiteConnect();
    }

    public JPanel createAndGet() {
	create();
	return panel;
    }

    public void create() {
	if (step == 1) {
	    panel.removeAll();
	} else {
	    try {
		addStepOne();
	    } catch (IllegalArgumentException e) {
		userExceptionHandling(e.getLocalizedMessage());
	    } catch (SQLiteException e) {
		systemExceptionHandling(e.getLocalizedMessage());
	    }
	}
    }

    public void addStepOne() throws IllegalArgumentException, SQLiteException {
	panel.setLayout(new MigLayout("", "[608.00px,left]", "[215px,top][400px:n,grow,top][center]"));

	JPanel formPanel = new JPanel();
	formPanel.setBounds(10, 43, 682, 66);
	formPanel
		.setLayout(new FormLayout(
			new ColumnSpec[] { FormSpecs.MIN_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, },
			new RowSpec[] { RowSpec.decode("12px"), RowSpec.decode("20px"), FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"), }));

	JLabel lblName = new JLabel("Name");
	lblName.setFont(Formatierung.eingabeFeldLabel());

	inputName = new JTextField();
	inputName.setText("");
	inputName.setColumns(30);

	JLabel lblProjektNummer = new JLabel("Projekt Nummer");
	lblProjektNummer.setFont(Formatierung.eingabeFeldLabel());

	inputProjektNr = new JTextField();
	inputProjektNr.setColumns(30);

	formPanel.add(lblName, "1, 2");
	formPanel.add(inputName, "3, 2, left, center");
	formPanel.add(lblProjektNummer, "1, 4");
	formPanel.add(inputProjektNr, "3, 4, left, center");

	btnWeiter = new JButton("Weiter");
	addActionWeiter();

	table = new MaterialTable();
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setViewportView(table.getMaterialTabel(false));

	panel.add(formPanel, "cell 0 0,alignx left,aligny top");
	panel.add(btnWeiter, "cell 0 2,alignx right,growy");
	panel.add(scrollPane, "cell 0 1,alignx left,aligny top");
    }

    public void addActionWeiter() {
	btnWeiter.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		step = 1;
		try {
		    create();
		    sqLite.dbConnect();

		    PartDataAdd partNew = new PartDataAdd(sqLite);
		    java.util.Date date = new java.util.Date();

		    // FIXME getMaterialID!
		    partNew.setData(inputName.getText(), table.getSelectedMaterialId(),
			    Integer.parseInt(inputProjektNr.getText()), date.getTime());

		    partNew.dbAdd();
		    sqLite.close();

		} catch (SQLiteException e) {
		    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben.");

		} catch (PartException e) {
		    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());

		} finally {
		    try {
			sqLite.close();
		    } catch (SQLiteException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		    }
		}
		panel.validate();
		panel.repaint();
	    }
	});
    }
}
