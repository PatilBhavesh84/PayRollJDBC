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
	public static void addDataUsingPreparedStatement(int id,String name,int phone_number,String address,String department,String gender,int basicPay,int deductions,int taxablePay,int incomeTax,int netpay,String start ) throws SQLException {
		String insert = "INSERT INTO employeepayroll('id','name','phone_number','address','department','gender','basicPay','deductions','taxablePay','incomeTax','netPay','start') values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(insert);
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1,id);
			statement.setString(2,name);
			statement.setLong(3,phone_number);
			statement.setString(4,address);
			statement.setString(5,department);
			statement.setString(6,gender);
			statement.setLong(7,basicPay);
			statement.setLong(8,deductions);
			statement.setLong(9,taxablePay);
			statement.setLong(10,incomeTax);
			statement.setLong(11,netpay);
			statement.setString(12,start);
			int rowEffected = statement.executeUpdate();
			System.out.println(rowEffected + " records inserted");
			connection.commit();
			System.out.println("Transaction is commited.");
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public static void main(String[] args) throws SQLException {
		connectToMysql();
		// prepared statement
		addDataUsingPreparedStatement(5,"Yogesh", 999745816, "wagholi","quality","M",20000, 1500,1000,800,500, "2020-05-05");
	}
}
