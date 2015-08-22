package de.edlly.versuchsklassen;

import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Canvas;
import javax.swing.UIManager;





public class GuiTest {
	private JFrame frmKupfermanager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest window = new GuiTest();
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

	
	public GuiTest() {
		initialize();
	}
	

	//private String Werkstueckname;

	private JTextField txtLochx;
	private JTextField txtLochy;
	private JTextField txtBiegungy;
	private JTextField txtBiegungwinkel;
	private JTable JTableWerkstueckWerte;
	private void initialize() {
	
		frmKupfermanager = new JFrame();
		frmKupfermanager.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmKupfermanager.setBounds(0, 0, 800, 600);
		//frmKupfermanager.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmKupfermanager.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane TabMenu = new JTabbedPane(JTabbedPane.TOP);
		TabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		TabMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmKupfermanager.getContentPane().add(TabMenu);
		
		JPanel Manager = new JPanel();
		Manager.setBorder(UIManager.getBorder("OptionPane.border"));
		
	    
		
		TabMenu.addTab("Manager", null, Manager, null);
		Manager.setLayout(null);
		
		JLabel lblWerkstueckManagerTitel = new JLabel("Werkst\u00FCck Manager");
		lblWerkstueckManagerTitel.setBounds(10, 8, 162, 20);
		lblWerkstueckManagerTitel.setFont(new Font("Tahoma", Font.BOLD, 16));
		Manager.add(lblWerkstueckManagerTitel);
		
		/*
		 * TODO: 
		 * Werkstück DB Erstellen
		 */
		JTable tableWerkstueckDB;
	    String[][] WerkstueckDBData = {
	    	    { "Winkel", "1304" }, { "Winkel1", "240" }, { "Etage", "220" },
	    	    { "Bla", "217" }, {"Vamocon", "215"} 
	    	    };

	    	    String[] WerkstueckDBColNames =  {
	    	      "Werkstück", "Länge"
	    	    };
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 747, 424);
		Manager.add(scrollPane);
		
		tableWerkstueckDB = new JTable (WerkstueckDBData, WerkstueckDBColNames);
		tableWerkstueckDB.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(tableWerkstueckDB);

		
		/*
		 * Werkstück Erstellen
		 */
		JPanel Erstellen = new JPanel();
		Erstellen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TabMenu.addTab("Erstellen", null, Erstellen, null);
		Erstellen.setLayout(null);
		
		
		
		JLabel lblErstellenTitle = new JLabel("Werkst\u00FCck Erstellen");
		lblErstellenTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErstellenTitle.setBounds(10, 8, 163, 20);
		Erstellen.add(lblErstellenTitle);
		
		//String[] MaterialArtList = {"Kupfer", "Alu"};
		
		//String[] MaterialGroesseList = {"20x10", "30X10", "40X10", "50X10", "60X10", "80X10", "100X10"};
		
		
		JButton btnWeiter = new JButton("Weiter");
		btnWeiter.setBounds(678, 498, 89, 23);
		Erstellen.add(btnWeiter);
		
		
		/* TODO:
		 * - Material Art und Größe aus Einstellbarer Liste */
		JLabel lblLoecherHinzu = new JLabel("Loecher Hinzufügen:");
		lblLoecherHinzu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoecherHinzu.setBounds(10, 39, 133, 16);
		
		String[] LoecherDurchmesserList = {"9", "11", "14", "18", "21x14"};
		JComboBox<Object> LoecherSelect = new JComboBox<Object>(LoecherDurchmesserList);
		LoecherSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LoecherSelect.setBounds(183, 89, 55, 20);
		
		JButton btnVerwerfen = new JButton("Verwerfen");
		btnVerwerfen.setBounds(25, 498, 89, 23);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(678, 498, 89, 23);
		
		JLabel lblWerkstueckname = new JLabel("Bla123");
		lblWerkstueckname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWerkstueckname.setBounds(201, 12, 189, 14);
		
		JLabel lblMaterialart = new JLabel("MaterialArt");
		lblMaterialart.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaterialart.setBounds(413, 12, 89, 14);
		
		JLabel lblMaterialmasse = new JLabel("MaterialMasse");
		lblMaterialmasse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaterialmasse.setBounds(501, 12, 104, 14);
		
		txtLochx = new JTextField();
		txtLochx.setText("LochX");
		txtLochx.setBounds(10, 89, 75, 20);
		txtLochx.setColumns(15);
		
		txtLochy = new JTextField();
		txtLochy.setText("LochY");
		txtLochy.setBounds(98, 89, 75, 20);
		txtLochy.setColumns(15);
		
		JButton btnLochHinzu = new JButton("+");

		btnLochHinzu.setBounds(248, 88, 41, 23);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX.setBounds(38, 66, 21, 14);

		JLabel lblY = new JLabel("Y");
		lblY.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblY.setBounds(125, 66, 46, 14);
		
		JLabel lblNewLabel = new JLabel("\u00D8");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(192, 66, 46, 14);

		JLabel lblBiegungHinzufgen = new JLabel("Biegung hinzuf\u00FCgen:");
		lblBiegungHinzufgen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBiegungHinzufgen.setBounds(339, 39, 131, 16);
		
		txtBiegungy = new JTextField();
		txtBiegungy.setText("BiegungY");
		txtBiegungy.setBounds(339, 89, 75, 20);
		txtBiegungy.setColumns(10);
		
		JLabel lblX_1 = new JLabel("Y");
		lblX_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX_1.setBounds(367, 66, 21, 14);

		txtBiegungwinkel = new JTextField();
		txtBiegungwinkel.setText("BiegungWinkel");
		txtBiegungwinkel.setBounds(424, 89, 75, 20);		
		txtBiegungwinkel.setColumns(10);
		
		JLabel lblWinkel = new JLabel("Winkel");
		lblWinkel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWinkel.setBounds(435, 66, 46, 14);

		String[] VorneHinten = {"Vorne", "Hinten"};
		JComboBox<Object> JCoBiegeRichtung = new JComboBox<Object>(VorneHinten);
		JCoBiegeRichtung.setFont(new Font("Tahoma", Font.PLAIN, 11));
		JCoBiegeRichtung.setBounds(511, 89, 55, 20);		
		
		JButton btnBiegehinzu = new JButton("+");
		btnBiegehinzu.setBounds(576, 88, 41, 23);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 120, 355, 367);

		
		/*
		 * TODO: löschen der Werte Aus der Tabelle
		 */
		
	    String[][] WerkstueckWerteData = {{ "L", "1304", "876876", "9", " ", " ", "X"}};
	    	    

	    	    String[] WerkstueckWerteColNames =  {
	    	      "Art", "X", "Y", "\u00D8", "°", "Richtung", "Löschen"
	    	    };
		
		JTableWerkstueckWerte = new JTable(WerkstueckWerteData, WerkstueckWerteColNames);

		scrollPane_1.setViewportView(JTableWerkstueckWerte);
		
		Erstellen.add(scrollPane_1);
		Erstellen.add(btnBiegehinzu);
		Erstellen.add(JCoBiegeRichtung);
		Erstellen.add(lblWinkel);
		Erstellen.add(txtBiegungwinkel);
		Erstellen.add(lblX_1);
		Erstellen.add(txtBiegungy);
		Erstellen.add(lblBiegungHinzufgen);
		Erstellen.add(lblNewLabel);
		Erstellen.add(lblLoecherHinzu);
		Erstellen.add(LoecherSelect);	
		Erstellen.add(btnVerwerfen);
		Erstellen.add(btnSpeichern);
		Erstellen.add(lblWerkstueckname);
		Erstellen.add(lblMaterialart);
		Erstellen.add(lblMaterialmasse);
		Erstellen.add(txtLochx);
		Erstellen.add(txtLochy);
		Erstellen.add(btnLochHinzu);
		Erstellen.add(lblX);
		Erstellen.add(lblY);
		
		JButton btnDeleteRow = new JButton("Makierte Zeilen L\u00F6schen");
		btnDeleteRow.setBounds(308, 498, 147, 23);
		Erstellen.add(btnDeleteRow);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(447, 120, 320, 360);
		Erstellen.add(canvas);
		
		JPanel panel = new JPanel();
		TabMenu.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		panel_1.setBounds(276, 100, 162, 172);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		btnBiegehinzu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnLochHinzu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

			
	}
}
