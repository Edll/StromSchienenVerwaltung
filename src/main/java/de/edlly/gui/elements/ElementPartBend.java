package de.edlly.gui.elements;

/**
 * TODO: Hinzufüge Button für Database!
 */

import de.edlly.db.*;
import de.edlly.gui.*;
import de.edlly.part.*;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FIXME: PART ID fürs eintragen -> Konzept?
 * 
 * @author Edlly java@edlly.de
 *
 */
public class ElementPartBend extends Element implements IElement {
    private JPanel panel = new JPanel();
    private JPanel formPanel = new JPanel();
    private IPartBend partBend;
    private double yMax;

    private BendTable bendTable = new BendTable();
    private JScrollPane scrollPane;

    private JComboBox inputRichtung;
    private JTextField inputAngel;
    private JTextField inputY;
    private JButton btnAdd;
    private JLabel lblName = new JLabel();
    private JLabel lblMaterialSize = new JLabel();
    private JLabel lblYMax = new JLabel();
    private JLabel lblProjektNr = new JLabel();
    private JButton btnSave;
    PartDataAdd partNew;

    public ElementPartBend() {
    }

    public JPanel createAndGet() {
	create();
	return panel;
    }

    public JPanel createAndGet(PartDataAdd partNew) {
	this.partNew = partNew;
	create();
	try {

	    this.yMax = this.partNew.getMaterialYMax();
	    this.lblName.setText("Name: " + this.partNew.getName());
	    this.lblYMax.setText("Maximale länge: " + Integer.toString(this.partNew.getMaterialYMax()));
	    this.lblMaterialSize.setText("Material Id: " + Integer.toString(this.partNew.getMaterialId()));
	    this.lblProjektNr.setText("Projekt Nr: " + Integer.toString(this.partNew.getProjektNr()));

	} catch (IllegalArgumentException e) {
	    userExceptionHandling(e.getLocalizedMessage());
	} catch (SQLiteException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	}
	return panel;
    }

    public void create() {
	try {
	    partBend = new PartBend();
	} catch (SQLiteException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	} catch (PartException e) {
	    userExceptionHandling(e.getLocalizedMessage());
	}

	panel.setLayout(new MigLayout("", "[grow,left]", "[20.00][][18.00][89.00,top][300px:n,grow,top][][]"));

	addHeader();
	addForm();
	addBendTable();
	addSave();

    }

    private void addHeader() {

	panel.add(lblName, "flowx,cell 0 1");
	panel.add(lblProjektNr, "flowx,cell 0 1");
	panel.add(lblYMax, "cell 0 1");
	panel.add(lblMaterialSize, "cell 0 1");
    }

    private void addForm() {
	formPanel.setLayout(new FormLayout(
		new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
			FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
			FormSpecs.DEFAULT_COLSPEC, },
		new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
			FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
			FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

	JLabel lblWinkel = new JLabel("Winkel");
	lblWinkel.setFont(Formatierung.eingabeFeldLabel());

	inputAngel = new JTextField();
	inputAngel.setColumns(10);
	inputAngel.setFont(Formatierung.eingabeFeldLabel());

	JLabel lblY = new JLabel("Y");
	lblY.setFont(Formatierung.eingabeFeldLabel());

	inputY = new JTextField();
	inputY.setColumns(10);
	inputY.setFont(Formatierung.eingabeFeldLabel());

	JLabel lblRichtung = new JLabel("Richtung");
	lblRichtung.setFont(Formatierung.eingabeFeldLabel());

	inputRichtung = new JComboBox();
	inputRichtung.setFont(Formatierung.eingabeFeldLabel());
	inputRichtung.setModel(new DefaultComboBoxModel(new String[] { "Vor", "Zurück" }));

	btnAdd = new JButton("Hinzufügen");
	btnAdd.setFont(Formatierung.eingabeFeldLabel());
	addActionWeiter();

	formPanel.add(lblWinkel, "2, 2");
	formPanel.add(inputAngel, "4, 2");
	formPanel.add(lblY, "2, 4");
	formPanel.add(inputY, "4, 4");
	formPanel.add(lblRichtung, "2, 6");
	formPanel.add(inputRichtung, "4, 6");
	formPanel.add(btnAdd, "6, 8");

	panel.add(formPanel, "cell 0 3,grow");
    }

    private void addBendTable() {
	scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setViewportView(bendTable.getTable());
	panel.add(scrollPane, "cell 0 4,alignx left,aligny top");
    }

    private void addSave() {
	btnSave = new JButton("Speichern");
	panel.add(btnSave, "cell 0 5");
	addActionSave();
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

		    IBend<Double> bend = new Bend<Double>(yMax, angelGet, YGet);
		    partBend.addBendSort(bend);

		    bendTable.addData(partBend);
		    bendTable.setTableModel();
		    scrollPane.setViewportView(bendTable.getTable());

		} catch (PartException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		} catch (NumberFormatException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		} catch (IllegalArgumentException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		}

		panel.validate();
		panel.repaint();
	    }
	});
    }

    public void addActionSave() {
	btnSave.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    partBend.addListToDB();
		} catch (PartException e) {
		    userExceptionHandling(e.getLocalizedMessage());
		}

	    }
	});
    }

}
