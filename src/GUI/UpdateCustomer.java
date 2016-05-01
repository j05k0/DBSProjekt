package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import Model.ElasticSearch;
import Model.SQLQueries;

import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class UpdateCustomer extends AbstractWindow implements ActionListener {
	private JTextField name;
	private JTextField surname;
	private JTextField address;
	private JTextField city;
	private JTextField postCode;
	private JButton confirm;
	private SQLQueries s;
	private ElasticSearch es;
	private ArrayList<Object> array;
	private int choice;
	
	public UpdateCustomer(int choice) {
				
		this.choice = choice;
		
		setTitle("Upravenie zákazníka");
		setSize(351, 278);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(136, 11, 176, 20);
		getContentPane().add(name);
		
		JLabel label = new JLabel("Meno");
		label.setBounds(10, 11, 79, 20);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Priezvisko");
		label_1.setBounds(10, 42, 79, 20);
		getContentPane().add(label_1);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(136, 42, 176, 20);
		getContentPane().add(surname);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(136, 73, 176, 20);
		getContentPane().add(address);
		
		JLabel label_2 = new JLabel("Ulica, \u010D\u00EDslo");
		label_2.setBounds(10, 73, 79, 20);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Mesto");
		label_3.setBounds(10, 104, 79, 20);
		getContentPane().add(label_3);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(136, 104, 176, 20);
		getContentPane().add(city);
		
		JLabel label_4 = new JLabel("PS\u010C");
		label_4.setBounds(10, 135, 79, 20);
		getContentPane().add(label_4);
		
		postCode = new JTextField();
		postCode.setColumns(10);
		postCode.setBounds(136, 135, 176, 20);
		getContentPane().add(postCode);
		
		confirm = new JButton("Potvrdi\u0165");
		confirm.setBounds(136, 184, 176, 44);
		getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				array = new ArrayList<>();
				array.add(name.getText());
				array.add(surname.getText());
				array.add(address.getText());
				array.add(city.getText());
				array.add(postCode.getText());
				if(choice == 1){
					s.UpdateCustomer(array, Detail.getSenderId());
					es.UpdateCustomer(array, Detail.getSenderId());
				}
				if(choice == 2){
					s.UpdateCustomer(array, Detail.getReceiverId());
					es.UpdateCustomer(array, Detail.getReceiverId());
				}
				InfoWindow("Údaje boli úspešne aktualizované");
				setVisible(false);
				Detail d = new Detail();
				d.actionPerformed(e);
				d.setVisible(true);
				Overview.printToTable();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		s = new SQLQueries();
		es = new ElasticSearch();
		if(choice == 1){
			array = s.SelectCustomerDetails(Detail.getSenderId());
		}
		if(choice == 2){
			array = s.SelectCustomerDetails(Detail.getReceiverId());
		}
		int i = 1;
		name.setText(array.get(i).toString());
		i++;
		surname.setText(array.get(i).toString());
		i++;
		address.setText(array.get(i).toString());
		i++;
		city.setText(array.get(i).toString());
		i++;
		postCode.setText(array.get(i).toString());
		
		setVisible(true);
	}

}
