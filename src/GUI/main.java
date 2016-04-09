package GUI;

import javax.swing.JFrame;

import Model.Strings;

import java.sql.*;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new MainMenu();
		f.setVisible(true);
		
		/*
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Strings.getDbUrl(), Strings.getUser(), Strings.getPass());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");*/
	}
}
