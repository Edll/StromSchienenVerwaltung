package de.edlly.gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.*;
import de.edlly.gui.werkstueckVerwaltung.PartVerwaltung;
import de.edlly.werkstueck.PartException;

/**
 * Programm Main Loader
 * 
 * @author Edlly java@edlly.de
 *
 */

public class Main {
    private JFrame frame;
    PartVerwaltung werkstueckVerwaltung;
    JPanel testpanel;

    public Main() {
	initialize();
    }

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {

		Main window = new Main();
		window.frame.setVisible(true);

	    }
	});
    }

    private void initialize() {
	frame = new JFrame();
	frame.setBounds(0, 0, 800, 600);
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

	Menu menu = new Menu(frame);
	menu.getMenu();

	PartVerwaltung werkstueckVerwaltung = new PartVerwaltung();
	try {
	    frame.getContentPane().add(werkstueckVerwaltung.addPartListPanel());
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (PartException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	/*
	 * TODO: Dies ist ein Test Code er ist ausgeklammert da das Modul nicht fertig ist. try { SQLiteConnect
	 * sqlConnection = new SQLiteConnect(); sqlConnection.dbConnect(); SQLiteDatenbankStruktur datenbankCheck = new
	 * SQLiteDatenbankStruktur(sqlConnection); datenbankCheck.datenbankCheckUndAnlegen(); sqlConnection.close(); }
	 * catch (SQLException e) { e.printStackTrace(); } catch (IllegalArgumentException e) {
	 * JOptionPane.showMessageDialog(null, e.getMessage()); }
	 */
    }
}
