package de.edlly.gui;

import java.awt.EventQueue;
import javax.swing.*;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteDatenbankStruktur;
import de.edlly.db.SQLiteException;
import de.edlly.gui.elements.ElementPartListe;
import net.miginfocom.swing.MigLayout;

/**
 * Programm Main Loader
 * 
 * @author Edlly java@edlly.de
 *
 */

public class Main {
    private JFrame frame;
    ElementPartListe werkstueckVerwaltung;
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
	testDatabankStruktur();
	
	frame = new JFrame();

	frame.getContentPane().setLayout(new MigLayout("fill", "[grow,left]", "[grow,top]"));
	frame.getContentPane().setBackground(Format.BGCOLOR);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

	Menu menu = new Menu(frame);
	menu.getMenu();

	ElementPartListe werkstueckVerwaltung = new ElementPartListe();
	frame.setTitle("Werkstück Verwaltung");

	frame.getContentPane().add(werkstueckVerwaltung.createAndGet());
    }

    private void testDatabankStruktur() {
	try {
	    SQLiteConnect sqlConnection = new SQLiteConnect();
	    sqlConnection.dbConnect();
	    SQLiteDatenbankStruktur datenbankCheck = new SQLiteDatenbankStruktur(sqlConnection);
	    datenbankCheck.datenbankCheckUndAnlegen();
	    sqlConnection.close();
	} catch (SQLiteException e) {
	    JOptionPane.showMessageDialog(null, e.getMessage());
	} 
    }
}
