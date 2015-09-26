package de.edlly.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.edlly.gui.werkstueckVerwaltung.WerkstueckVerwaltung;
import de.edlly.werkstueck.PartException;

public class Menu {
    private JFrame frame;
    
    public Menu(JFrame frame){
	this.frame = frame;
    }
    
    public void getMenu(){
	JMenuBar mainMenuBar = new JMenuBar();
	frame.setJMenuBar(mainMenuBar);

	JMenu mPart = new JMenu("Werkstücke");
	mainMenuBar.add(mPart);

	JMenuItem itemListPart = new JMenuItem("Liste");
	itemListPart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    WerkstueckVerwaltung werkstueckVerwaltung = new WerkstueckVerwaltung();
		  try {
		      System.out.println("works");
		    frame.getContentPane().add( werkstueckVerwaltung.werkstueckVerwaltungsPanel());
		    frame.repaint();
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		} catch (PartException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		}
		
	});
	mPart.add(itemListPart);

	JMenuItem itemNeuPart = new JMenuItem("Neu");
	itemNeuPart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mPart.add(itemNeuPart);

	JMenu mMaterial = new JMenu("Material");
	mainMenuBar.add(mMaterial);

	JMenuItem itemListMaterial = new JMenuItem("Liste");
	itemListMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mMaterial.add(itemListMaterial);

	JMenuItem itemNeuMaterial = new JMenuItem("Neu");
	itemNeuMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mMaterial.add(itemNeuMaterial);

	JMenu mSettings = new JMenu("Einstellungen");
	mainMenuBar.add(mSettings);

	JMenuItem itemMaterial = new JMenuItem("Material");
	itemMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mSettings.add(itemMaterial);
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

    }
}
