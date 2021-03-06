package de.edlly.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.gui.Element;
import de.edlly.gui.Format;
import de.edlly.gui.IElement;
import de.edlly.material.MaterialLoeschen;
import net.miginfocom.swing.MigLayout;

/**
 * *
 * 
 * @author Edlly
 *
 */

public class ElementMaterialListe extends Element implements IElement {

    private MaterialTable table;
    private MaterialLoeschen materialLoeschen;
    private JPanel panel;
    private JButton jBLoeschen;
    private SQLiteConnect sqLite;

    public ElementMaterialListe() {
        sqLite = new SQLiteConnect();
    }

    public JPanel createAndGet() {
        create();

        return panel;
    }

    public void create() {
        panel = new JPanel();
        panel.setLayout(new MigLayout("", "[grow,left]", "[grow,top][100px:n,top]"));

        addMaterialTable();
        addAuswahlLoeschen();
    }

    public void addMaterialTable() {
        try {
            table = new MaterialTable();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setViewportView(table.getMaterialTabel(true));
            panel.add(scrollPane, "cell 0 0,grow");

        } catch (IllegalArgumentException e) {

            userExceptionHandling(e.getLocalizedMessage());
        } catch (SQLiteException e) {

            systemExceptionHandling(e.getLocalizedMessage());
        }

    }

    public void addAuswahlLoeschen() {

        JPanel panel = new JPanel();

        jBLoeschen = new JButton("Makierte Löschen");
        jBLoeschen.setFont(Format.buttonFont());
        panel.add(jBLoeschen);

        jBLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int selectedId = table.getSelectedMaterialId();
                try {

                    sqLite.dbConnect();
                    materialLoeschen = new MaterialLoeschen(sqLite);
                    if (materialLoeschen.loschen(selectedId)) {
                        JOptionPane.showMessageDialog(null, "Das ausgewählte Material ist gelöscht worden.");
                    }
                    addMaterialTable();
                    sqLite.close();
                } catch (SQLiteException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        sqLite.close();
                    } catch (SQLiteException e) {

                        e.printStackTrace();
                    }
                }

            }
        });

        this.panel.add(panel, "cell 0 1,grow");
    }
}
