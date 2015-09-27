package de.edlly.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

/**
 * Formatierungsoption für die Oberfläche
 * 
 * @author Edlly java@edlly.de
 *
 */

public  abstract class Formatierung {

    // Formatierung Standard ElementPanel
    
    public static final String MIG_ELEMENT_PANEL_LEFT = "[grow,left]";
    public static final String MIG_ELEMENT_PANEL_TOP = "[grow,top]";
    
    // Position eines headers
    public static final int HEADER_POSITION_X = 5;
    public static final int HEADER_POSITION_Y = 5;

    // Header Font Formatierung
    public static Font headerFont() {
	Font headerFont = new Font("Tahoma", Font.BOLD, 16);
	return headerFont;
    }

    // Eingabefelder Beschriftung Font Formatierung
    public static Font eingabeFeldLabel() {
	Font headerFont = new Font("Tahoma", Font.BOLD, 11);
	return headerFont;
    }

    // Button Font Formatierung
    public static Font buttonFont() {
	Font buttonFont = new Font("Tahoma", Font.BOLD, 11);
	return buttonFont;
    }

    // Tabellen Schrift Formatierung
    public static Font tableFont() {
	Font tableFont = new Font("Tahoma", Font.PLAIN, 11);
	return tableFont;
    }

    // Rahmen um Eingabebereiche
    public static LineBorder rahmenUmEingabebereiche() {
	LineBorder rahmen = new LineBorder(Color.GRAY);
	return rahmen;
    }

}
