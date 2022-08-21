package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			System.out.println("Successfully connected to MySQL database ");
		} catch (SQLException e) {
			System.out.println("An error occurred while connecting MySQL databse");
			e.getMessage();
		} catch (ClassNotFoundException e) {
			System.out.println("cannot find the driver in the class path !");
			e.getMessage();
		}
	}
	private static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/payroll_service",
				"root",
				"Bhavesh@8448");
		return connection;
	}
	public static void getAllDataUsingPreparedStatemnt() throws SQLException {
		String select = "SELECT * FROM employee_payroll";
		System.out.println(select);
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");			
				String salary = resultSet.getString("basicPay");

				System.out.println("Data: "+ name + ", " + salary);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public static void updateDataUsingPreparedStatement(String basicPay, int id) throws SQLException {
		System.out.println("\nUpdated Employee salary");
		String update = "UPDATE employee_payroll SET basicPay=? where id=?";
		System.out.println(update);
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1,basicPay);
			statement.setInt(2,id);
			int rowEffected = statement.executeUpdate();
			System.out.println(rowEffected + " record updated");
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public static void retrieveAllDataUsingPreparedStatemnt() throws SQLException {
		System.out.println("\nRetrieve data who have joined in particular date range");
		String select = "select * from employee_payroll where start between cast('2021-01-01' as date) and date (now());";
		System.out.println(select);
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");			
				String start = resultSet.getString("start");

				System.out.println("Data: "+ name + ", " + start);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		
	}

	public static void main(String[] args) throws SQLException {
		connectToMysql();
		getAllDataUsingPreparedStatemnt();
		updateDataUsingPreparedStatement("8000",3);
		retrieveAllDataUsingPreparedStatemnt();
	}	
}