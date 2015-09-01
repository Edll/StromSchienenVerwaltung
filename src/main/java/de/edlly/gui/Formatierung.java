package de.edlly.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

public class Formatierung {
	
	
	
	// Position eines headers
	public static final int HEADER_POSITION_X = 5;
	public static final int HEADER_POSITION_Y = 5;
	
	
	// Header Font Formatierung
	public static Font headerFont(){
		Font headerFont = new Font("Tahoma", Font.BOLD, 16);
		return headerFont;
	}
	
	// Eingabefelder Beschriftung Font Formatierung
	public static Font eingabeFeldLabel(){
		Font headerFont = new Font("Tahoma", Font.BOLD, 11);
		return headerFont;		
	}
	
	// Rahmen um Eingabebereiche
	public static LineBorder rahmenUmEingabebereiche(){
		LineBorder rahmen = new LineBorder(Color.GRAY);
		return rahmen;
	}
	
	// Button Font Formatierung
	public static Font buttonFont(){
		Font headerFont = new Font("Tahoma", Font.BOLD, 11);
		return headerFont;		
	}
	
	

}
