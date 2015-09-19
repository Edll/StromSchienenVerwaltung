package de.edlly.gui.materialVerwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.edlly.db.SQLiteConnect;
import de.edlly.gui.Formatierung;
import de.edlly.material.MaterialLoeschen;

public class AusgabeMaterialLoeschen {
    private JButton makiertesMaterialLoeschen;
    private AusgabeMaterialTabelle materialTabelle;
    private MaterialLoeschen materialLoeschen;
    private SQLiteConnect sqlConnection = new SQLiteConnect();

    public AusgabeMaterialLoeschen(AusgabeMaterialTabelle materialTabelle) {
	this.materialTabelle = materialTabelle;

    }

    public JPanel auswahlLoeschenPanel(int PositionX, int PositionY) {

	JPanel materialLoeschenAnzeigeBereich = new JPanel();
	materialLoeschenAnzeigeBereich.setBorder(Formatierung.rahmenUmEingabebereiche());
	materialLoeschenAnzeigeBereich.setLayout(null);
	materialLoeschenAnzeigeBereich.setBounds(550, 420, 170, 100);

	makiertesMaterialLoeschen = new JButton("Makierte Löschen");
	makiertesMaterialLoeschen.setFont(Formatierung.buttonFont());
	makiertesMaterialLoeschen.setBounds(10, 10, 150, 23);
	materialLoeschenAnzeigeBereich.add(makiertesMaterialLoeschen);

	makiertesMaterialLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		int selectedId = materialTabelle.getSelectedMaterialId();
		try {

		    sqlConnection.dbConnect();
		    materialLoeschen = new MaterialLoeschen(sqlConnection);
		    if (materialLoeschen.loschen(selectedId)) {
			JOptionPane.showMessageDialog(null, "Das ausgewählte Material ist gelöscht worden.");
			materialTabelle.refreshMaterialTabelle();
		    }
		    sqlConnection.close();
		} catch (SQLException e) {
		    e.printStackTrace();

		} finally {
		    try {
			sqlConnection.close();
		    } catch (SQLException e) {

			e.printStackTrace();
		    }
		}

	    }
	});

	return materialLoeschenAnzeigeBereich;
    }
}
