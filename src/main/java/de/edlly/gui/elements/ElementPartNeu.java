package de.edlly.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.layout.*;
import net.miginfocom.swing.MigLayout;
import de.edlly.db.*;
import de.edlly.gui.*;
import de.edlly.part.*;

public class ElementPartNeu extends Element implements IElement {
    private JPanel panel;
    private ElementPartBend panelBend;
    private IPart partNew;
    private MaterialTable table;
    private SQLiteConnect sqLite;

    private JTextField inputName;
    private JTextField inputProjektNr;
    private JButton btnWeiter;
    private int step;

    public ElementPartNeu() {
        panel = new JPanel();
        panelBend = new ElementPartBend();
        sqLite = new SQLiteConnect();
    }

    public JPanel createAndGet() {
        create();

        return panel;
    }

    public void create() {
        if (step == 1) {
            panel.removeAll();
            panel.add(panelBend.createAndGet(partNew));
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
        panel.setLayout(new MigLayout("", "[grow,left]", "[fill,top][fill,top][fill,top]"));
        panel.setBackground(Format.BGCOLOR);
        JPanel formPanel = new JPanel();
        formPanel
                .setLayout(new FormLayout(
                        new ColumnSpec[] { FormSpecs.MIN_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
                                FormSpecs.DEFAULT_COLSPEC, },
                        new RowSpec[] { RowSpec.decode("12px"), RowSpec.decode("20px"), FormSpecs.RELATED_GAP_ROWSPEC,
                                RowSpec.decode("20px"), }));
        formPanel.setBackground(Format.BGCOLOR);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(Format.eingabeFeldLabel());

        inputName = new JTextField();
        inputName.setText("");
        inputName.setColumns(30);
        inputName.setFont(Format.eingabeFeldLabel());

        JLabel lblProjektNummer = new JLabel("Projekt Nummer");
        lblProjektNummer.setFont(Format.eingabeFeldLabel());

        inputProjektNr = new JTextField();
        inputProjektNr.setColumns(30);
        inputProjektNr.setFont(Format.eingabeFeldLabel());

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
        scrollPane.getViewport().setBackground(Format.BGCOLOR);

        panel.add(formPanel, "cell 0 0,alignx left,aligny top");
        panel.add(btnWeiter, "cell 0 2,alignx left,aligny top");
        panel.add(scrollPane, "cell 0 1,alignx left,aligny top");
    }

    public void addActionWeiter() {
        btnWeiter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                step = 1;
                try {

                    sqLite.dbConnect();
                    partNew = new Part();
                    java.util.Date date = new java.util.Date();
                    partNew.setData(inputName.getText(), table.getSelectedMaterialId(),
                            Integer.parseInt(inputProjektNr.getText()), date.getTime());

                    create();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben.");

                } catch (PartException e) {
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());

                } catch (SQLiteException e) {
                    systemExceptionHandling(e.getLocalizedMessage());
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
