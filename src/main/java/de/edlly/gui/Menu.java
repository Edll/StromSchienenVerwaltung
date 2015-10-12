package de.edlly.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import de.edlly.gui.elements.ElementMaterialListe;
import de.edlly.gui.elements.ElementMaterialNeu;
import de.edlly.gui.elements.ElementPartListe;
import de.edlly.gui.elements.ElementPartNeu;

public class Menu {
    private JFrame frame;
    private JMenuBar mainMenuBar;
    private JMenu mPart;
    private JMenuItem itemListPart;
    private JMenuItem itemNeuPart;
    private JMenu mMaterial;
    private JMenuItem itemListMaterial;
    private JMenuItem itemNeuMaterial;
    private JMenu mSettings;
    private JMenuItem itemMaterial;

    public Menu(JFrame frame) {
        this.frame = frame;
    }

    public void getMenu() {
        addMenuItem();
        addMenuAction();
    }

    public void addMenuAction() {
        itemListPart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.setTitle("Werkstück Liste");

                ElementPartListe werkstueckVerwaltung = new ElementPartListe();
                frame.getContentPane().add(werkstueckVerwaltung.createAndGet());

                frame.repaint();
                frame.validate();
            }
        });

        itemNeuPart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ElementPartNeu werkstueckVerwaltung = new ElementPartNeu();
                frame.getContentPane().removeAll();
                frame.setTitle("Werkstück Anlegen");

                frame.getContentPane().add(werkstueckVerwaltung.createAndGet());

                frame.repaint();
                frame.validate();
            }
        });

        itemNeuMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();
                frame.setTitle("Material Liste");

                ElementMaterialNeu neuesMaterialAnlegen = new ElementMaterialNeu();
                frame.getContentPane().add(neuesMaterialAnlegen.createAndGet());

                frame.repaint();
                frame.validate();

            }
        });

        itemListMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setTitle("Material Anlegen");

                ElementMaterialListe materialVerwaltung = new ElementMaterialListe();
                frame.getContentPane().add(materialVerwaltung.createAndGet());

                frame.repaint();
                frame.validate();
            }
        });

    }

    public void addMenuItem() {
        mainMenuBar = new JMenuBar();
        frame.setJMenuBar(mainMenuBar);

        mPart = new JMenu("Werkstücke");
        mainMenuBar.add(mPart);

        itemListPart = new JMenuItem("Liste");
        mPart.add(itemListPart);

        itemNeuPart = new JMenuItem("Neu");
        mPart.add(itemNeuPart);

        mMaterial = new JMenu("Material");
        mainMenuBar.add(mMaterial);

        itemListMaterial = new JMenuItem("Liste");
        mMaterial.add(itemListMaterial);

        itemNeuMaterial = new JMenuItem("Neu");
        mMaterial.add(itemNeuMaterial);

        mSettings = new JMenu("Einstellungen");
        mainMenuBar.add(mSettings);

        itemMaterial = new JMenuItem("Material");
        mSettings.add(itemMaterial);
    }
}
