package de.edlly.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import de.edlly.gui.materialVerwaltung.TabMaterialVerwaltung;

import java.awt.Color;
import java.awt.BorderLayout;

public class Main {
    private JFrame mainFrame;

    public Main() {
	initialize();
    }

    public static void main(String[] args) {

	EventQueue.invokeLater(new Runnable() {
	    public void run() {


		    Main window = new Main();
		    window.mainFrame.setVisible(true);

	    }
	});
    }

    private void initialize() {
	mainFrame = new JFrame();
	mainFrame.setBounds(0, 0, 800, 600);
	mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));

	JTabbedPane tabMenu = tabMenuErstellen();

	mainFrame.getContentPane().add(tabMenu);
    }

    private JTabbedPane tabMenuErstellen() {
	JTabbedPane tabMenu = new JTabbedPane(JTabbedPane.TOP);
	tabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	tabMenu.setBorder(new LineBorder(new Color(0, 0, 0)));

	tabsHinzufuegen(tabMenu);

	return tabMenu;
    }

    private void tabsHinzufuegen(JTabbedPane tabMenu) {
	WerkstueckManager werkstueckVerwaltung = new WerkstueckManager();
	tabMenu.addTab("Werkst\u00FCck Verwaltung", null, werkstueckVerwaltung.werkstueckVerwaltungsPanel(), null);

	TabMaterialVerwaltung materialVerwaltung = new TabMaterialVerwaltung();
	tabMenu.addTab("Material Verwaltung", null, materialVerwaltung.materialVerwaltungsPanel(), null);
    }
}
