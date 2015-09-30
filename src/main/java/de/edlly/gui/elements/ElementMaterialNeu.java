package de.edlly.gui.elements;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;

import de.edlly.db.*;
import de.edlly.gui.Element;
import de.edlly.gui.Formatierung;
import de.edlly.gui.IElement;
import de.edlly.material.Material;
import de.edlly.material.MaterialSorte;
import de.edlly.material.NeuerMaterialDatensatz;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Erzeugt ein JPanel das die Eingabefelder für einen neuen Datensatz beinhaltet. Enthält eine ActionListener für den
 * "+" Knopf der die Daten in das Objekt f�r einen neuen Material Datensatz legt und dann ein Anlegen des Datensatz
 * auslöst.
 * 
 * TODO: Form Layout!
 * 
 * @author Edlly java@edlly.de
 *
 */

public class ElementMaterialNeu extends Element implements IElement {
    private JPanel panel;
    private SQLiteConnect sqLite;
    private MaterialSorte materialSorteListe;
    String[] materialListe;
    private JTextField inputX;
    private JTextField inputZ;
    private JTextField inputY;
    private JButton save;
    private JComboBox sortenAuswahl;
    private JPanel formPanel;

    public ElementMaterialNeu() {
	panel = new JPanel();
	formPanel = new JPanel();
	sqLite = new SQLiteConnect();

    }

    public JPanel createAndGet() {
	create();
	return panel;
    }

    public void create() {
	panel.setLayout(new MigLayout("", "[grow,left]", "[grow,top]"));

	try {
	    createForm();
	    actionMaterialHinzu();

	} catch (SQLiteException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	}

    }

    public void actionMaterialHinzu() {

	save.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {

		try {
		    sqLite.dbConnect();
		    MaterialSorte materialSorteId = new MaterialSorte(sqLite);

		    int MaterialSorteSelectId = materialSorteId
			    .getMaterialSorteId((String) sortenAuswahl.getSelectedItem());

		    int x = Integer.parseInt(inputX.getText());
		    int z = Integer.parseInt(inputZ.getText());
		    int yMax = Integer.parseInt(inputY.getText());

		    NeuerMaterialDatensatz MaterialDatensatzAnlegen = new NeuerMaterialDatensatz(sqLite);
		    MaterialDatensatzAnlegen.setMaterialDaten(x, z, yMax, MaterialSorteSelectId);

		    if (MaterialDatensatzAnlegen.datensatzAusObjektWertenAnlegen()) {
			userSuccessHandling("Das neue Material ist erfolgreich eingefügt worden.");
		    }

		    sqLite.close();
		} catch (NumberFormatException e) {

		    userExceptionHandling("Bitte eine gültige Zahl eingeben.");
		} catch (IllegalArgumentException e) {
		    userExceptionHandling(e.getLocalizedMessage());

		} catch (SQLiteException e) {
		    systemExceptionHandling(e.getLocalizedMessage());
		} finally {
		    try {
			sqLite.close();
		    } catch (SQLiteException e) {
			systemExceptionHandling(e.getLocalizedMessage());
		    }
		}

	    }
	});
    }

    public void createForm() throws SQLiteException {

	formPanel.setLayout(new FormLayout(
		new ColumnSpec[] { ColumnSpec.decode("left:max(69dlu;min)"),
			ColumnSpec.decode("left:max(85dlu;default)"), },
		new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
			FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
			FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
			FormSpecs.DEFAULT_ROWSPEC, }));

	JLabel lblSortenAuswahl = new JLabel("Material Sorte");
	lblSortenAuswahl.setFont(Formatierung.eingabeFeldLabel());

	materialSortenListeAbfragen();
	sortenAuswahl = new JComboBox(materialListe);
	sortenAuswahl.setFont(Formatierung.eingabeFeldLabel());

	JLabel lblInputX = new JLabel("Breite");
	lblInputX.setFont(Formatierung.eingabeFeldLabel());

	inputX = new JTextField();
	inputX.setToolTipText("Maximal Breite " + Material.MAXIMALER_X_WERT + " mm");
	inputX.setColumns(10);

	JLabel lblInputeZ = new JLabel("Dicke");
	lblInputeZ.setFont(Formatierung.eingabeFeldLabel());

	inputZ = new JTextField();
	inputZ.setToolTipText("Maximal Dicke " + Material.MAXIMALER_Z_WERT + " mm");
	inputZ.setColumns(10);

	JLabel lblInputeY = new JLabel("Maximale Länge");
	lblInputeY.setFont(Formatierung.eingabeFeldLabel());

	inputY = new JTextField();
	inputY.setToolTipText("Maximal Länge " + Material.MAXIMALER_Y_WERT + " mm");
	inputY.setColumns(10);

	save = new JButton("Speichern");
	save.setFont(Formatierung.buttonFont());

	formPanel.add(lblSortenAuswahl, "1, 2, default, top");
	formPanel.add(sortenAuswahl, "2, 2, default, center");
	formPanel.add(lblInputX, "1, 4, default, top");
	formPanel.add(inputX, "2, 4, default, center");
	formPanel.add(lblInputeZ, "1, 6, default, top");
	formPanel.add(inputZ, "2, 6, default, center");
	formPanel.add(lblInputeY, "1, 8, default, top");
	formPanel.add(inputY, "2, 8, left, center");
	formPanel.add(save, "1, 10, default, top");

	panel.add(formPanel, "flowx,cell 0 0");
    }

    private void materialSortenListeAbfragen() throws SQLiteException, IllegalArgumentException {
	sqLite = new SQLiteConnect();
	sqLite.dbConnect();
	materialSorteListe = new MaterialSorte(sqLite);
	materialListe = materialSorteListe.materialSorteNamensListe();
	sqLite.close();
    }
}
