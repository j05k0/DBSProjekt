package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Model {

	public DefaultTableModel buildTable(ResultSet rs) {

		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			Vector<String> columnNames = new Vector<String>();
			int columnCount = rsmd.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(rsmd.getColumnName(column));
			}

			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}

			DefaultTableModel defTable = new DefaultTableModel(data, columnNames);
			defTable.fireTableDataChanged();
			return defTable;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	public DefaultTableModel buildFilterTable(TableModel filter, String name) {

		Vector<String> columnNames = new Vector<>();
		for (int col = 0; col < filter.getColumnCount(); col++) {
			columnNames.add(filter.getColumnName(col));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (int i = 0; i < filter.getRowCount(); i++) {
			if (filter.getValueAt(i, 4).equals(name)) {
				Vector<Object> vector = new Vector<>();
				for (int column = 0; column < filter.getColumnCount(); column++) {
					System.out.println(filter.getValueAt(i, column));
					vector.add(filter.getValueAt(i, column));
				}
				data.add(vector);
			}
		}
		DefaultTableModel result = new DefaultTableModel(data, columnNames);
		result.fireTableDataChanged();
		return result;
	}
}
