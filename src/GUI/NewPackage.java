package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Choice;
import javax.swing.JTextField;

import Model.Branch;
import Model.Employee;
import Model.PostClass;
import Model.Strings;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class NewPackage extends AbstractWindow implements ActionListener {

	private double TotalPrice;

	private JComboBox<Model.Service> service;
	private JComboBox<Branch> branch;
	private JComboBox<Employee> employee;
	private JComboBox<PostClass> postClass;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel date;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JTextField weight;
	private JTextField senderName;
	private JTextField senderSurname;
	private JTextField senderAddress;
	private JTextField senderCity;
	private JTextField senderPostCode;
	private JTextField receiverName;
	private JTextField receiverSurname;
	private JTextField receiverAddress;
	private JTextField receiverCity;
	private JTextField receiverPostCode;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JLabel label14;
	private JLabel label15;
	private JLabel label16;
	private JLabel label17;
	private JLabel label18;
	private JButton create;
	private JLabel lbldajeOOdosielateovi;
	private JLabel lbldajeOAdrestovi;
	private JLabel lblNzov;
	private JLabel lblTrieda;
	private JCheckBox insurance;
	private JCheckBox confirmation;
	private ItemListener itemLis;

	public NewPackage() {
		setTitle("Nov\u00E1 z\u00E1sielka");
		setSize(800, 600);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		label1 = new JLabel("Pros\u00EDm vypl\u0148te \u00FAdaje o novej z\u00E1sielke:");
		label1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label1.setBounds(10, 11, 302, 33);
		getContentPane().add(label1);

		label2 = new JLabel("\u00DAdaje o slu\u017Ebe");
		label2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label2.setBounds(393, 11, 302, 33);
		getContentPane().add(label2);

		service = new JComboBox<>();
		service.setBounds(519, 55, 176, 20);
		getContentPane().add(service);

		label3 = new JLabel("D\u00E1tum");
		label3.setBounds(10, 55, 46, 20);
		getContentPane().add(label3);

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date d = new Date();
		date = new JLabel(df.format(d));
		date.setBounds(136, 55, 176, 20);
		getContentPane().add(date);

		label5 = new JLabel("Hmotnos\u0165 (g)");
		label5.setBounds(10, 86, 79, 20);
		getContentPane().add(label5);

		weight = new JTextField();
		weight.setBounds(136, 86, 176, 20);
		getContentPane().add(weight);
		weight.setColumns(10);

		label6 = new JLabel("Pobo\u010Dka");
		label6.setBounds(10, 117, 64, 20);
		getContentPane().add(label6);

		branch = new JComboBox<>();
		branch.setBounds(136, 117, 176, 20);
		getContentPane().add(branch);

		label7 = new JLabel("Zamestnanec");
		label7.setBounds(10, 148, 79, 20);
		getContentPane().add(label7);

		employee = new JComboBox<>();
		employee.setBounds(136, 148, 176, 20);
		getContentPane().add(employee);

		label8 = new JLabel("Meno");
		label8.setBounds(10, 239, 79, 20);
		getContentPane().add(label8);

		label9 = new JLabel("Priezvisko");
		label9.setBounds(10, 270, 79, 20);
		getContentPane().add(label9);

		label10 = new JLabel("Ulica, \u010D\u00EDslo");
		label10.setBounds(10, 301, 79, 20);
		getContentPane().add(label10);

		label11 = new JLabel("Mesto");
		label11.setBounds(10, 332, 79, 20);
		getContentPane().add(label11);

		label12 = new JLabel("PS\u010C");
		label12.setBounds(10, 363, 79, 20);
		getContentPane().add(label12);

		senderName = new JTextField();
		senderName.setColumns(10);
		senderName.setBounds(136, 239, 176, 20);
		getContentPane().add(senderName);

		senderSurname = new JTextField();
		senderSurname.setColumns(10);
		senderSurname.setBounds(136, 270, 176, 20);
		getContentPane().add(senderSurname);

		senderAddress = new JTextField();
		senderAddress.setColumns(10);
		senderAddress.setBounds(136, 301, 176, 20);
		getContentPane().add(senderAddress);

		senderCity = new JTextField();
		senderCity.setColumns(10);
		senderCity.setBounds(136, 332, 176, 20);
		getContentPane().add(senderCity);

		senderPostCode = new JTextField();
		senderPostCode.setColumns(10);
		senderPostCode.setBounds(136, 363, 176, 20);
		getContentPane().add(senderPostCode);

		label14 = new JLabel("Meno");
		label14.setBounds(393, 239, 79, 20);
		getContentPane().add(label14);

		receiverName = new JTextField();
		receiverName.setColumns(10);
		receiverName.setBounds(519, 239, 176, 20);
		getContentPane().add(receiverName);

		label15 = new JLabel("Priezvisko");
		label15.setBounds(393, 270, 79, 20);
		getContentPane().add(label15);

		receiverSurname = new JTextField();
		receiverSurname.setColumns(10);
		receiverSurname.setBounds(519, 270, 176, 20);
		getContentPane().add(receiverSurname);

		label16 = new JLabel("Ulica, \u010D\u00EDslo");
		label16.setBounds(393, 301, 79, 20);
		getContentPane().add(label16);

		receiverAddress = new JTextField();
		receiverAddress.setColumns(10);
		receiverAddress.setBounds(519, 301, 176, 20);
		getContentPane().add(receiverAddress);

		label17 = new JLabel("Mesto");
		label17.setBounds(393, 332, 79, 20);
		getContentPane().add(label17);

		receiverCity = new JTextField();
		receiverCity.setColumns(10);
		receiverCity.setBounds(519, 332, 176, 20);
		getContentPane().add(receiverCity);

		label18 = new JLabel("PS\u010C");
		label18.setBounds(393, 363, 79, 20);
		getContentPane().add(label18);

		receiverPostCode = new JTextField();
		receiverPostCode.setColumns(10);
		receiverPostCode.setBounds(519, 363, 176, 20);
		getContentPane().add(receiverPostCode);

		create = new JButton("Vytvor nov\u00FA z\u00E1sielku");
		create.setBounds(519, 443, 176, 52);
		getContentPane().add(create);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!senderName.getText().equals("") && !senderSurname.getText().equals("")
						&& !senderAddress.getText().equals("") && !senderCity.getText().equals("")
						&& !senderPostCode.getText().equals("") && !receiverName.getText().equals("")
						&& !receiverSurname.getText().equals("") && !receiverAddress.getText().equals("")
						&& !receiverCity.getText().equals("") && !receiverPostCode.getText().equals("")
						&& !weight.getText().equals("")) {

					try {
						TotalPrice = CalculatePrice(postClass.getSelectedIndex() + 1, insurance.isSelected(),
								confirmation.isSelected(), Integer.parseInt(weight.getText()),
								service.getSelectedIndex() + 1);

						array = new ArrayList<>();
						
						array.add(senderName.getText());
						array.add(senderSurname.getText());
						array.add(senderAddress.getText());
						array.add(senderCity.getText());
						array.add(senderPostCode.getText());
						
						array.add(receiverName.getText());
						array.add(receiverSurname.getText());
						array.add(receiverAddress.getText());
						array.add(receiverCity.getText());
						array.add(receiverPostCode.getText());
						
						array.add(service.getSelectedIndex() + 1);
						array.add(postClass.getSelectedIndex() + 1);
						array.add(insurance.isSelected());
						array.add(confirmation.isSelected());
						
						array.add(date.getText());
						array.add(Integer.parseInt(weight.getText()));
						array.add((float) TotalPrice);
						array.add(branch.getSelectedIndex() + 1);
						Employee em = (Employee) employee.getSelectedItem();
						array.add(em.getId());
						
						sql.InsertNewPackage(array);
						
						InfoWindow("Udaje uspesne pridane");
						setVisible(false);
					} catch (NumberFormatException e) {
						InfoWindow("Neplatn˝ form·t v poli 'hmotnosù'");
					}
				} else
					InfoWindow("Nie s˙ vyplnenÈ vöetky polia!");
			}
		});

		lbldajeOOdosielateovi = new JLabel("\u00DAdaje o odosielate\u013Eovi:");
		lbldajeOOdosielateovi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbldajeOOdosielateovi.setBounds(10, 195, 302, 33);
		getContentPane().add(lbldajeOOdosielateovi);

		lbldajeOAdrestovi = new JLabel("\u00DAdaje o adres\u00E1tovi:");
		lbldajeOAdrestovi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbldajeOAdrestovi.setBounds(393, 195, 302, 33);
		getContentPane().add(lbldajeOAdrestovi);

		lblNzov = new JLabel("Druh z\u00E1sielky");
		lblNzov.setBounds(393, 55, 116, 20);
		getContentPane().add(lblNzov);

		lblTrieda = new JLabel("Trieda");
		lblTrieda.setBounds(393, 86, 116, 20);
		getContentPane().add(lblTrieda);

		postClass = new JComboBox<>();
		postClass.setBounds(519, 86, 176, 20);
		getContentPane().add(postClass);

		JLabel lblDobierka = new JLabel("Poistenie");
		lblDobierka.setBounds(393, 120, 116, 20);
		getContentPane().add(lblDobierka);

		insurance = new JCheckBox("");
		insurance.setBounds(598, 117, 97, 23);
		getContentPane().add(insurance);

		confirmation = new JCheckBox("");
		confirmation.setBounds(598, 148, 97, 23);
		getContentPane().add(confirmation);

		JLabel lblPotvrdenieODoruen = new JLabel("Potvrdenie o doru\u010Den\u00ED");
		lblPotvrdenieODoruen.setBounds(393, 151, 147, 20);
		getContentPane().add(lblPotvrdenieODoruen);
		
		itemLis = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				employee.removeAllItems();
				ArrayList<Object> emp = sql.SetEmployee(branch.getSelectedItem().toString());
				Iterator<Object> iter = emp.iterator();
				while (iter.hasNext()) {
					employee.addItem((Employee) iter.next());
				}
			}
		};
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		weight.setText("");
		senderName.setText("");
		senderSurname.setText("");
		senderAddress.setText("");
		senderCity.setText("");
		senderPostCode.setText("");
		receiverName.setText("");
		receiverSurname.setText("");
		receiverAddress.setText("");
		receiverCity.setText("");
		receiverPostCode.setText("");
		confirmation.setSelected(false);
		insurance.setSelected(false);
		service.removeAllItems();
		employee.removeAllItems();
		postClass.removeAllItems();
		branch.removeItemListener(itemLis);
		branch.removeAllItems();
		
		array = sql.SelectBranches();
		iter = array.iterator();
		while(iter.hasNext()){
			branch.addItem((Branch) iter.next());
		}

		array = sql.SetEmployee(branch.getSelectedItem().toString());
		iter = array.iterator();
		while(iter.hasNext()){
			employee.addItem((Employee) iter.next());
		}
		
		branch.addItemListener(itemLis);
		
		array = sql.SelectServices();
		iter = array.iterator();
		while(iter.hasNext()){
			service.addItem((Model.Service) iter.next());
		}
		
		array = sql.SelectClasses();
		iter = array.iterator();
		while(iter.hasNext()){
			postClass.addItem((PostClass) iter.next());
		}

		setVisible(true);
	}
}
