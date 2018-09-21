package com.example.demo;

import java.sql.*;
import java.util.Scanner;

public class ConnectToSql {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:8889/Names";
		String user = "root";
		String pass = "VerrySecurPasswrd";
		String database = "Names";
		String table = "first_name";
		
		try {
			System.out.println("Connecting to " + url);
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection Established.");
			Statement statement = con.createStatement();
			Scanner input = new Scanner(System.in);
			System.out.print("Enter data for database: ");
			String name = input.nextLine();
			input.close();
			System.out.println();
			
			//statement.executeUpdate("CREATE DATABASE Names;");
			//statement.executeUpdate("CREATE TABLE first_name (Name VARCHAR(30));");
			statement.executeUpdate("INSERT INTO "+table+" (Name) VALUES ('"+name+"');");
			statement.executeQuery("Use " + database + ";");
			ResultSet data = statement.executeQuery("SELECT * FROM "+table+";");
			
			System.out.println("Printing Name from table first_names: ");
			while (data.next()) {
	            String name1 = data.getString("Name");
	            System.out.println(name1);
	         }
			con.close();
		}
		catch (SQLException err) {
			System.out.println(err);
		}
	}

}
