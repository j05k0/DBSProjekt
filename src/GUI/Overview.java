package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import Model.Branch;
import Model.Employee;
import Model.SQLQueries;
import Model.Strings;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;

@SuppressWarnings("serial")
public class Overview extends AbstractWindow implements ActionListener {

	private static int packageId = 0;
	private static JTable table;
	private static JTable statsTable;
	private JButton edit;
	private JButton delete;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox<Branch> branch;
	private JComboBox<Employee> employee;
	private JComboBox<String> state;
	private JLabel lblNewLabel;
	private JLabel lblVybavujciZamestnanec;
	private JLabel lblStavZsielky;
	private JButton filter;
	private JTextField senderName;
	private JTextField senderSurname;
	private JTextField sendDate;

	public Overview() {
		setTitle("Zobrazenie tabulky");
		setSize(1000, 700);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		edit = new JButton("Zobrazi\u0165 detail");
		edit.setBounds(726, 11, 248, 48);
		getContentPane().add(edit);
		edit.addActionListener(new Detail());

		delete = new JButton("Odstr\u00E1ni\u0165 z\u00E1znam");
		delete.setBounds(726, 69, 248, 48);
		getContentPane().add(delete);
		
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 706, 386);
		getContentPane().add(scrollPane);
		
		statsTable = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		
		scrollPane_1 = new JScrollPane(statsTable);
		scrollPane_1.setBounds(726, 163, 248, 234);
		getContentPane().add(scrollPane_1);

		branch = new JComboBox<Branch>();
		branch.setBounds(170, 408, 182, 20);
		getContentPane().add(branch);
		
		employee = new JComboBox<Employee>();
		employee.setBounds(170, 439, 182, 20);
		getContentPane().add(employee);
	
		state = new JComboBox<String>();
		state.setBounds(170, 470, 182, 20);
		getContentPane().add(state);

		lblNewLabel = new JLabel("Pobo\u010Dka podania");
		lblNewLabel.setBounds(10, 408, 150, 20);
		getContentPane().add(lblNewLabel);

		lblVybavujciZamestnanec = new JLabel("Vybavuj\u00FAci zamestnanec");
		lblVybavujciZamestnanec.setBounds(10, 439, 150, 20);
		getContentPane().add(lblVybavujciZamestnanec);

		lblStavZsielky = new JLabel("Stav z\u00E1sielky");
		lblStavZsielky.setBounds(10, 470, 150, 20);
		getContentPane().add(lblStavZsielky);

		filter = new JButton("Filtrova\u0165 z\u00E1sielky");
		filter.setBounds(534, 501, 182, 48);
		getContentPane().add(filter);
		
		JLabel lblNewLabel_1 = new JLabel("Po\u010Det z\u00E1sielok na zamestnanca");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(726, 128, 248, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblMeno = new JLabel("Meno odosielate\u013Ea");
		lblMeno.setBounds(374, 408, 150, 20);
		getContentPane().add(lblMeno);
		
		JLabel lblPriezviskoOdosielatea = new JLabel("Priezvisko odosielate\u013Ea");
		lblPriezviskoOdosielatea.setBounds(374, 439, 150, 20);
		getContentPane().add(lblPriezviskoOdosielatea);
		
		JLabel lblZsielkaPodanDa = new JLabel("Z\u00E1sielka podan\u00E1 d\u0148a");
		lblZsielkaPodanDa.setBounds(374, 470, 150, 20);
		getContentPane().add(lblZsielkaPodanDa);
		
		senderName = new JTextField();
		senderName.setBounds(534, 408, 182, 20);
		getContentPane().add(senderName);
		senderName.setColumns(10);
		
		senderSurname = new JTextField();
		senderSurname.setColumns(10);
		senderSurname.setBounds(534, 439, 182, 20);
		getContentPane().add(senderSurname);
		
		sendDate = new JTextField();
		sendDate.setColumns(10);
		sendDate.setBounds(534, 470, 182, 20);
		getContentPane().add(sendDate);
		
		filter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee em = (Employee) employee.getSelectedItem();
				array = new ArrayList<>();
				array.add(branch.getSelectedIndex());
				array.add(em.getId());
				array.add(state.getSelectedIndex());
				table.setModel(sql.SelectPackages(array));
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				edit.removeAll();
				int row = table.getSelectedRow();
				int col = 0;
				setPackageId((int) table.getValueAt(row, col));
				System.out.println("Id zasielky " + getPackageId());
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sql.DeletePackage(getPackageId());
				InfoWindow("Zásielka bola úspešne odstránená");
				printToTable();
			}
		});
		
		
	}

	public static int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		Overview.packageId = packageId;
	}
	
	public static void printToTable(){
		table.setModel(sql.SelectDefaultTable());
		statsTable.setModel(sql.SelectStatsOfEmloyees());
	}
	
	public JTable getTable() {
		return table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		branch.removeAllItems();
		employee.removeAllItems();
		state.removeAllItems();
		
		Branch tmp = new Branch();
		tmp.setName("(nezáleží)");
		branch.addItem(tmp);
		
		Employee tmp1 = new Employee();
		tmp1.setName("(nezáleží)");
		tmp1.setSurname("");
		employee.addItem(tmp1);
		
		state.addItem("(nezáleží)");
		
		table.setModel(sql.SelectDefaultTable());
		statsTable.setModel(sql.SelectStatsOfEmloyees());

		array = sql.SelectBranches();
		iter = array.iterator();
		while (iter.hasNext()) {
			branch.addItem((Branch) iter.next());
		}

		array = sql.SelectEmployees();
		iter = array.iterator();
		while (iter.hasNext()) {
			employee.addItem((Employee) iter.next());
		}

		branch.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(branch.getItemCount() > 1){
					employee.removeAllItems();
					Employee tmp1 = new Employee();
					tmp1.setName("(nezáleží)");
					tmp1.setSurname("");
					employee.addItem(tmp1);
					if (branch.getSelectedIndex() == 0) {
						array = sql.SelectEmployees();
					} else {
						array = sql.SetEmployee(branch.getSelectedItem().toString());
					}
					iter = array.iterator();
					while (iter.hasNext()) {
						Employee emp = (Employee) iter.next();
						employee.addItem(emp);
					}
				}
			}
		});

		array = sql.SelectState();
		iter = array.iterator();
		while (iter.hasNext()) {
			state.addItem(iter.next().toString());
		}
		
		setVisible(true);
	}
}
