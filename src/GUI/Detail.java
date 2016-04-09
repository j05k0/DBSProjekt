package GUI;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import Model.SQLQueries;
import Model.Strings;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Detail extends AbstractWindow implements ActionListener {

	private int packageId;
	private static int senderId;
	private static int receiverId;
	private JLabel service;
	private JLabel postClass;
	private JLabel date;
	private JLabel weight;
	private JLabel branch;
	private JLabel employee;
	private JLabel sender;
	private JLabel senderAddress;
	private JLabel senderCity;
	private JLabel senderPost;
	private JLabel receiver;
	private JLabel receiverAddress;
	private JLabel receiverCity;
	private JLabel receiverPost;
	private JLabel state;
	private JLabel price;
	private JCheckBox confirmation;
	private JCheckBox insurance;
	private JButton updateService;
	private JButton updateSender;
	private JButton updateReceiver;
	private JButton updateState;

	public static int getSenderId() {
		return senderId;
	}
	
	public static int getReceiverId() {
		return receiverId;
	}
	
	public static void setSenderId(int senderId) {
		Detail.senderId = senderId;
	}

	public static void setReceiverId(int receiverId) {
		Detail.receiverId = receiverId;
	}
	
	public Detail() {

		setTitle("Detail z\u00E1sielky");
		setSize(625, 613);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblDetailndajeZsielky = new JLabel("Detailn\u00E9 \u00FAdaje z\u00E1sielky");
		lblDetailndajeZsielky.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailndajeZsielky.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetailndajeZsielky.setBounds(10, 11, 589, 33);
		getContentPane().add(lblDetailndajeZsielky);

		JLabel lblDruh = new JLabel("Druh z\u00E1sielky:");
		lblDruh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDruh.setBounds(300, 51, 145, 20);
		getContentPane().add(lblDruh);

		service = new JLabel("-");
		service.setBounds(455, 51, 144, 20);
		getContentPane().add(service);

		JLabel lblTrieda = new JLabel("Trieda:");
		lblTrieda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblTrieda.setBounds(300, 80, 145, 20);
		getContentPane().add(lblTrieda);

		postClass = new JLabel("-");
		postClass.setBounds(455, 80, 144, 20);
		getContentPane().add(postClass);

		JLabel lblPotvrdenieODoruen = new JLabel("Potvrdenie o doru\u010Den\u00ED:");
		lblPotvrdenieODoruen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPotvrdenieODoruen.setBounds(300, 111, 149, 20);
		getContentPane().add(lblPotvrdenieODoruen);

		confirmation = new JCheckBox("");
		confirmation.setEnabled(false);
		confirmation.setBounds(455, 110, 144, 23);
		getContentPane().add(confirmation);

		JLabel lblPoistenie = new JLabel("Poistenie:");
		lblPoistenie.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPoistenie.setBounds(300, 142, 145, 20);
		getContentPane().add(lblPoistenie);

		insurance = new JCheckBox("");
		insurance.setEnabled(false);
		insurance.setBounds(455, 141, 144, 23);
		getContentPane().add(insurance);

		JLabel lblDtumPodania = new JLabel("D\u00E1tum podania:");
		lblDtumPodania.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDtumPodania.setBounds(10, 51, 110, 20);
		getContentPane().add(lblDtumPodania);

		date = new JLabel("-");
		date.setBounds(130, 51, 160, 20);
		getContentPane().add(date);

		JLabel lblHmotnos = new JLabel("Hmotnos\u0165 (g):");
		lblHmotnos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblHmotnos.setBounds(10, 82, 110, 20);
		getContentPane().add(lblHmotnos);

		weight = new JLabel("-");
		weight.setBounds(130, 82, 160, 20);
		getContentPane().add(weight);

		JLabel lblPoboka = new JLabel("Pobo\u010Dka podania:");
		lblPoboka.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPoboka.setBounds(10, 113, 110, 20);
		getContentPane().add(lblPoboka);

		branch = new JLabel("-");
		branch.setBounds(130, 113, 160, 20);
		getContentPane().add(branch);

		JLabel lblVybavuje = new JLabel("Vybavuje:");
		lblVybavuje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblVybavuje.setBounds(10, 144, 110, 20);
		getContentPane().add(lblVybavuje);

		employee = new JLabel("-");
		employee.setBounds(130, 144, 160, 20);
		getContentPane().add(employee);

		JLabel lblOdosielate = new JLabel("Odosielate\u013E:");
		lblOdosielate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblOdosielate.setBounds(10, 232, 110, 20);
		getContentPane().add(lblOdosielate);

		JLabel lblUlicaslo = new JLabel("Ulica, \u010D\u00EDslo:");
		lblUlicaslo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblUlicaslo.setBounds(10, 263, 110, 20);
		getContentPane().add(lblUlicaslo);

		JLabel lblMesto = new JLabel("Mesto:");
		lblMesto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblMesto.setBounds(10, 294, 110, 20);
		getContentPane().add(lblMesto);

		JLabel lblPs = new JLabel("PS\u010C:");
		lblPs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPs.setBounds(10, 325, 110, 20);
		getContentPane().add(lblPs);

		sender = new JLabel("-");
		sender.setBounds(130, 232, 160, 20);
		getContentPane().add(sender);

		senderAddress = new JLabel("-");
		senderAddress.setBounds(130, 263, 160, 20);
		getContentPane().add(senderAddress);

		senderCity = new JLabel("-");
		senderCity.setBounds(130, 294, 160, 20);
		getContentPane().add(senderCity);

		senderPost = new JLabel("-");
		senderPost.setBounds(130, 325, 160, 20);
		getContentPane().add(senderPost);

		JLabel lblAdrest = new JLabel("Adres\u00E1t:");
		lblAdrest.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAdrest.setBounds(300, 232, 110, 20);
		getContentPane().add(lblAdrest);

		receiver = new JLabel("-");
		receiver.setBounds(455, 232, 144, 20);
		getContentPane().add(receiver);

		JLabel lblUlicaslo_1 = new JLabel("Ulica, \u010D\u00EDslo:");
		lblUlicaslo_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblUlicaslo_1.setBounds(300, 263, 110, 20);
		getContentPane().add(lblUlicaslo_1);

		receiverAddress = new JLabel("-");
		receiverAddress.setBounds(455, 263, 144, 20);
		getContentPane().add(receiverAddress);

		JLabel lblMesto_1 = new JLabel("Mesto:");
		lblMesto_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblMesto_1.setBounds(300, 294, 110, 20);
		getContentPane().add(lblMesto_1);

		receiverCity = new JLabel("-");
		receiverCity.setBounds(455, 294, 144, 20);
		getContentPane().add(receiverCity);

		receiverPost = new JLabel("-");
		receiverPost.setBounds(455, 325, 144, 20);
		getContentPane().add(receiverPost);

		JLabel lblPs_1 = new JLabel("PS\u010C:");
		lblPs_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPs_1.setBounds(300, 325, 110, 20);
		getContentPane().add(lblPs_1);
		
		JLabel lblAktulnyStavZsielky = new JLabel("Aktu\u00E1lny stav z\u00E1sielky:");
		lblAktulnyStavZsielky.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAktulnyStavZsielky.setBounds(10, 429, 159, 20);
		getContentPane().add(lblAktulnyStavZsielky);
		
		state = new JLabel("-");
		state.setBounds(179, 429, 160, 20);
		getContentPane().add(state);
		
		JLabel lblCelkovCenaSluieb = new JLabel("Celkov\u00E1 cena slu\u017Eieb:");
		lblCelkovCenaSluieb.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCelkovCenaSluieb.setBounds(10, 464, 159, 20);
		getContentPane().add(lblCelkovCenaSluieb);
		
		price = new JLabel("-");
		price.setBounds(179, 460, 160, 20);
		getContentPane().add(price);
		
		updateService = new JButton("Upravi\u0165 slu\u017Ebu");
		updateService.setBounds(439, 182, 160, 39);
		getContentPane().add(updateService);
		updateService.addActionListener(new UpdateService());
		updateService.addActionListener(new Close());

		updateSender = new JButton("Upravi\u0165 odosielate\u013Ea");
		updateSender.setBounds(130, 356, 160, 39);
		getContentPane().add(updateSender);
		updateSender.addActionListener(new UpdateCustomer(1));
		updateSender.addActionListener(new Close());
		
		updateReceiver = new JButton("Upravi\u0165 adres\u00E1ta");
		updateReceiver.setBounds(439, 356, 160, 39);
		getContentPane().add(updateReceiver);
		updateReceiver.addActionListener(new UpdateCustomer(2));
		updateReceiver.addActionListener(new Close());
		
		updateState = new JButton("Upravi\u0165 stav");
		updateState.setBounds(300, 428, 145, 23);
		getContentPane().add(updateState);
		updateState.addActionListener(new UpdateState());
		updateState.addActionListener(new Close());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		packageId = Overview.getPackageId();
		if (packageId == 0) {
			InfoWindow("Nebola zvolená žiadna položka!");
		} else {
			array = sql.SelectPackageDetails(packageId);
			int i = 0;
			date.setText(array.get(i).toString());
			i++;
			weight.setText(array.get(i).toString());
			i++;
			branch.setText(array.get(i).toString());
			i++;
			employee.setText(array.get(i).toString());
			i++;
			service.setText(array.get(i).toString());
			i++;
			postClass.setText(array.get(i).toString());
			i++;
			confirmation.setSelected(Boolean.parseBoolean(array.get(i).toString()));
			i++;
			insurance.setSelected(Boolean.parseBoolean(array.get(i).toString()));
			i++;
			sender.setText(array.get(i).toString());
			i++;
			senderAddress.setText(array.get(i).toString());
			i++;
			senderCity.setText(array.get(i).toString());
			i++;
			senderPost.setText(array.get(i).toString());
			i++;
			receiver.setText(array.get(i).toString());
			i++;
			receiverAddress.setText(array.get(i).toString());
			i++;
			receiverCity.setText(array.get(i).toString());
			i++;
			receiverPost.setText(array.get(i).toString());
			i++;
			state.setText(array.get(i).toString());
			i++;
			price.setText(array.get(i).toString());
			i++;
			setSenderId((int) array.get(i));
			i++;
			setReceiverId((int) array.get(i));
						
			setVisible(true);
		}
	}
}
