package guiTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class GuiPartListeTry {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    GuiPartListeTry window = new GuiPartListeTry();
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
    public GuiPartListeTry() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 889, 549);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new MigLayout("", "[grow,left]", "[grow,top]"));
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, "cell 0 0,alignx left,aligny top");
	panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
    }
}
