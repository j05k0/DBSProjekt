package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

import Model.SQLQueries;

public class UpdateState extends AbstractWindow implements ActionListener {

	private JComboBox<String> state;
	private JButton confirm;
	
	public UpdateState() {
		setTitle("Upravenie stavu zásielky");
		setSize(303, 116);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		state = new JComboBox<String>();
		state.setBounds(124, 11, 153, 20);
		getContentPane().add(state);
		
		confirm = new JButton("Potvrdi\u0165");
		confirm.setBounds(188, 42, 89, 23);
		getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sql.UpdateState(state.getSelectedIndex() + 1, Overview.getPackageId());
				InfoWindow("Stav zásielky bol zmenený");
				setVisible(false);
				Detail d = new Detail();
				d.actionPerformed(e);
				d.setVisible(true);
				Overview.printToTable();
			}
		});
		
		JLabel lblStav = new JLabel("Stav z\u00E1sielky");
		lblStav.setBounds(10, 14, 104, 14);
		getContentPane().add(lblStav);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		array = sql.SelectState();
		Iterator<Object> i = array.iterator();
		while(i.hasNext()){
			state.addItem(i.next().toString());
		}
		setVisible(true);
	}
}
