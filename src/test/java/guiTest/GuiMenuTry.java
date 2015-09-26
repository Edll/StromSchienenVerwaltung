package guiTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;


public class GuiMenuTry {

    private JFrame frame;
    JPanel panel_1 = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    GuiMenuTry window = new GuiMenuTry();
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
    public GuiMenuTry() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JMenuBar mainMenuBar = new JMenuBar();
	frame.setJMenuBar(mainMenuBar);

	JMenu mPart = new JMenu("Werkstücke");
	mainMenuBar.add(mPart);

	JMenuItem itemListPart = new JMenuItem("Liste");
	itemListPart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mPart.add(itemListPart);

	JMenuItem itemNeuPart = new JMenuItem("Neu");
	itemNeuPart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mPart.add(itemNeuPart);

	JMenu mMaterial = new JMenu("Material");
	mainMenuBar.add(mMaterial);

	JMenuItem itemListMaterial = new JMenuItem("Liste");
	itemListMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mMaterial.add(itemListMaterial);

	JMenuItem itemNeuMaterial = new JMenuItem("Neu");
	itemNeuMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mMaterial.add(itemNeuMaterial);

	JMenu mSettings = new JMenu("Einstellungen");
	mainMenuBar.add(mSettings);

	JMenuItem itemMaterial = new JMenuItem("Material");
	itemMaterial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	mSettings.add(itemMaterial);
	frame.getContentPane().setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBounds(0, 0, 424, 230);
	frame.getContentPane().add(panel);

	JLabel lblNewLabel = new JLabel("test2");
	panel_1.add(lblNewLabel);
    }
}
