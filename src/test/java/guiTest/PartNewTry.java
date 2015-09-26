package guiTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class PartNewTry {

    private JFrame frame;
    private JTextField inputName;
    private JTextField inputProjektNr;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    PartNewTry window = new PartNewTry();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public PartNewTry() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 718, 468);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JPanel mainPanel = new JPanel();
	mainPanel.setBounds(0, 0, 702, 430);
	frame.getContentPane().add(mainPanel);
	
	JPanel formPanel = new JPanel();
	formPanel.setLayout(new FormLayout(new ColumnSpec[] {
			ColumnSpec.decode("31px"),
			ColumnSpec.decode("76px"),
			FormSpecs.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("227px"),},
		new RowSpec[] {
			RowSpec.decode("12px"),
			RowSpec.decode("20px"),
			FormSpecs.RELATED_GAP_ROWSPEC,
			RowSpec.decode("20px"),}));
	
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
	
	table = new JTable();
	table.setModel(new DefaultTableModel(
		new Object[][] {
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
			{null, null, null, null, null, null},
		},
		new String[] {
			"New column", "New column", "New column", "New column", "New column", "New column"
		}
	));
	mainPanel.setLayout(new MigLayout("", "[608.00px,left]", "[215px,top][400px:n,grow,top][bottom]"));
	mainPanel.add(formPanel, "cell 0 0,alignx left,aligny top");
	mainPanel.add(table, "cell 0 1,alignx left,growy");
	
	JButton btnWeiter = new JButton("Weiter");
	mainPanel.add(btnWeiter, "cell 0 2,alignx right,growy");
    }
}
