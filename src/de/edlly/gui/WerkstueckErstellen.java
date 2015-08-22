package de.edlly.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import de.edlly.material.DbAbfrage;
import de.edlly.werkstueck.KoordinatenBiegung;
import de.edlly.werkstueck.KoordinatenLoch;

public class WerkstueckErstellen {
	
	
	public Connection SqlConn=null;
	private JTextField txtWerkstueckname;
	private String Werkstueckname;
	private JTextField txtLochx;
	private JTextField txtLochy;
	private JTextField txtBiegungy;
	private JTextField txtBiegungwinkel;
	int counter=0;
	private int xMax;
	private int yMax;
	// Konstruktor
	WerkstueckErstellen(){
		// SQL Connection herstellen
		if(this.SqlConn == null){
			SqlConn = de.edlly.db.SQLiteConnect.dbConnection();
		}
	}	



	
	public JPanel ui(){
		JPanel WerkstueckErstellen = new JPanel();
		

	      
		WerkstueckErstellen.setLayout(null);
		JLabel lblErstellenTitle = new JLabel("Werkst\u00FCck Erstellen");
		lblErstellenTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErstellenTitle.setBounds(10, 8, 163, 20);
		WerkstueckErstellen.add(lblErstellenTitle);
		

		
		/* 
		 * Erstellen des Werkstück Schritt 1
		 * Name, Materialauswahl aus Table
		 * */
		
		JLabel lblWerkstueckName = new JLabel("Werkst\u00FCck Name:");
		lblWerkstueckName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWerkstueckName.setBounds(10, 43, 116, 14);
		WerkstueckErstellen.add(lblWerkstueckName);
		
		txtWerkstueckname = new JTextField();
		txtWerkstueckname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtWerkstueckname.setBounds(134, 39, 274, 20);
		WerkstueckErstellen.add(txtWerkstueckname);
		txtWerkstueckname.setColumns(50);
			
		JScrollPane MaterialTableScroll = new JScrollPane();
		MaterialTableScroll.setBounds(10, 100, 738, 340);
		MaterialTableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		MaterialTableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		WerkstueckErstellen.add(MaterialTableScroll);
		


		 // Erstellen des Table Models    
		 ManagerModel  MaterialAuswahlModel = new ManagerModel();
		 MaterialAuswahlModel.addColumn("Id");
		 MaterialAuswahlModel.addColumn("Sorte");
		 MaterialAuswahlModel.addColumn("Größe");
		 MaterialAuswahlModel.addColumn("Maximale Länge");
		 
		 // Daten der Table aus DB Abfragen
		 DbAbfrage MaterialSql = new DbAbfrage();
		 MaterialSql.SqlConn = this.SqlConn;
		 String[][] MaterialDBData = MaterialSql.SelectTableFormat();
		 
		 
		 // NullPointerException nicht auslösen
		 if(MaterialDBData != null) {	
			 
			 for(int i=0; i!=MaterialDBData.length; i++){	 
				 MaterialAuswahlModel.addRow(new Object[]{MaterialDBData[i][0],MaterialDBData[i][1],MaterialDBData[i][2], MaterialDBData[i][3]}); 
			}
		 }

		 // Table erzeugen
		 JTable MaterialAuswahlTable = new JTable(MaterialAuswahlModel);
		 
		 // nur ein Element auswählbar
		 MaterialAuswahlTable.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION); 		
		 
		 // Table zum ScrollPane Hinzufügen
		 MaterialTableScroll.getViewport().add(MaterialAuswahlTable, null);
		
		 // Knopf zum weiter gehen
		 JButton btnWeiter = new JButton("Weiter");
		 btnWeiter.setBounds(678, 498, 89, 23);
		 WerkstueckErstellen.add(btnWeiter);
		
		
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
		
		JLabel lblWerkstueckname = new JLabel("WerkstueckName");
		lblWerkstueckname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWerkstueckname.setBounds(201, 12, 189, 15);
		
		JLabel lblMaterialSorte = new JLabel("MaterialArt");
		lblMaterialSorte.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaterialSorte.setBounds(413, 12, 89, 15);
		
		JLabel lblMaterialGroesse = new JLabel("MaterialMasse");
		lblMaterialGroesse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaterialGroesse.setBounds(501, 12, 104, 15);
		
		txtLochx = new JTextField();
		txtLochx.setText("");
		txtLochx.setBounds(10, 89, 75, 20);
		txtLochx.setColumns(15);
		
		txtLochy = new JTextField();
		txtLochy.setText("");
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
		
		JLabel lblDurchmesser = new JLabel("\u00D8");
		lblDurchmesser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDurchmesser.setBounds(192, 66, 46, 14);

		JLabel lblBiegungHinzufgen = new JLabel("Biegung hinzuf\u00FCgen:");
		lblBiegungHinzufgen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBiegungHinzufgen.setBounds(339, 39, 131, 16);
		
		txtBiegungy = new JTextField();
		txtBiegungy.setText("");
		txtBiegungy.setBounds(339, 89, 75, 20);
		txtBiegungy.setColumns(10);
		
		JLabel lblX_1 = new JLabel("Y");
		lblX_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblX_1.setBounds(367, 66, 21, 14);

		txtBiegungwinkel = new JTextField();
		txtBiegungwinkel.setText("");
		txtBiegungwinkel.setBounds(424, 89, 75, 20);		
		txtBiegungwinkel.setColumns(10);
		
		JLabel lblWinkel = new JLabel("Winkel");
		lblWinkel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWinkel.setBounds(435, 66, 46, 14);

		String[] VorneHinten = {"Vorne", "Hinten"};
		JComboBox<Object> JCoBiegeRichtung = new JComboBox<Object>(VorneHinten);
		JCoBiegeRichtung.setFont(new Font("Tahoma", Font.PLAIN, 11));
		JCoBiegeRichtung.setBounds(511, 89, 60, 20);		
		
		JButton btnBiegehinzu = new JButton("+");
		btnBiegehinzu.setBounds(581, 88, 41, 23);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 120, 355, 367);

		
		/*
		 * TODO: löschen der Werte Aus der Tabelle
		 * TODO: Daten aus Datenbank ziehen
		 */
		   	    

	    	
		WerkstueckModel WerkstueckTableModel = new WerkstueckModel();
		WerkstueckTableModel.addColumn("Art");
		WerkstueckTableModel.addColumn("X");
		WerkstueckTableModel.addColumn("Y");
	    WerkstueckTableModel.addColumn("\u00D8");
	    WerkstueckTableModel.addColumn("°");
	    WerkstueckTableModel.addColumn("Richtung");
        JTable JTableWerkstueckWerte = new JTable(WerkstueckTableModel);
       
		JButton btnDeleteRow = new JButton("Makierte Zeilen L\u00F6schen");
		btnDeleteRow.setBounds(308, 498, 175, 23);


		
		// Versuchsbereich für das Canvas

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(447, 120, 320, 360);
		scrollPane_2.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		
		WerkstueckCavans Werkstcanvas = new WerkstueckCavans();
		scrollPane_2.setViewportView(Werkstcanvas );

		
		scrollPane_1.setViewportView(JTableWerkstueckWerte);
		
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg2) {
				try{
				// Textfeld zurück setzten
				txtWerkstueckname.setText("");
				
				// Elemente Hinzufügen und Entfernen
				WerkstueckErstellen.remove(scrollPane_1);
				WerkstueckErstellen.remove(btnBiegehinzu);
				WerkstueckErstellen.remove(JCoBiegeRichtung);
				WerkstueckErstellen.remove(lblWinkel);
				WerkstueckErstellen.remove(txtBiegungwinkel);
				WerkstueckErstellen.remove(lblX_1);
				WerkstueckErstellen.remove(txtBiegungy);
				WerkstueckErstellen.remove(lblBiegungHinzufgen);
				WerkstueckErstellen.remove(lblDurchmesser);
				WerkstueckErstellen.remove(lblLoecherHinzu);
				WerkstueckErstellen.remove(LoecherSelect);	
				WerkstueckErstellen.remove(btnVerwerfen);
				WerkstueckErstellen.remove(btnSpeichern);
				WerkstueckErstellen.remove(lblWerkstueckname);
				WerkstueckErstellen.remove(lblMaterialSorte);
				WerkstueckErstellen.remove(lblMaterialGroesse);
				WerkstueckErstellen.remove(txtLochx);
				WerkstueckErstellen.remove(txtLochy);
				WerkstueckErstellen.remove(btnLochHinzu);
				WerkstueckErstellen.remove(lblX);
				WerkstueckErstellen.remove(lblY);
				WerkstueckErstellen.remove(btnDeleteRow);	
				WerkstueckErstellen.remove(scrollPane_2);
				

				WerkstueckErstellen.add(txtWerkstueckname);
				WerkstueckErstellen.add(lblWerkstueckName);
				WerkstueckErstellen.add(MaterialTableScroll);
				WerkstueckErstellen.add(btnWeiter);
				
				WerkstueckErstellen.revalidate();
				WerkstueckErstellen.repaint();	
				}catch (IllegalArgumentException e ) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				} 
		   }
	   });
		
		
		
		btnVerwerfen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				// Textfeld zurück setzten
				txtWerkstueckname.setText("");
				
				// Elemente Hinzufügen und Entfernen
				WerkstueckErstellen.remove(scrollPane_1);
				WerkstueckErstellen.remove(btnBiegehinzu);
				WerkstueckErstellen.remove(JCoBiegeRichtung);
				WerkstueckErstellen.remove(lblWinkel);
				WerkstueckErstellen.remove(txtBiegungwinkel);
				WerkstueckErstellen.remove(lblX_1);
				WerkstueckErstellen.remove(txtBiegungy);
				WerkstueckErstellen.remove(lblBiegungHinzufgen);
				WerkstueckErstellen.remove(lblDurchmesser);
				WerkstueckErstellen.remove(lblLoecherHinzu);
				WerkstueckErstellen.remove(LoecherSelect);	
				WerkstueckErstellen.remove(btnVerwerfen);
				WerkstueckErstellen.remove(btnSpeichern);
				WerkstueckErstellen.remove(lblWerkstueckname);
				WerkstueckErstellen.remove(lblMaterialSorte);
				WerkstueckErstellen.remove(lblMaterialGroesse);
				WerkstueckErstellen.remove(txtLochx);
				WerkstueckErstellen.remove(txtLochy);
				WerkstueckErstellen.remove(btnLochHinzu);
				WerkstueckErstellen.remove(lblX);
				WerkstueckErstellen.remove(lblY);
				WerkstueckErstellen.remove(btnDeleteRow);	
				WerkstueckErstellen.remove(scrollPane_2);
				
				WerkstueckErstellen.add(txtWerkstueckname);
				WerkstueckErstellen.add(lblWerkstueckName);
				WerkstueckErstellen.add(btnWeiter);
				WerkstueckErstellen.add(MaterialTableScroll);
				
				WerkstueckErstellen.revalidate();
				WerkstueckErstellen.repaint();
			}
		});

		

		btnWeiter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					// Werkstück Name entspricht den Vorgabe gibt ein throw wie der String auszusehen hat
					Werkstueckname = WerkstueckName(txtWerkstueckname.getText());	

					// wurde ein Material in der Liste ausgewählt
					if(MaterialAuswahlTable.getSelectedRow() == -1){
						throw new IllegalArgumentException( "Bitte ein Material aus der Liste auswählen." );	
					}
					
					// Material aus der DB holen.
				    int id =Integer.parseInt((String)MaterialAuswahlTable.getModel().getValueAt(MaterialAuswahlTable.getSelectedRow(), 0));
				    int[] AusgewMaterial =  MaterialSql.SelectId(id);
				    
				    yMax = AusgewMaterial[3];
				    xMax = AusgewMaterial[1];
				    Werkstcanvas.Werkx = AusgewMaterial[1];
				    Werkstcanvas.Werky = AusgewMaterial[3];
				    
				    
					// Name, Art und Größe auf die Label Schreiben
					lblWerkstueckname.setText(Werkstueckname);
					lblMaterialSorte.setText(MaterialSql.SelectMaterialSorteId(AusgewMaterial[0]));
					lblMaterialGroesse.setText(AusgewMaterial[1] + "x" + AusgewMaterial[2]);
				
					// Element aus der UI entfernen und hinzufügen.
					WerkstueckErstellen.remove(txtWerkstueckname);
					WerkstueckErstellen.remove(lblWerkstueckName);
					WerkstueckErstellen.remove(btnWeiter);
					WerkstueckErstellen.remove(MaterialTableScroll);
					
					WerkstueckErstellen.add(scrollPane_1);
					WerkstueckErstellen.add(btnBiegehinzu);
					WerkstueckErstellen.add(JCoBiegeRichtung);
					WerkstueckErstellen.add(lblWinkel);
					WerkstueckErstellen.add(txtBiegungwinkel);
					WerkstueckErstellen.add(lblX_1);
					WerkstueckErstellen.add(txtBiegungy);
					WerkstueckErstellen.add(lblBiegungHinzufgen);
					WerkstueckErstellen.add(lblDurchmesser);
					WerkstueckErstellen.add(lblLoecherHinzu);
					WerkstueckErstellen.add(LoecherSelect);	
					WerkstueckErstellen.add(btnVerwerfen);
					WerkstueckErstellen.add(btnSpeichern);
					WerkstueckErstellen.add(lblWerkstueckname);
					WerkstueckErstellen.add(lblMaterialSorte);
					WerkstueckErstellen.add(lblMaterialGroesse);
					WerkstueckErstellen.add(txtLochx);
					WerkstueckErstellen.add(txtLochy);
					WerkstueckErstellen.add(btnLochHinzu);
					WerkstueckErstellen.add(lblX);
					WerkstueckErstellen.add(lblY);
					WerkstueckErstellen.add(btnDeleteRow);	
					WerkstueckErstellen.add(scrollPane_2);
					
					WerkstueckErstellen.revalidate();
					WerkstueckErstellen.repaint();
				
				} catch (IllegalArgumentException e ) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				
				
				}

			}
		});
		
		btnLochHinzu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg3) {
				
				try{
				/*	
				Konstanten WerkstueckKonstanten = new Konstanten();
				WerkstueckKonstanten.Material = lblMaterialGroesse.getText();
				int[] MaterialMax = WerkstueckKonstanten.MaterialGroese();
				*/
				
				KoordinatenLoch Koordinaten = new KoordinatenLoch();
				Koordinaten.yMax = yMax;
				Koordinaten.xMax = xMax;
				Koordinaten.x = Integer.parseInt(txtLochx.getText());
				Koordinaten.y = Integer.parseInt(txtLochy.getText()); 
				Koordinaten.Durchmesser = Integer.parseInt((String) LoecherSelect.getSelectedItem()); 
				Koordinaten.CheckXY();
				Koordinaten.CheckLoch();
				Werkstcanvas.paintHole(Werkstcanvas.getGraphics(), Koordinaten.x, Koordinaten.y, Koordinaten.Durchmesser);
				
			//	x =  DataCheck.WerkstueckKoordinate(Integer.parseInt(txtLochx.getText()), MaterialMax[0]);
			//	y = DataCheck.WerkstueckKoordinate(Integer.parseInt(txtLochy.getText()), MaterialMax[1]);
			
		
				 DefaultTableModel model = (DefaultTableModel) JTableWerkstueckWerte.getModel();
			        model.addRow(new Object[]{ "L", Koordinaten.x, Koordinaten.y, Koordinaten.Durchmesser, "", ""});
				
			 
				WerkstueckErstellen.revalidate();
				WerkstueckErstellen.repaint();
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Bitte eine Zahl gültige Zahl eingeben.");	
					
				} catch (IllegalArgumentException e ) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				
				}
				
			}
		});
		

		
		
		btnBiegehinzu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg4) {
				try{
					int winkel, y;
					String Richtung;
					
					/*Konstanten WerkstueckKonstanten = new Konstanten();
					//WerkstueckKonstanten.Material = (String) MaterialSelect.getSelectedItem();
				//	int[] MaterialMax = WerkstueckKonstanten.MaterialGroese();*/
					
					y =  Integer.parseInt(txtBiegungy.getText());
					winkel =  Integer.parseInt(txtBiegungwinkel.getText());
					
					
					KoordinatenBiegung Koordinaten = new KoordinatenBiegung();
					Koordinaten.Winkel = Integer.parseInt(txtBiegungwinkel.getText());
					Koordinaten.y = Integer.parseInt(txtBiegungy.getText()); 
					Koordinaten.Check();
					
					
					
					Richtung = ((String) JCoBiegeRichtung.getSelectedItem());
	
					 DefaultTableModel model = (DefaultTableModel) JTableWerkstueckWerte.getModel();
				        model.addRow(new Object[]{ "B", "", y, "", winkel, Richtung,});
					
					WerkstueckErstellen.revalidate();
					WerkstueckErstellen.repaint();
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Bitte eine Zahl gültige Zahl eingeben.");	
						
					} catch (IllegalArgumentException e ) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					
					}
			}
		});
		
		btnDeleteRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                
            	// Mehr als eine Zeile?
            	if(JTableWerkstueckWerte.getSelectedRows() != null){
            		int[] SelectedRows = JTableWerkstueckWerte.getSelectedRows();
            		if(SelectedRows.length > 1){
            			for(int i=0; SelectedRows.length!=i; i++  ){
            				// Alle ausgewählten Zeilen entfernen
                        	WerkstueckTableModel.removeRow(JTableWerkstueckWerte.getSelectedRow());
            			}
            			
            		}else{
                	// nur eine Zeile
                    if (JTableWerkstueckWerte.getSelectedRow() != -1) {
                    	WerkstueckTableModel.removeRow(JTableWerkstueckWerte.getSelectedRow());
                    }
            	}
            	}
            	

                
            }
        });
		
		
		return  WerkstueckErstellen;
		
	}

	
	/**
	 *  Eigenes TableModel um die Zellen so zu erzwingen wie es steht.
	 * @author Edlly
	 *
	 */
	public class ManagerModel extends DefaultTableModel
	{
		private static final long serialVersionUID = 1L;

		public int getColumnCount()
	     {
	         return 4;
	     }

	     public boolean isCellEditable(int r, int c)
	    {
	        return c == 3;
	    }
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class [] {
	    	       java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
	    	    };

				public Class<?> getColumnClass(int columnIndex) {
	    	        return types [columnIndex];
	    	    } 	
  }
	
	public class WerkstueckModel extends DefaultTableModel
	{
		private static final long serialVersionUID = 1L;

		public int getColumnCount()
	     {
	         return 6;
	     }

	     public boolean isCellEditable(int r, int c)
	    {
	        return c == 5;
	    }
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class [] {
	    	       java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
	    	    };

				public Class<?> getColumnClass(int columnIndex) {
	    	        return types [columnIndex];
	    	    } 	
  }
	
	/** Prüft den Namen eines Werkstück auf die enthaltenen Zeichen und die Länge
	 * 
	 * @param Name des Werkstück das zu Prüfen ist
	 * @return Name des Werkstück als String
	 */
	public static String WerkstueckName(String Name) {

		Pattern p = Pattern.compile( "\\w*" );
		Matcher m = p.matcher(Name);
		if(1 >  Name.length() | !m.matches() |  30 <  Name.length()){
			throw new IllegalArgumentException( "Der Name darf nur A-Z, a-z und 0-9 enthalten und Maximal 30 Zeichen lang sein." );	
			
		}

		return Name;


	}


	
}
