package de.edlly.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.SQLException;






public class Main {
	private JFrame frmKupfermanager;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmKupfermanager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Connection SqlConn=null;
	 

	public Main() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */


	private void initialize() {
		SqlConn = de.edlly.db.SQLiteConnect.dbConnection();
		frmKupfermanager = new JFrame();
		frmKupfermanager.setBounds(0, 0, 800, 600);
		//frmKupfermanager.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmKupfermanager.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane TabMenu = new JTabbedPane(JTabbedPane.TOP);
		TabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		TabMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmKupfermanager.getContentPane().add(TabMenu);
		
		de.edlly.gui.WerkstueckManager TabWerkstueckManager = new de.edlly.gui.WerkstueckManager();
	  
		
		TabMenu.addTab("Werkst\u00FCck Manager", null, TabWerkstueckManager.ui(), null);

		
		/*
		 * Werkst�ck Erstellen
		 */
		de.edlly.gui.WerkstueckErstellen TabWerkstueckErstellen = new de.edlly.gui.WerkstueckErstellen();
		TabMenu.addTab("Werkst\u00FCckErstellen", null, TabWerkstueckErstellen.ui(), null);
		
		
		
		// Material Manager
		de.edlly.gui.materialVerwaltung.Tab TabMaterialManager = new de.edlly.gui.materialVerwaltung.Tab();
		TabMenu.addTab("Material Manager", null, TabMaterialManager.ui(), null);
		
		

	

			
		try {
			SqlConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}	
	
}
