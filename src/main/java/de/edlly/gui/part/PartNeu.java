package de.edlly.gui.part;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import com.jgoodies.forms.layout.*;
import net.miginfocom.swing.MigLayout;
import de.edlly.db.SQLiteConnect;
import de.edlly.gui.material.MaterialListe;
import de.edlly.werkstueck.IPartData;
import de.edlly.werkstueck.PartDataAdd;
import de.edlly.werkstueck.PartException;

public class PartNeu {
    private JTextField inputName;
    private JTextField inputProjektNr;
    private JPanel panel = new JPanel();
    private int step;
    private MaterialListe materialListe;
    private SQLiteConnect sqlConnection = new SQLiteConnect();

    public JPanel loadPanel() throws SQLException {

	createSteps();
	return panel;
    }

    public void createSteps() throws SQLException {
	if (step == 1) {
	    panel.removeAll();
	} else {
	    panel = addStepOne();
	}
    }

    public JPanel addStepOne() throws SQLException {
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new MigLayout("", "[608.00px,left]", "[215px,top][400px:n,grow,top][bottom]"));

	JPanel formPanel = new JPanel();
	formPanel.setBounds(10, 43, 682, 66);
	formPanel.setLayout(new FormLayout(
		new ColumnSpec[] { ColumnSpec.decode("31px"), ColumnSpec.decode("76px"), FormSpecs.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("227px"), },
		new RowSpec[] { RowSpec.decode("12px"), RowSpec.decode("20px"), FormSpecs.RELATED_GAP_ROWSPEC,
			RowSpec.decode("20px"), }));

	JLabel lblName = new JLabel("Name");
	formPanel.add(lblName, "2, 2, right, center");

	inputName = new JTextField();
	inputName.setText("");
	formPanel.add(inputName, "4, 2, fill, default");
	inputName.setColumns(10);

	JLabel lblProjektNummer = new JLabel("Projekt Nummer");
	formPanel.add(lblProjektNummer, "2, 4, left, center");

	inputProjektNr = new JTextField();
	formPanel.add(inputProjektNr, "4, 4, fill, top");
	inputProjektNr.setColumns(10);

	mainPanel.add(formPanel, "cell 0 0,alignx left,aligny top");

	JButton btnWeiter = new JButton("Weiter");
	mainPanel.add(btnWeiter, "cell 0 2,alignx right,growy");

	materialListe = new MaterialListe();
	mainPanel.add(materialListe.bereichMaterialDatenbank(), "cell 0 1,alignx left,growy");

	btnWeiter.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		step = 1;
		try {
		    createSteps();
		    sqlConnection.dbConnect();

		    PartDataAdd<?> partNew = new PartDataAdd<IPartData<?>>(sqlConnection);
		    java.util.Date date = new java.util.Date();

		    partNew.setData(inputName.getText(), materialListe.getSelectedMaterialId(),
			    Integer.parseInt(inputProjektNr.getText()), date.getTime());

		    partNew.dbAdd();
		    sqlConnection.close();

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben.");

		} catch (PartException e) {
		    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());

		} finally {
		    try {
			sqlConnection.close();
		    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		    }
		}
		panel.validate();
		panel.repaint();
	    }
	});

	return mainPanel;
    }
}
