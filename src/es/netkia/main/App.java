package es.netkia.main;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import es.netkia.gui.AppUI;
import es.netkia.io.PropertiesManager;

public class App {
	
	public final static String NAME = "Script Generator";
	public final static String VERSION = "2.0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppUI window = new AppUI(new PropertiesManager());
					window.frame.setTitle(App.NAME +" "+ App.VERSION);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Something went wrong. \nCause: "+e.toString(),"Exit", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
}
