package com.example.demo;

import java.sql.*;

public class ConnectToSql {

	public static void main(String[] args) {
		String local = "localhost:8889";
		String user = "root";
		String pass = "root";
		
		try {
			Connection con = DriverManager.getConnection(local, user, pass);
		}
		catch (SQLException err) {
			System.out.println(err);
		}
	}

}
