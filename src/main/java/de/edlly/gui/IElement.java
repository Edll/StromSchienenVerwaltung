package de.edlly.gui;

import javax.swing.JPanel;

public interface IElement {

    /**
     * Für jedes Element des Programms gibt es eine eigene Klasse. In dieser wird die Oberfläche zusammengestellt und
     * dann in ein Panel verpackt. Mit dieser Funktion wird das Panel erzeugt und Abgefragt.
     * 
     * @return Das JPanel mit dem Inhalt des Element der Klasse.
     */
    public JPanel createAndGet();

    /**
     * Erzeugt das Element des Programms und fügt die Inhalte zusammen.
     *
     */
    public void create();

    /**
     * Beinhaltetet die Behandlung Von System Exceptions die auftreten können innerhalb des Elements.
     */
    public void systemExceptionHandling(String message);

    /**
     * Beinhaltetet die Behandlung Von User Exceptions die durch Falsche eingaben auftretten können.
     */

    public void userExceptionHandling(String message);
}
