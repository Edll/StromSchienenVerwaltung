package de.edlly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

class WerkstueckCavans extends  JPanel {

    /** VERSUCH KLASSE um eine Werkstück das erstellt wird auch zu zeichnen Wärend der Nutzer Daten eingibt
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int Werkx;
	int Werky;
	

	public WerkstueckCavans () {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
     //  setBackground (Color.LIGHT_GRAY);
      // setSize(Werkx*2, 4000);
       // ausgeklammert weil nur für test.
       /*if(Werkx == 0){
    	   Werkx = 100;
       }      
       if(Werky == 0){
    	   Werky = 100;
       }*/
    }
	
	  @Override
      public Dimension getPreferredSize() {

          Dimension layoutSize = super.getPreferredSize();
          int max = Math.max(layoutSize.width, layoutSize.height);
          return new Dimension(max + Werkx*2, max + Werky*2);
      }

    public void paintComponent (Graphics g) {
    	 super.paintComponent(g);
    	g.setColor(Color.BLACK);
       g.fillRect(0, 0, ((Werkx*2)+4), ((Werky*2))+4);
     
       g.setColor(Color.WHITE);
       g.fillRect(2, 2, (Werkx*2), (Werky*2));
       
       
       g.setColor(Color.BLACK);
       g.fillOval( (40*2), (600*2), 9, 9);
    }    
    
    
    public void paintHole (Graphics g , int Holex , int Holey, int HoleDia)  {
    //    Graphics2D g2;
   //     g2 = (Graphics2D) g;
      // rausgenommen um nicht immer über zu malen
      //  g2.setColor(Color.WHITE);
     //   g2.fillRect(20, 50, Werkx, Werky);
        g.setColor(Color.BLACK);
        g.fillOval( (Holex*2), (Holey*2), HoleDia, HoleDia);
        
     }
    
 }
