package Model;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLQueries {

	private Connection conn;
	private Statement s;
	private PreparedStatement preps;
	private ResultSet rs;
	private ArrayList<Object> array;
	private DefaultTableModel defaultTable;
	private Model m;

	// select podrobnych detailov zasielky
	public ArrayList<Object> SelectPackageDetails(int packageId) {

		array = new ArrayList<>();
		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na zobrazenie detailu");

			s = conn.createStatement();
			rs = s.executeQuery(
					"SELECT d.nazov AS druh, t.nazov AS trieda, s.potvrdenie_o_doruceni, s.poistenie, z.podana, z.hmotnost, p.nazov AS pobocka, zam.meno || ' ' || zam.priezvisko AS zamestnanec, odos.id AS odos_id, odos.meno || ' ' || odos.priezvisko AS odosielatel, odos.ulica_cislo AS odosielatel_ulica, odos.mesto AS odosielatel_mesto, odos.psc AS odosielatel_psc, adre.id AS adre_id, adre.meno || ' ' || adre.priezvisko AS adresat, adre.ulica_cislo AS adresat_ulica, adre.mesto AS adresat_mesto, adre.psc AS adresat_psc, stav.stav, z.cena FROM zasielka z JOIN sluzba s ON s.id = z.sluzba_id JOIN druh_zasielky d ON d.id = s.druh_id JOIN pobocka p ON p.id = z.pobocka_id JOIN trieda t ON t.id = s.trieda_id JOIN zamestnanec zam ON zam.id = z.zamestnanec_id JOIN zakaznik odos ON odos.id = z.odosielatel_id JOIN zakaznik adre ON adre.id = z.adresat_id JOIN stav_zasielky stav ON stav.id = z.stav_zasielky_id WHERE z.id = "
							+ packageId);

			while (rs.next()) {
				array.add(rs.getString("podana"));
				array.add(rs.getString("hmotnost"));
				array.add(rs.getString("pobocka"));
				array.add(rs.getString("zamestnanec"));
				array.add(rs.getString("druh"));
				array.add(rs.getString("trieda"));
				array.add(rs.getBoolean("potvrdenie_o_doruceni"));
				array.add(rs.getBoolean("poistenie"));
				array.add(rs.getString("odosielatel"));
				array.add(rs.getString("odosielatel_ulica"));
				array.add(rs.getString("odosielatel_mesto"));
				array.add(rs.getString("odosielatel_psc"));
				array.add(rs.getString("adresat"));
				array.add(rs.getString("adresat_ulica"));
				array.add(rs.getString("adresat_mesto"));
				array.add(rs.getString("adresat_psc"));
				array.add(rs.getString("stav"));
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.CEILING);
				array.add(df.format(rs.getFloat("cena")) + " €");
				array.add(rs.getInt("odos_id"));
				array.add(rs.getInt("adre_id"));
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return array;
	}

	// select sluzieb z tabulky sluzba
	public ArrayList<Object> SelectServices() {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie druhov zasielky");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM druh_zasielky");

			while (rs.next()) {
				Service service = new Service();
				service.setName(rs.getString("nazov"));
				service.setServiceId(rs.getInt("id"));
				service.setPrice(rs.getDouble("cena"));
				array.add(service);
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	// select pobociek z tabulky pobocka
	public ArrayList<Object> SelectBranches() {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie pobociek");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM pobocka");

			while (rs.next()) {
				Branch branch = new Branch();
				branch.setName(rs.getString("nazov"));
				branch.setAddress(rs.getString("ulica_cislo"));
				branch.setCity(rs.getString("mesto"));
				array.add(branch);
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	public ArrayList<Object> SelectEmployees() {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie zamestnancov");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM zamestnanec");

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("meno"));
				employee.setSurname(rs.getString("priezvisko"));
				employee.setBranch(rs.getString("pozicia_id"));
				employee.setBranchId(rs.getInt("pobocka_id"));
				array.add(employee);
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	// nastavenie zamestananca na zaklade vybranej pobocky
	public ArrayList<Object> SetEmployee(String branch) {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na aktualizaciu choice");

			s = conn.createStatement();
			rs = s.executeQuery(
					"SELECT z.id, z.meno, z.priezvisko, p.nazov, z.pobocka_id FROM zamestnanec z JOIN pobocka p ON p.id = z.pobocka_id WHERE p.nazov LIKE '"
							+ branch + "'");

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("meno"));
				employee.setSurname(rs.getString("priezvisko"));
				employee.setBranch(rs.getString("nazov"));
				employee.setBranchId(rs.getInt("pobocka_id"));
				array.add(employee);
			}

			rs.close();
			s.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	// select tried zasielok
	public ArrayList<Object> SelectClasses() {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie pobociek");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM trieda");

			while (rs.next()) {
				PostClass postClass = new PostClass();
				postClass.setName(rs.getString("nazov"));
				postClass.setPrice(rs.getInt("cena"));
				postClass.setPostClassId(rs.getInt("id"));
				array.add(postClass);
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	// select detailov zasielky pri updatovani zasielky
	public ArrayList<Object> SelectPackageDetailsForUpdate(int packageId) {

		array = new ArrayList<>();
		conn = null;
		preps = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie detailov sluzby pri update");

			String sql = "SELECT z.hmotnost, z.pobocka_id, z.zamestnanec_id, s.druh_id, s.trieda_id, s.potvrdenie_o_doruceni, s.poistenie FROM zasielka z JOIN sluzba s ON s.id = z.sluzba_id WHERE z.id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, packageId);
			if (preps.execute()) {
				rs = preps.getResultSet();
				while (rs.next()) {
					array.add(rs.getInt("hmotnost"));
					array.add(rs.getInt("pobocka_id"));
					array.add(rs.getInt("druh_id"));
					array.add(rs.getInt("trieda_id"));
					array.add(rs.getBoolean("potvrdenie_o_doruceni"));
					array.add(rs.getBoolean("poistenie"));
				}
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	public ArrayList<Object> SelectCustomerDetails(int customerId) {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie detailov zakaznika");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM zakaznik WHERE id = " + customerId);

			while (rs.next()) {
				array.add(rs.getInt("id"));
				array.add(rs.getString("meno"));
				array.add(rs.getString("priezvisko"));
				array.add(rs.getString("ulica_cislo"));
				array.add(rs.getString("mesto"));
				array.add(rs.getString("psc"));
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	public ArrayList<Object> SelectState() {
		array = new ArrayList<>();

		conn = null;
		s = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie stavov zasielky");

			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM stav_zasielky");

			while (rs.next()) {
				array.add(rs.getString("stav"));
			}

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return array;
	}

	public DefaultTableModel SelectPackages(ArrayList<Object> array) {

		int branchId = (int) array.get(0);
		int employeeId = (int) array.get(1);
		int stateId = (int) array.get(2);
		
		preps = null;
		defaultTable = null;
		conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie zasielok");

			StringBuilder str = new StringBuilder();
			str.append(
					"SELECT z.id, d.nazov AS druh, z.podana, p.nazov AS pobocka, odos.meno || ' ' || odos.priezvisko AS odosielatel, adre.meno || ' ' || adre.priezvisko AS adresat FROM zasielka z JOIN sluzba s ON s.id = z.sluzba_id JOIN druh_zasielky d ON d.id = s.druh_id JOIN pobocka p ON p.id = z.pobocka_id JOIN zakaznik odos ON odos.id = z.odosielatel_id JOIN zakaznik adre ON adre.id = z.adresat_id");
			if (branchId != 0 || employeeId != 0 || stateId != 0) {
				str.append(" WHERE ");
				if (branchId != 0) {
					str.append("z.pobocka_id = ?");
					if (employeeId != 0) {
						str.append(" AND z.zamestnanec_id = ?");
						if (stateId != 0) {
							str.append(" AND z.stav_zasielky_id = ?");
							preps = conn.prepareStatement(str.toString());
							preps.setInt(1, branchId);
							preps.setInt(2, employeeId);
							preps.setInt(3, stateId);
						} else {
							preps = conn.prepareStatement(str.toString());
							preps.setInt(1, branchId);
							preps.setInt(2, employeeId);
						}
					} else if (stateId != 0) {
						str.append(" AND z.stav_zasielky_id = ?");
						preps = conn.prepareStatement(str.toString());
						preps.setInt(1, branchId);
						preps.setInt(2, stateId);
					} else {
						preps = conn.prepareStatement(str.toString());
						preps.setInt(1, branchId);
					}
				} else if (employeeId != 0) {
					str.append("z.zamestnanec_id = ?");
					if (stateId != 0) {
						str.append(" AND z.stav_zasielky_id = ?");
						preps = conn.prepareStatement(str.toString());
						preps.setInt(1, employeeId);
						preps.setInt(2, stateId);
					} else {
						preps = conn.prepareStatement(str.toString());
						preps.setInt(1, employeeId);
					}
				} else if (stateId != 0) {
					str.append("z.stav_zasielky_id = ?");
					preps = conn.prepareStatement(str.toString());
					preps.setInt(1, stateId);
				}

			} else {
				preps = conn.prepareStatement(str.toString());
			}

			if (preps.execute()) {
				rs = preps.getResultSet();
			}

			m = new Model();
			defaultTable = m.buildTable(rs);

			System.out.println(str);

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return defaultTable;
	}

	public DefaultTableModel SelectStatsOfEmloyees() {

		conn = null;
		s = null;
		defaultTable = null;
		m = new Model();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na nacitanie statistik zamestnancov");

			s = conn.createStatement();
			rs = s.executeQuery(
					"SELECT zam.meno || ' ' || zam.priezvisko AS zamestnanec, count(z.id) AS pocet FROM zasielka z RIGHT JOIN zamestnanec zam ON z.zamestnanec_id = zam.id GROUP BY zam.meno, zam.priezvisko ORDER BY pocet DESC");

			defaultTable = m.buildTable(rs);

			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return defaultTable;
	}

	public DefaultTableModel SelectDefaultTable(){
		
		conn = null;
		s = null;
		defaultTable = null;
		m = new Model();		
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na vytvorenie tabulky");

			s = conn.createStatement();
			rs = s.executeQuery(
					"SELECT z.id, d.nazov AS druh, z.podana, p.nazov AS pobocka, odos.meno || ' ' || odos.priezvisko AS odosielatel, adre.meno || ' ' || adre.priezvisko AS adresat FROM zasielka z JOIN sluzba s ON s.id = z.sluzba_id JOIN druh_zasielky d ON d.id = s.druh_id JOIN pobocka p ON p.id = z.pobocka_id JOIN zakaznik odos ON odos.id = z.odosielatel_id JOIN zakaznik adre ON adre.id = z.adresat_id ORDER BY z.id ASC");

			defaultTable = m.buildTable(rs);
			
			rs.close();
			s.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return defaultTable;
	}
	
	// aktualizovanie zasielky
	public void UpdatePackage(ArrayList<Object> array, int packageId) {

		int serviceId = 0;
		conn = null;
		preps = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na aktualizaciu zasielky");

			String sql = "SELECT sluzba_id FROM zasielka WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, packageId);
			if (preps.execute()) {
				rs = preps.getResultSet();
				while (rs.next()) {
					serviceId = rs.getInt("sluzba_id");
				}
			}

			sql = "UPDATE zasielka SET hmotnost = ?, pobocka_id = ?, zamestnanec_id = ?, cena = ? WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, (int) array.get(0));
			preps.setInt(2, (int) array.get(1));
			preps.setInt(3, (int) array.get(2));
			preps.setDouble(4, (double) array.get(7));
			preps.setInt(5, packageId);
			preps.executeUpdate();

			sql = "UPDATE sluzba SET druh_id = ?, trieda_id = ?, potvrdenie_o_doruceni = ?, poistenie = ? WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, (int) array.get(3));
			preps.setInt(2, (int) array.get(4));
			preps.setBoolean(3, (boolean) array.get(5));
			preps.setBoolean(4, (boolean) array.get(6));
			preps.setInt(5, serviceId);
			preps.executeUpdate();

			rs.close();
			conn.commit();
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					System.err.println(ex.getMessage());
					System.err.println("Zmeny vykonane transakciou boli vratene spat");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void UpdateCustomer(ArrayList<Object> array, int customerId) {

		conn = null;
		PreparedStatement preps = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na aktualizaciu zakaznika");

			String sql = "UPDATE zakaznik SET meno = ?, priezvisko = ?, ulica_cislo = ?, mesto = ?, psc = ? WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setString(1, array.get(0).toString());
			preps.setString(2, array.get(1).toString());
			preps.setString(3, array.get(2).toString());
			preps.setString(4, array.get(3).toString());
			preps.setString(5, array.get(4).toString());
			preps.setInt(6, customerId);
			preps.executeUpdate();

			conn.commit();
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					System.err.println(ex.getMessage());
					System.err.println("Zmeny vykonane transakciou boli vratene spat");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void UpdateState(int stateId, int packageId) {
		conn = null;
		preps = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na aktualizaciu stavu zasielky");

			String sql = "UPDATE zasielka SET stav_zasielky_id = ? WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, stateId);
			preps.setInt(2, packageId);
			preps.executeUpdate();

			conn.commit();
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					System.err.println(ex.getMessage());
					System.err.println("Zmeny vykonane transakciou boli vratene spat");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void DeletePackage(int packageId) {

		conn = null;
		preps = null;
		int serviceId = 0;
		int senderId = 0;
		int receiverId = 0;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na odstranenie zasielok");

			String sql = "SELECT sluzba_id, odosielatel_id, adresat_id FROM zasielka WHERE id = ?";
			preps = conn.prepareStatement(sql);
			preps.setInt(1, packageId);
			if (preps.execute()) {
				rs = preps.getResultSet();
				while (rs.next()) {
					serviceId = rs.getInt("sluzba_id");
					senderId = rs.getInt("odosielatel_id");
					receiverId = rs.getInt("adresat_id");
				}

				sql = "DELETE FROM sluzba WHERE id = ?";
				preps = conn.prepareStatement(sql);
				preps.setInt(1, serviceId);
				preps.executeUpdate();

				sql = "DELETE FROM zakaznik WHERE id = ?";
				preps = conn.prepareStatement(sql);
				preps.setInt(1, senderId);
				preps.executeUpdate();

				sql = "DELETE FROM zakaznik WHERE id = ?";
				preps = conn.prepareStatement(sql);
				preps.setInt(1, receiverId);
				preps.executeUpdate();
			}

			conn.commit();
			rs.close();
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					System.err.println(ex.getMessage());
					System.err.println("Zmeny vykonane transakciou boli vratene spat");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public void InsertNewPackage(ArrayList<Object> array){
		
		conn = null;
		preps = null;
		int CustomerId1 = 0;
		int CustomerId2 = 0;
		int ServiceId = 0;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(),
					Strings.getPass());
			conn.setAutoCommit(false);
			System.out.println("Databaza bola uspesne otvorena na pridanie novej zasielky");

			String sql = "INSERT INTO zakaznik (id, meno, priezvisko, ulica_cislo, mesto, PSC) "
					+ "VALUES (DEFAULT,?,?,?,?,?) returning id";
			preps = conn.prepareStatement(sql);

			preps.setString(1, (String) array.get(0));
			preps.setString(2, (String) array.get(1));
			preps.setString(3, (String) array.get(2));
			preps.setString(4, (String) array.get(3));
			preps.setString(5, (String) array.get(4));
			if (preps.execute()) {
				ResultSet result = preps.getResultSet();
				while (result.next()) {
					CustomerId1 = result.getInt("id");
				}
			}
			preps.setString(1, (String) array.get(5));
			preps.setString(2, (String) array.get(6));
			preps.setString(3, (String) array.get(7));
			preps.setString(4, (String) array.get(8));
			preps.setString(5, (String) array.get(9));
			if (preps.execute()) {
				ResultSet result = preps.getResultSet();
				while (result.next()) {
					CustomerId2 = result.getInt("id");
				}
			}

			sql = "INSERT INTO sluzba (id, druh_id, trieda_id, poistenie, potvrdenie_o_doruceni) "
					+ "VALUES (DEFAULT,?,?,?,?) returning id";
			preps = conn.prepareStatement(sql);

			preps.setInt(1, (int) array.get(10));
			preps.setInt(2, (int) array.get(11));
			preps.setBoolean(3, (boolean) array.get(12));
			preps.setBoolean(4, (boolean) array.get(13));
			if (preps.execute()) {
				ResultSet result = preps.getResultSet();
				while (result.next()) {
					ServiceId = result.getInt("id");
				}
			}

			sql = "INSERT INTO zasielka (sluzba_id, podana, hmotnost, cena, pobocka_id, zamestnanec_id, odosielatel_id, adresat_id, stav_zasielky_id) "
					+ "VALUES (?,?::date,?,?,?,?,?,?,?)";
			preps = conn.prepareStatement(sql);

			preps.setInt(1, ServiceId);
			preps.setString(2, (String) array.get(14));
			preps.setInt(3, (int) array.get(15));
			preps.setFloat(4, (float) array.get(16));
			preps.setInt(5, (int) array.get(17));
			preps.setInt(6, (int) array.get(18));
			preps.setInt(7, CustomerId1);
			preps.setInt(8, CustomerId2);
			preps.setInt(9, 1);
			preps.executeUpdate();

			conn.commit();
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					System.err.println(ex.getMessage());
					System.err.println("Zmeny vykonane transakciou boli vratene spat");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preps != null)
					preps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
}
