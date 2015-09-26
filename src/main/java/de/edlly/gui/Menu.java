package de.edlly.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import de.edlly.gui.materialVerwaltung.AusgabeMaterialAnlegen;
import de.edlly.gui.materialVerwaltung.AusgabeMaterialTabelle;
import de.edlly.gui.werkstueckVerwaltung.PartVerwaltung;
import de.edlly.werkstueck.PartException;

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

		try {
		    PartVerwaltung werkstueckVerwaltung = new PartVerwaltung();
		    frame.getContentPane().add(werkstueckVerwaltung.addPartListPanel());
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		} catch (PartException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

		frame.repaint();
		frame.validate();
	    }
	});

	itemNeuPart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    }
	});

	itemMaterial.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

	    }
	});

	itemNeuMaterial.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

		frame.getContentPane().removeAll();

		AusgabeMaterialAnlegen neuesMaterialAnlegen = new AusgabeMaterialAnlegen();
		try {

		    frame.getContentPane().add(neuesMaterialAnlegen.materialEingabeBereich(10, 420));
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

		frame.repaint();
		frame.validate();

	    }
	});

	itemListMaterial.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.getContentPane().removeAll();

		try {
		    AusgabeMaterialTabelle materialVerwaltung = new AusgabeMaterialTabelle();
		    frame.getContentPane().add(materialVerwaltung.materialTabellePanel(10, 10));
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

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
