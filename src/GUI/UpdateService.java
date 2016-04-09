package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.Branch;
import Model.Employee;
import Model.PostClass;
import Model.SQLQueries;
import Model.Service;

@SuppressWarnings("serial")
public class UpdateService extends AbstractWindow implements ActionListener {
	private JTextField weight;
	private JComboBox<Branch> branch;
	private JComboBox<Employee> employee;
	private JComboBox<Service> service;
	private JComboBox<PostClass> postClass;
	private JCheckBox confirmation;
	private JCheckBox insurance;
	private JButton confirm;
	private ArrayList<Employee> emp;

	public UpdateService() {

		setTitle("Upravenie sluûby");
		setSize(400, 360);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JLabel label = new JLabel("Hmotnos\u0165 (g):");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label.setBounds(10, 11, 110, 20);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("Pobo\u010Dka podania:");
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_1.setBounds(10, 42, 110, 20);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Vybavuje:");
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_2.setBounds(10, 73, 110, 20);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Druh z\u00E1sielky:");
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_3.setBounds(10, 104, 145, 20);
		getContentPane().add(label_3);

		JLabel label_4 = new JLabel("Trieda:");
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(10, 135, 145, 20);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("Potvrdenie o doru\u010Den\u00ED:");
		label_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_5.setBounds(10, 166, 149, 20);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("Poistenie:");
		label_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_6.setBounds(10, 197, 145, 20);
		getContentPane().add(label_6);

		weight = new JTextField();
		weight.setBounds(174, 11, 186, 20);
		getContentPane().add(weight);
		weight.setColumns(10);

		branch = new JComboBox<Branch>();
		branch.setBounds(174, 42, 186, 20);
		getContentPane().add(branch);

		employee = new JComboBox<Employee>();
		employee.setBounds(174, 73, 186, 20);
		getContentPane().add(employee);

		service = new JComboBox<Service>();
		service.setBounds(174, 104, 186, 20);
		getContentPane().add(service);

		postClass = new JComboBox<PostClass>();
		postClass.setBounds(174, 135, 186, 20);
		getContentPane().add(postClass);

		confirmation = new JCheckBox("");
		confirmation.setBounds(174, 165, 97, 23);
		getContentPane().add(confirmation);

		insurance = new JCheckBox("");
		insurance.setBounds(174, 196, 97, 23);
		getContentPane().add(insurance);

		confirm = new JButton("Potvrdi\u0165");
		confirm.setBounds(215, 272, 145, 38);
		getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double TotalPrice = CalculatePrice(postClass.getSelectedIndex() + 1, insurance.isSelected(),
							confirmation.isSelected(), Integer.parseInt(weight.getText()),
							service.getSelectedIndex() + 1);

					array = new ArrayList<>();
					array.add(Integer.parseInt(weight.getText()));
					array.add(branch.getSelectedIndex() + 1);
					Employee em = (Employee) employee.getSelectedItem();
					array.add(em.getId());
					array.add(service.getSelectedIndex() + 1);
					array.add(postClass.getSelectedIndex() + 1);
					array.add(confirmation.isSelected());
					array.add(insurance.isSelected());
					array.add(TotalPrice);

					sql.UpdatePackage(array, Overview.getPackageId());

					InfoWindow("⁄daje boli ˙speöne aktualizovanÈ");
					setVisible(false);
					Detail d = new Detail();
					d.actionPerformed(e);
					d.setVisible(true);
					Overview.printToTable();
				} catch (NumberFormatException nfe) {
					InfoWindow("Neplatn˝ form·t v poli 'hmotnosù'");
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		array = sql.SelectBranches();
		iter = array.iterator();
		while (iter.hasNext()) {
			branch.addItem(((Branch) iter.next()));
		}

		array = sql.SetEmployee(branch.getSelectedItem().toString());
		iter = array.iterator();
		emp = new ArrayList<>();
		while (iter.hasNext()) {
			Employee emp = (Employee) iter.next();
			employee.addItem(emp);
			this.emp.add(emp);
		}

		branch.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				employee.removeAllItems();
				ArrayList<Object> emp = sql.SetEmployee(branch.getSelectedItem().toString());
				Iterator<Object> iter = emp.iterator();
				while (iter.hasNext()) {
					Employee temp = (Employee) iter.next();
					employee.addItem(temp);
				}
				
			}
		});

		array = sql.SelectServices();
		iter = array.iterator();
		while (iter.hasNext()) {
			service.addItem((Service) iter.next());
		}

		array = sql.SelectClasses();
		iter = array.iterator();
		while (iter.hasNext()) {
			postClass.addItem(((PostClass) iter.next()));
		}

		array = sql.SelectPackageDetailsForUpdate(Overview.getPackageId());
		iter = array.iterator();

		weight.setText(iter.next().toString());
		branch.setSelectedIndex(((int) iter.next()) - 1);
		service.setSelectedIndex(((int) iter.next()) - 1);
		postClass.setSelectedIndex(((int) iter.next()) - 1);
		confirmation.setSelected(Boolean.parseBoolean(iter.next().toString()));
		insurance.setSelected(Boolean.parseBoolean(iter.next().toString()));

		/*
		 * int employeeId = (int) array.get(idx); Iterator<Employee> emIter =
		 * emp.iterator(); while (emIter.hasNext()) { Employee em =
		 * emIter.next(); if (em.getId() == employeeId) {
		 * employee.setSelectedItem(em); } } idx++;
		 */

		setVisible(true);
	}
}
