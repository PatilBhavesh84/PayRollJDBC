package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayRoll {
	public static void connectToMysql() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/payroll_service",
					"root",
					"Bhavesh@8448");

			System.out.println("Successfully connected to MySQL database" );
		} catch (SQLException e) {
			System.out.println("An error occurred while connecting MySQL databse");
			e.getMessage();
		} catch (ClassNotFoundException e) {
			System.out.println("cannot find the driver in the class path !");
			e.getMessage();
		}
	}
	public static void main(String[] args) throws SQLException {
		connectToMysql();
	}
}