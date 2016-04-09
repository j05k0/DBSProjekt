package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Statistics extends AbstractWindow implements ActionListener {

	JTable table;
	JScrollPane scrollPane;
	
	public Statistics(){
		
		setTitle("Štatistika práce zamestnancov");
		setSize(300, 500);
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 284, 461);
		getContentPane().add(scrollPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		table.setModel(sql.SelectStatsOfEmloyees());
		setVisible(true);
	}

}
