package de.edlly.gui.elements;

import java.sql.SQLException;
import javax.swing.*;

import de.edlly.gui.*;
import de.edlly.part.*;
import net.miginfocom.swing.MigLayout;

/**
 * Anzeige der Werkstück Verwaltung
 * 
 * @author Edlly java@edlly.de
 *
 */
public class ElementPartListe extends Element implements IElement {
    private PartTable partTable;
    private JPanel panel;

    public ElementPartListe() {
	partTable = new PartTable();
    }

    public JPanel createAndGet() {
	create();

	return panel;
    }

    public void create() {
	panel = new JPanel();
	panel.setLayout(new MigLayout("", Formatierung.MIG_ELEMENT_PANEL_LEFT, Formatierung.MIG_ELEMENT_PANEL_TOP));

	JScrollPane scrollPane = new JScrollPane();

	try {
	    scrollPane.setViewportView(partTable.getPartTable());
	} catch (SQLException e) {
	    systemExceptionHandling(e.getLocalizedMessage());
	} catch (PartException e) {
	    userExceptionHandling(e.getLocalizedMessage());
	}
	panel.add(scrollPane);

    }

}
