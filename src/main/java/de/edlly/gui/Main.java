package de.edlly.gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import de.edlly.gui.materialVerwaltung.TabMaterialVerwaltung;
import de.edlly.gui.werkstueckVerwaltung.WerkstueckVerwaltung;
import de.edlly.werkstueck.PartException;
import de.edlly.db.*;

/**
 * Programm Main Loader
 * 
 * @author Edlly java@edlly.de
 *
 */

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

	try {
	    SQLiteConnect sqlConnection = new SQLiteConnect();
	    sqlConnection.dbConnect();
	    SQLiteDatenbankStruktur datenbankCheck = new SQLiteDatenbankStruktur(sqlConnection);
	    datenbankCheck.datenbankCheckUndAnlegen();
	    sqlConnection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    JOptionPane.showMessageDialog(null, e.getMessage());
	}

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
	try {
	    WerkstueckVerwaltung werkstueckVerwaltung = new WerkstueckVerwaltung();
	    tabMenu.addTab("Werkst\u00FCck Verwaltung", null, werkstueckVerwaltung.werkstueckVerwaltungsPanel(), null);

	    TabMaterialVerwaltung materialVerwaltung = new TabMaterialVerwaltung();
	    tabMenu.addTab("Material Verwaltung", null, materialVerwaltung.materialVerwaltungsPanel(), null);
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (PartException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
