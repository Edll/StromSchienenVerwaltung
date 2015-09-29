package de.edlly.gui;

import javax.swing.JOptionPane;

public abstract class Element implements IElement{


    public void systemExceptionHandling(String message) {
	JOptionPane.showMessageDialog(null, message, "Fehler im System", JOptionPane.ERROR_MESSAGE);
    }
    
    
    public void userExceptionHandling(String message){
	JOptionPane.showMessageDialog(null, message, "Fehler bei der Eingabe", JOptionPane.ERROR_MESSAGE);
    }

    public void userSuccessHandling(String message){
	JOptionPane.showMessageDialog(null, message, "Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
    }

}
