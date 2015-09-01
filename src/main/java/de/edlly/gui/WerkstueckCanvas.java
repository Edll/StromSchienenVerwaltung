package de.edlly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;

import javax.swing.JPanel;

class WerkstueckCavans extends JPanel {

    /**
     * VERSUCH KLASSE um eine Werkstueck das erstellt wird auch zu zeichnen Waerend der Nutzer Daten eingibt
     * 
     */
    private static final long serialVersionUID = 1L;

    int Werkx;
    int Werky;

    public WerkstueckCavans() {
	setLayout(new BorderLayout());
	setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public Dimension getPreferredSize() {

	Dimension layoutSize = super.getPreferredSize();
	int max = Math.max(layoutSize.width, layoutSize.height);
	return new Dimension(max + Werkx * 2, max + Werky * 2);
    }

    public void paintComponent(Graphics g) {

	super.paintComponent(g);
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, ((Werkx * 2) + 4), ((Werky * 2)) + 4);

	g.setColor(Color.WHITE);
	g.fillRect(2, 2, (Werkx * 2), (Werky * 2));

	g.setColor(Color.BLACK);
	g.fillOval((40 * 2), (600 * 2), 9, 9);
    }

    public void paintHole(Graphics g, int Holex, int Holey, int HoleDia) {

	g.setColor(Color.BLACK);
	g.fillOval((Holex * 2), (Holey * 2), HoleDia, HoleDia);

    }

}
