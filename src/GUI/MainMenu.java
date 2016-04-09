package GUI;

import javax.swing.WindowConstants;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class MainMenu extends AbstractWindow {

	private JButton newConsignment;
	private JButton overview;
	
	public MainMenu(){
		setTitle("Main menu");
		setSize(401, 248);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		newConsignment = new JButton("Nov\u00E1 z\u00E1sielka");
		newConsignment.setBounds(20, 112, 157, 50);
		getContentPane().add(newConsignment);
		newConsignment.addActionListener(new NewPackage());
		
		JLabel lblVitajteVSystme = new JLabel("Vitajte v syst\u00E9me na spr\u00E1vu z\u00E1sielok po\u0161ty");
		lblVitajteVSystme.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVitajteVSystme.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitajteVSystme.setBounds(10, 11, 365, 90);
		getContentPane().add(lblVitajteVSystme);
		
		overview = new JButton("Preh\u013Ead z\u00E1sielok");
		overview.setBounds(204, 112, 157, 50);
		getContentPane().add(overview);
		overview.addActionListener(new Overview());
		
	}
}
