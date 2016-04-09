package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Model;
import Model.SQLQueries;
import Model.Strings;

@SuppressWarnings("serial")
public abstract class AbstractWindow extends JFrame {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	protected static SQLQueries sql = new SQLQueries();
	protected ArrayList<Object> array;
	protected Iterator<Object> iter;
	protected static Model model = new Model();
	
	class Close implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
	class MainWindow implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			JFrame f = new MainMenu();
			f.setVisible(true);
		}
	}
	
	public void InfoWindow(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	public double CalculatePrice(int classId, boolean insurance, boolean confirm, int weight, int serviceId) {
		double price = 0;

		conn = null;
		stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT cena FROM trieda WHERE id = " + classId);
			while (rs.next()) {
				price += rs.getFloat("cena");
			}

			rs = stmt.executeQuery("SELECT cena FROM druh_zasielky WHERE id = " + serviceId);
			while (rs.next()) {
				price += rs.getFloat("cena");
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		if (insurance) {
			price += 1;
		}

		if (confirm) {
			price += 0.5;
		}

		if (weight >= 50 && weight < 100)
			price += 0.2;
		if (weight >= 100 && weight < 500)
			price += 0.45;
		if (weight >= 500)
			price += 1;

		System.out.println("Cena je " + price);
		return price;
	}
}
