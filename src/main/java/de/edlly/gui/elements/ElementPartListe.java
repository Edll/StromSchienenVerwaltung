package de.edlly.gui.elements;

import javax.swing.*;

import de.edlly.db.SQLiteException;
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

    /**
     * @wbp.parser.entryPoint
     */
    public JPanel createAndGet() {
        create();
        return panel;
    }

    public void create() {
        panel = new JPanel();
        panel.setLayout(new MigLayout("fill", Format.MIG_ELEMENT_PANEL_LEFT, Format.MIG_ELEMENT_PANEL_TOP));
        panel.setBackground(Format.BGCOLOR);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(Format.BGCOLOR);
        try {
            scrollPane.setViewportView(partTable.getPartTable());
            panel.add(scrollPane, "grow");

        } catch (SQLiteException e) {
            systemExceptionHandling(e.getLocalizedMessage());
        } catch (PartException e) {
            userExceptionHandling(e.getLocalizedMessage());
        }

    }

}
